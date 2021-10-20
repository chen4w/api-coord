package repchain.inter.cooperation.http.async.multi.response;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.rcjava.sign.impl.ECDSASign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.http.async.single.response.AsyncResponseClient;
import repchain.inter.cooperation.http.model.Header;
import repchain.inter.cooperation.http.model.InterCoResult;
import repchain.inter.cooperation.http.model.SysCert;
import repchain.inter.cooperation.http.model.tran.ReqAckProof;
import repchain.inter.cooperation.http.model.tran.Signature;
import repchain.inter.cooperation.http.model.yml.InterCo;
import repchain.inter.cooperation.http.model.yml.RepchainConfig;
import repchain.inter.cooperation.http.model.yml.Service;
import repchain.inter.cooperation.http.utils.PkUtil;
import repchain.inter.cooperation.http.utils.RequestAck;
import repchain.inter.cooperation.http.utils.YamlUtils;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.*;

/**
 * @author lhc
 * @version 1.0
 * @className Client
 * @date 2021年10月11日 3:10 下午
 * @description 同步单次请求客户端代码
 */
public class AsyncMultiResponseClient {

    private static final Logger logger = LoggerFactory.getLogger(AsyncResponseClient.class);

    /**
     * 模拟数据库列表
     */
    private static final List<Map<Object, Object>> list = new ArrayList<>();

    static {
        // 插入数据，模拟数据库数据
        Map<Object, Object> firstInfo = MapUtil.of(new String[][]{
                {"name", "Tom"},
                {"age", "18"},
        });
        list.add(firstInfo);
        Map<Object, Object> secondInfo = MapUtil.of(new String[][]{
                {"name", "Jack"},
                {"age", "16"},
        });
        list.add(secondInfo);
        Map<Object, Object> thirdInfo = MapUtil.of(new String[][]{
                {"name", "Bin"},
                {"age", "17"},
        });
        list.add(thirdInfo);
        Map<Object, Object> fourthInfo = MapUtil.of(new String[][]{
                {"name", "Lucy"},
                {"age", "18"},
        });
        list.add(fourthInfo);
    }


    public static void main(String[] args) {
        try {
            // 从数据库取出，之前请求方发送的数据及请求头，state=0 代表还没有返回的记录
            List<Entity> headers = Db.use().query("select * from header where state=0 and data like '%pageSize%' order by cid,id", new HashMap<>(0));
            // 此处根据业务逻辑自己定义，示例为依次调用已持久化的header的回调接口地址，返回数据
            if (headers != null && headers.size() > 0) {
                String cid = "";
                int seq = 1;
                for (Entity entity : headers) {
                    // 获取还未返回数据的header信息
                    Header header = entity.toBean(Header.class);
                    // 获取持久化的请求的业务数据，示例为 {pageNo:1,pageSize:1} 这种数据类型的解析，请根据业务逻辑自行修改
                    if (cid.equals(header.getCid())) {
                        seq++;
                    } else {
                        seq = 1;
                    }
                    cid = header.getCid();
                    String page = header.getData();
                    Map<String, Object> map = JSONUtil.parseObj(page);
                    Integer pageSize = (Integer) map.get("pageSize");
                    Integer pageNo = (Integer) map.get("pageNo");
                    // 模拟数据库分页查询
                    List<Map<Object, Object>> resultList = new ArrayList<>();
                    int currIdx = (pageNo > 1 ? (pageNo - 1) * pageSize : 0);
                    for (int i = 0; i < pageSize && i < list.size() - currIdx; i++) {
                        resultList.add(list.get(currIdx + i));
                    }
                    // 构建请求参数对象
                    Map<String, Object> requestMap = new HashMap<>(1);
                    requestMap.put("data", resultList);
                    // 发送请求，并存证
                    // 设置请求序号
                    header.setSeq(seq);
                    request(requestMap, header);
                    // 更改header状态state=1，表示对当前的异步请求已经返回数据，可根据自身业务逻辑自行修改
                    header.setState(1);
                    Db.use().update(Entity.create().parseBean(header, true, true), Entity.create("header").set("id", header.getId()));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * @return void
     * @author lhc
     * @description // 请求数据
     * @date 5:38 下午 2021/10/18
     * @params [paramMap (请求入参), cid（请求id）, req (请求序号，从1开始递增)]
     **/
    public static void request(Map<String, Object> paramMap, Header dataHeader) {
        // 获取yml文件中的信息
        RepchainConfig repchainConfig = YamlUtils.repchainConfig;
        List<InterCo> interCoList = repchainConfig.getRepchain().getInterCo();
        InterCo interCo = interCoList.get(0);
        List<Service> services = interCo.getServices();
        Service service = services.get(0);
        // 对业务请求数据进行hash取值
        String contentHash = DigestUtil.sha256Hex(JSONUtil.toJsonStr(paramMap));
        // 使用yml文件中的公钥和证书，对业务请求参数进行数据签名
        Signature signature = getSignature(interCo, contentHash);
        // 此处需要将构建的请求头内容传给服务方，此处请求头信息包含了接口协同需要存证的信息，及数据签名需要校验的身份信息
        Header header = customHeader(service, false, signature, dataHeader);
        paramMap.put("header", JSONUtil.toJsonStr(header));
        String result;
        // 请求业务接口，服务方接口地址及端口号可从dashboard管理平台获取，然后将端口号和地址写入到yml文件中
        if ("GET".equals(dataHeader.getCallback_method())) {
            result = HttpUtil.get(dataHeader.getCallback_url(), paramMap);
        } else {
            result = HttpUtil.post(dataHeader.getCallback_url(), paramMap);
        }
        System.out.println(result);
        // 获取返回结果对象
        InterCoResult resultMap = JSONUtil.toBean(result, InterCoResult.class);
        // 获取服务提供方签名信息
        Signature responseSignature = resultMap.getSignature();
        // 构建证书对象，用于签名数据及校验权限用
        SysCert sysCert = PkUtil.getSysCert(interCo);
        // 获取yml文件中配置的区块链的host地址
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
    public static Header customHeader(Service service, boolean isEnd, Signature signature, Header header) {
        return Header
                .builder()
                // 请求Id
                .cid(header.getCid())
                // 请求方id，可从yml文件中获取，此id为服务请求登记时的id，可从dashboard中查看
                .e_from(service.getE_from())
                .e_to(service.getE_to())
                // 请求接口/方法
                .method(header.getCallback_method() + " " + header.getCallback_url())
                // 创建时间
                .tm_create(System.currentTimeMillis())
                // 请求 or 应答标志, true 代表请求; false 代表应答
                .b_req(true)
                // 结束标志, true 代表结束（即本次请求/应答为最后一个）,false代表未结束
                .b_end(isEnd)
                // 请求或应答的序号, 从1开始
                .seq(header.getSeq())
                // 用于校验权限签名的字符串
                .validStr(signature.getSign())
                // 用于保存签名的内容
                .signData(signature.getHash())
                .build();
    }

    /**
     * @return model.tran.Signature
     * @author lhc
     * @description // 构建签名对象
     * @date 4:41 下午 2021/10/13
     * @params [interCo （读取yml文件内容）, contentHash （内容存证的hash）, content (内容)]
     **/
    public static Signature getSignature(InterCo interCo, String contentHash) {
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
                .sign(HexUtil.encodeHexStr((new ECDSASign()).sign(privateKey, contentHash.getBytes(StandardCharsets.UTF_8), "SHA256withECDSA")))
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
