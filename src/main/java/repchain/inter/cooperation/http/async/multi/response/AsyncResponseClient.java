package repchain.inter.cooperation.http.async.multi.response;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.rcjava.sign.impl.ECDSASign;
import repchain.inter.cooperation.http.model.Header;
import repchain.inter.cooperation.http.model.SysCert;
import repchain.inter.cooperation.http.model.tran.ReqAckProof;
import repchain.inter.cooperation.http.model.tran.Signature;
import repchain.inter.cooperation.http.model.yml.InterCo;
import repchain.inter.cooperation.http.model.yml.RepchainConfig;
import repchain.inter.cooperation.http.model.yml.Service;
import repchain.inter.cooperation.http.utils.PkUtil;
import repchain.inter.cooperation.http.utils.RequestAck;
import repchain.inter.cooperation.http.utils.SnowIdGenerator;
import repchain.inter.cooperation.http.utils.YamlUtils;

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
public class AsyncResponseClient {

    public static void main(String[] args) {
        // 生成本次请求的存证id，此id会在本次请求存证中重复使用
        String cid = SnowIdGenerator.getId();
        // 获取yml文件中的信息
        RepchainConfig repchainConfig = YamlUtils.repchainConfig;
        List<InterCo> interCoList = repchainConfig.getRepchain().getInterCo();
        InterCo interCo = interCoList.get(0);
        List<Service> services = interCo.getServices();
        Service service = services.get(0);
        // 构建证书对象，用于签名数据及校验权限用
        SysCert sysCert = PkUtil.getSysCert(interCo);
        // 获取yml文件中配置的host地址
        String host = repchainConfig.getRepchain().getHost();
        // 创建请求实例，可使用@Autowired创建单例模式对象
        RequestAck requestAck = new RequestAck(host);
        // 1. 起始接口存证
        ReqAckProof rb = rb(interCo, service, cid);
        JSONObject jsonObject = requestAck.rb(rb, sysCert);
        // 若果有错误信息，则提交存证数据失败
        if (!StrUtil.isBlankIfStr(jsonObject.get("err"))) {
            System.out.println("提交区块链数据失败：" + jsonObject.get("err"));
        }
        System.out.println(jsonObject);
        // 2. 中间接口存证
        // 构建接口调用参数
        Map<String, Object> paramMap = new HashMap<>(2);
        paramMap.put("name", "Tom");
        // 此处需要将构建的请求头内容传给服务方
        paramMap.put("header", JSONUtil.toJsonStr(getHeader(service, cid, 2, false)));
        // 业务逻辑...
        // 构建交易对象提交给区块链进行存证,一个业务逻辑中可以多次调用服务方接口，若中间多次调用服务方接口，则每一次调用都需要进行存证，存证序号seq参数需要递增，如2，3，4，5
        ReqAckProof ri = ri(interCo, service, cid, paramMap,2);
        jsonObject = requestAck.ri(ri, sysCert);
        if (!StrUtil.isBlankIfStr(jsonObject.get("err"))) {
            System.out.println("提交区块链数据失败：" + jsonObject.get("err"));
        }
        // 请求业务接口
        String result = HttpUtil.get("http://" + service.getTo_host() + ":" + service.getTo_port() + "/info", paramMap);
        System.out.println(result);
        // 3. 结束应答接口存证，此处
        jsonObject = requestAck.re(re(interCo, service, cid, result, 3), sysCert);
        if (!StrUtil.isBlankIfStr(jsonObject.get("err"))) {
            System.out.println("提交区块链数据失败：" + jsonObject.get("err"));
        }
    }


    /**
     * @return model.tran.ReqAckProof
     * @author lhc
     * @description // 构建起始接口存证对象
     * @date 4:48 下午 2021/10/13
     * @params [repchainConfig （yml文件读取内容）, cid（本次请求存证id）, paramMap（请求的入参数据）]
     **/
    public static ReqAckProof rb(InterCo interCo, Service service, String cid) {
        // 起始内容，可更改成其他的内容
        String content = "begin";
        // 将传输内容转换为hash
        String contentHash = DigestUtil.sha256Hex(content);
        // 构建签名对象
        Signature signature = getSignature(interCo, contentHash, content);
        // 构建请求头
        Header header = getHeader(service, cid, 1, false);
        // 返回构建的交易对象
        return getReqAckProof(content, header, contentHash, signature, service);
    }

    /**
     * @return model.tran.ReqAckProof
     * @author lhc
     * @description // 构建中间接口存证对象
     * @date 16:24 上午 2021/10/13
     * @params [repchainConfig （yml文件读取内容）, cid（本次请求存证id）, content（请求的入参数据）,seq (请求应的序号，此处为上一次存证的序号+1)]
     **/
    public static ReqAckProof ri(InterCo interCo, Service service, String cid, Map<String, Object> content,int seq) {
        // 将传输内容转换为hash
        String contentHash = DigestUtil.sha256Hex(JSONUtil.toJsonStr(content));
        // 构建签名对象
        Signature signature = getSignature(interCo, contentHash, JSONUtil.toJsonStr(content));
        // 构建请求头
        Header header = getHeader(service, cid, seq, false);
        // 返回构建的交易对象
        return getReqAckProof(content, header, contentHash, signature, service);
    }

    /**
     * @return model.tran.ReqAckProof
     * @author lhc
     * @description // 构建结束接口存证对象
     * @date 16:24 上午 2021/10/13
     * @params [repchainConfig （yml文件读取内容）, cid（本次请求存证id）, content（返回结果的内容）, seq(请求或应答的序号, 此处为最后一次请求的序号+1) ]
     **/
    public static ReqAckProof re(InterCo interCo, Service service, String cid, String content,int seq) {
        // 将传输内容转换为hash
        String contentHash = DigestUtil.sha256Hex(content);
        // 构建签名对象
        Signature signature = getSignature(interCo, contentHash, content);
        // 构建请求头
        Header header = getHeader(service, cid, seq, true);
        // 返回构建的交易对象
        return getReqAckProof(content, header, contentHash, signature, service);
    }


    /**
     * @return model.Header
     * @author lhc
     * @description // 构建中间接口存证请求头
     * @date 5:22 下午 2021/10/13
     * @params [service (yml文件读取，包含请求方id和调用方id), cid (请求id), seq (存证序号), isEnd （是否为结束调用存证）]
     **/
    private static Header getHeader(Service service, String cid, int seq, boolean isEnd) {
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
                .seq(seq)
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
    public static ReqAckProof getReqAckProof(Object content, Header header, String contentHash, Signature signature, Service service) {
        // 发送数据让服务方进行数据签名
        Map<String, Object> signMap = new HashMap<>(1);
        signMap.put("data", content);
        signMap.put("header", JSONUtil.toJsonStr(header));
        String signatureStr = HttpUtil.get("http://" + service.getTo_host() + ":" + service.getTo_port() + "/sign", signMap);
        Signature responseSignature = JSONUtil.toBean(signatureStr, Signature.class);
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
