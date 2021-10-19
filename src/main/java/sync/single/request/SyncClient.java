package sync.single.request;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.rcjava.sign.impl.ECDSASign;
import model.Header;
import model.SysCert;
import model.tran.ReqAckProof;
import model.tran.Signature;
import model.yml.InterCo;
import model.yml.RepchainConfig;
import model.yml.Service;
import utils.PkUtil;
import utils.RequestAck;
import utils.SnowIdGenerator;
import utils.YamlUtils;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lhc
 * @version 1.0
 * @className Client
 * @date 2021年10月11日 3:10 下午
 * @description 同步单次请求客户端代码
 */
public class SyncClient {

    public static void main(String[] args) {
        // 1. 构建请求的请求头及请求参数
        // 生成本次请求的存证id，此id会在本次请求存证中重复使用
        String cid = SnowIdGenerator.getId();
        // 获取yml文件中的信息
        RepchainConfig repchainConfig = YamlUtils.repchainConfig;
        List<InterCo> interCoList = repchainConfig.getRepchain().getInterCo();
        InterCo interCo = interCoList.get(0);
        List<Service> services = interCo.getServices();
        Service service = services.get(0);
        // 构建接口调用参数
        Map<String, Object> paramMap = new HashMap<>(3);
        paramMap.put("name", "Tom");
        // 对业务请求数据进行hash取值
        String contentHash = DigestUtil.sha256Hex(JSONUtil.toJsonStr(paramMap));
        // 使用yml文件中的公钥和证书，对业务请求参数进行数据签名
        Signature signature = getSignature(interCo, contentHash, JSONUtil.toJsonStr(paramMap));
        // 此处需要将构建的请求头内容传给服务方，此处请求头信息包含了接口协同需要存证的信息，及数据签名需要校验的身份信息
        Header header = customHeader(service, cid, false, signature,JSONUtil.toJsonStr(paramMap));
        paramMap.put("header", JSONUtil.toJsonStr(header));
        // 2. 请求业务接口
        // 请求业务接口，服务方接口地址及端口号可从dashboard管理平台获取，然后将端口号和地址写入到yml文件中
        String result = HttpUtil.get("http://" + service.getTo_host() + ":" + service.getTo_port() + "/info", paramMap);
        System.out.println(result);
        // 3. 请求及应答信息存证
        // 获取返回结果对象
        Map<String, Object> resultMap = JSONUtil.parseObj(result);
        // 获取服务提供方签名信息
        Signature responseSignature = JSONUtil.toBean(resultMap.get("signature").toString(), Signature.class);
        // 构建证书对象，用于签名数据及校验权限用
        SysCert sysCert = PkUtil.getSysCert(interCo);
        // 获取yml文件中配置的host地址
        String host = repchainConfig.getRepchain().getHost();
        // 创建请求实例，若用Spring 此处可以创建javabean
        RequestAck requestAck = new RequestAck(host);
        // 构建存证交易对象，使用证书和私钥对存证信息进行数据签名，提交给区块链进行存证
        ReqAckProof rb = getReqAckProof(header, contentHash, signature, responseSignature);
        JSONObject jsonObject = requestAck.rb(rb, sysCert);
        // 若果有错误信息，则提交存证数据失败
        if (!StrUtil.isBlankIfStr(jsonObject.get("err"))) {
            System.out.println("提交区块链数据失败：" + jsonObject);
        }
    }

    /**
     * @return model.Header
     * @author lhc
     * @description // 构建中间接口存证请求头
     * @date 5:22 下午 2021/10/13
     * @params [service (yml文件读取，包含请求方id和调用方id), cid (请求id), seq (存证序号), isEnd （是否为结束调用存证）]
     **/
    public static Header customHeader(Service service, String cid, boolean isEnd, Signature signature,String content) {
        return Header
                .builder()
                // 请求Id
                .cid(cid)
                // 请求方id，可从yml文件中获取，此id为服务请求登记时的id，可从dashboard中查看
                .e_from(service.getE_from())
                .e_to(service.getE_to())
                // 请求接口/方法
                .method("GET http://" + service.getTo_host() + ":" + service.getTo_port() + "/info")
                // 创建时间
                .tm_create(System.currentTimeMillis())
                // 请求 or 应答标志, true 代表请求; false 代表应答
                .b_req(true)
                // 结束标志, true 代表结束（即本次请求/应答为最后一个）,false代表未结束
                .b_end(isEnd)
                // 请求或应答的序号, 从1开始
                .seq(1)
                // 用于校验权限签名的字符串
                .validStr(signature.getSign())
                // 签名的数据
                .signData(content)
                .build();
    }

    /**
     * @return model.tran.Signature
     * @author lhc
     * @description // 构建签名对象
     * @date 4:41 下午 2021/10/13
     * @params [interCo （读取yml文件内容）, contentHash （内容存证的hash）, content (内容)]
     **/
    public static Signature getSignature(InterCo interCo, String contentHash, String content) {
        // 获取PrivateKey对象
        PrivateKey privateKey = PkUtil.getPrivateKey(interCo.handlePrivateKey(), interCo.getPassword());
        // 构建签名对象
        return Signature
                .builder()
                // 用户的唯一标识
                .eid(interCo.getCreditCode())
                // 用户证书名称
                .cert_name(interCo.getCertName())
                // 内容hash
                .hash(contentHash)
                // 对内容数据进行签名
                .sign(HexUtil.encodeHexStr((new ECDSASign()).sign(privateKey, content.getBytes(StandardCharsets.UTF_8), "SHA256withECDSA")))
                // 时间戳
                .timeCreate(System.currentTimeMillis())
                .build();
    }

    /**
     * @return model.tran.ReqAckProof
     * @author lhc
     * @description // 构建交易对象，获取服务方数据签名
     * @date 4:35 下午 2021/10/13
     * @params [content （内容）, header （请求头）, contentHash （内容hash）, signature（签名对象）]
     **/
    public static ReqAckProof getReqAckProof(Header header, String contentHash, Signature signature, Signature responseSignature) {
        // 构建提交给区块链的交易对象
        ReqAckProof reqAckProof = ReqAckProof
                .builder()
                // 将内容进行hash取值，hash算法请遵循服务定义的内容hash算法，默认为SHA256withECDSA
                .hash(contentHash)
                // 设置请求方签名对象
                .sign_r(signature)
                // 设置服务方签名对象
                .sign_c(responseSignature)
                .build();
        // 将header请求头中内容copy到reqAckProof中，构建交易对象
        BeanUtil.copyProperties(header, reqAckProof);
        return reqAckProof;
    }
}