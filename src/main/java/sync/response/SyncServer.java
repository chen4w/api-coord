package sync.response;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.json.JSONUtil;
import com.rcjava.sign.impl.ECDSASign;
import model.Header;
import model.SysCert;
import model.tran.ReqAckProof;
import model.tran.Signature;
import model.yml.InterCo;
import model.yml.RepchainConfig;
import model.yml.Service;
import utils.PkUtil;
import utils.ResponseAck;
import utils.YamlUtils;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lhc
 * @version 1.0
 * @className Server
 * @date 2021年10月11日 3:03 下午
 * @description 同步单次请求服务端代码
 */
public class SyncServer {

    /**
     * @return void
     * @author lhc
     * @description // 服务断启动
     * @date 3:11 下午 2021/10/11
     * @params [args]
     **/
    public static void main(String[] args) {
        // 创建Http服务器，端口为8888
        HttpUtil.createServer(8888)
                // 签名接口，对调用方数据进行数据签名
                .addAction("/sign", SyncServer::sign)
                // 业务接口地址，此处为需要提供给其他系统调用的业务接口 ,地址为/info
                .addAction("/info", SyncServer::getInfo)
                .start();
    }

    /**
     * @return void
     * @author lhc
     * @description // 模拟controller方法，此处为模拟的业务接口
     * @date 2:17 下午 2021/10/12
     * @params [name]
     **/
    public static void getInfo(HttpServerRequest request, HttpServerResponse response) {
        // 获取请求头
        String headerStr = request.getParam("header");
        Header header = JSONUtil.toBean(headerStr, Header.class);
        // 获取本次请求的存证id，此id会在本次请求存证中重复使用
        String cid = header.getCid();
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
        // 服务端获取请求，构建服务方存证对象
        ResponseAck responseAck = new ResponseAck(host);
        // 获取请求时存证
        ReqAckProof cb = cb(interCo, service, cid);
        responseAck.cb(cb, sysCert);
        // 构建返回结果数据
        String name = request.getParam("name");
        String info = "This is your info," + name;
        Map<String, Object> result = new HashMap<>(3);
        result.put("code", 0);
        result.put("msg", "success");
        result.put("data", info);
        result.put("sign", "");
        // 返回结果存证
        ReqAckProof ci = ci(interCo, service, cid, result);
        responseAck.ci(ci, sysCert);
        // 返回数据给请求方
        response.write(JSONUtil.toJsonStr(result));
        // 返回数据结束存证
        responseAck.ce(ce(interCo, service, cid, JSONUtil.toJsonStr(result)), sysCert);
    }

    /**
     * @return void
     * @author lhc
     * @description // 数据签名
     * @date 2:15 下午 2021/10/13
     * @params [request, response]
     **/
    public static void sign(HttpServerRequest request, HttpServerResponse response) {
        // 获取yml文件中的信息
        RepchainConfig repchainConfig = YamlUtils.repchainConfig;
        List<InterCo> interCoList = repchainConfig.getRepchain().getInterCo();
        InterCo interCo = interCoList.get(0);
        // 获取PrivateKey对象
        PrivateKey privateKey = PkUtil.getPrivateKey(interCo.handlePrivateKey(), interCo.getPassword());
        // 传过来为json字符串
        String data = request.getParam("data");
        // 将传输内容转换为hash，hash算法需与接口定义一致
        String contentHash = DigestUtil.sha256Hex(data);
        // 构建签名对象
        Signature signature = Signature
                .builder()
                // 用户的唯一标识
                .eid(interCo.getCreditCode())
                // 用户证书名称
                .cert_name(interCo.getCertName())
                // 内容hash
                .hash(contentHash)
                // 对内容数据进行签名
                .sign(HexUtil.encodeHexStr((new ECDSASign()).sign(privateKey, data.getBytes(StandardCharsets.UTF_8), "SHA256withECDSA")))
                // 时间戳
                .timeCreate(System.currentTimeMillis())
                .build();
        // 返回签名数据
        response.write(JSONUtil.toJsonStr(signature));
    }

    /**
     * @return model.tran.ReqAckProof
     * @author lhc
     * @description // 开始返回结果存证
     * @date 9:26 上午 2021/10/14
     * @params [interCo, service, cid]
     **/
    public static ReqAckProof cb(InterCo interCo, Service service, String cid) {
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
     * @description // 中间返回结果存证
     * @date 9:30 上午 2021/10/14
     * @params [interCo, service, cid, content]
     **/
    public static ReqAckProof ci(InterCo interCo, Service service, String cid, Map<String, Object> content) {
        // 将传输内容转换为hash
        String contentHash = DigestUtil.sha256Hex(JSONUtil.toJsonStr(content));
        // 构建签名对象
        Signature signature = getSignature(interCo, contentHash, JSONUtil.toJsonStr(content));
        // 构建请求头
        Header header = getHeader(service, cid, 2, false);
        // 返回构建的交易对象
        return getReqAckProof(content, header, contentHash, signature, service);
    }

    public static ReqAckProof ce(InterCo interCo, Service service, String cid, String content) {
        // 将传输内容转换为hash
        String contentHash = DigestUtil.sha256Hex(content);
        // 构建签名对象
        Signature signature = getSignature(interCo, contentHash, content);
        // 构建请求头
        Header header = getHeader(service, cid, 3, true);
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
                .b_req(false)
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
        String signatureStr = HttpUtil.get("http://" + service.getFrom_host() + ":" + service.getFrom_port() + "/sign", signMap);
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
