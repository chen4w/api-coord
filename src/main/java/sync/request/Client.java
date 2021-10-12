package sync.request;

import cn.hutool.http.HttpUtil;
import model.ReqAckProof;
import model.yml.RepchainConfig;
import utils.RequestAck;
import utils.YamlUtils;

import java.util.HashMap;

/**
 * @author lhc
 * @version 1.0
 * @className Client
 * @date 2021年10月11日 3:10 下午
 * @description 同步单次请求客户端代码
 */
public class Client {

    public static void main(String[] args) {
        // 从yml文件中读取host地址
        RepchainConfig repchainConfig = YamlUtils.repchainConfig;
        String host = repchainConfig.getRepchain().getHost();
        // 创建请求实例，可使用@Autowired创建单例模式对象
        RequestAck requestAck = new RequestAck(host);
        // 起始接口存证
        requestAck.rb(new ReqAckProof());
        HashMap<String, Object> paramMap = new HashMap<>(1);
        paramMap.put("name", "Tom");
        // 中间接口存证
        requestAck.ri(new ReqAckProof());
        String result1= HttpUtil.get("http://localhost:8888/info",paramMap);
        System.out.println(result1);
        // 结束应答接口存证
        requestAck.re(new ReqAckProof());
    }
}
