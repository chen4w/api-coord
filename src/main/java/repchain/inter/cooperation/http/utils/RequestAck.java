package repchain.inter.cooperation.http.utils;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.rcjava.client.TranPostClient;
import com.rcjava.protos.Peer;
import repchain.inter.cooperation.http.model.SysCert;
import repchain.inter.cooperation.http.model.tran.ReqAckProof;

/**
 * @author lhc
 * @version 1.0
 * @className RequestAck
 * @date 2021年10月11日 3:33 下午
 * @description 请求方提交数据存证到区块链
 */
public class RequestAck {

    /**
     * 区块链客户端
     */
    private final TranPostClient tranPostClient;

    /**
     * 合约名称
     */
    private String chainCodeName = "InterfaceCooperation";
    /**
     * 合约中的方法名称
     */
    private String functionName = "reqAckProof";

    /**
     * 构造函数
     *
     * @param host 设置repchain地址
     */
    public RequestAck(String host) {
        this.tranPostClient = new TranPostClient(host);
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @author lhc
     * @description // 接口请求方调用起始存证
     * @date 11:07 上午 2021/10/13
     * @params [reqAckProof （交易对象）, sysCert（证书对象）]
     **/
    public JSONObject rb(ReqAckProof reqAckProof, SysCert sysCert) {
        return getJsonObject(reqAckProof, sysCert);
    }

    /**
     * @return com.alibaba.fastjson.JSONObject
     * @author lhc
     * @description // 接口中间调用存证
     * @date 4:44 下午 2021/10/13
     * @params [reqAckProof, sysCert]
     **/
    public JSONObject ri(ReqAckProof reqAckProof, SysCert sysCert) {
        return getJsonObject(reqAckProof, sysCert);
    }

    /**
     * @author lhc
     * @description // 接口调用结束存证
     * @date 4:45 下午 2021/10/13
     * @params [reqAckProof, sysCert]
     * @return com.alibaba.fastjson.JSONObject
     **/
    public JSONObject re(ReqAckProof reqAckProof, SysCert sysCert) {
        return getJsonObject(reqAckProof, sysCert);
    }

    private JSONObject getJsonObject(ReqAckProof reqAckProof, SysCert sysCert) {
        String tranId = SnowIdGenerator.getId();
        TranClient userClient = TranClient.getClient(sysCert.getCreditCode(), sysCert.getCertName(), sysCert.getPrivateKey(), chainCodeName, sysCert.getPassword());
        Peer.Transaction tran = userClient
                .getTranCreator()
                .createInvokeTran(tranId, userClient.getCertId(), userClient.getChaincodeId(), functionName, JSONUtil.toJsonStr(reqAckProof));
        return tranPostClient.postSignedTran(tran);
    }
}
