package repchain.inter.cooperation.middleware.utils;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.rcjava.client.TranPostClient;
import com.rcjava.protos.Peer;
import repchain.inter.cooperation.middleware.model.SysCert;
import repchain.inter.cooperation.middleware.model.tran.ReqAckProof;

/**
 * @author lhc
 * @version 1.0
 * @className RequestAck
 * @date 2021年10月11日 3:33 下午
 * @description 描述
 */
public class ResponseAck {

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
    public ResponseAck(String host) {
        this.tranPostClient = new TranPostClient(host);
    }

    public JSONObject cb(ReqAckProof reqAckProof,SysCert sysCert) {
        return getJsonObject(reqAckProof, sysCert);

    }

    public JSONObject ci(ReqAckProof reqAckProof,SysCert sysCert){
        return getJsonObject(reqAckProof, sysCert);
    }

    public JSONObject ce(ReqAckProof reqAckProof,SysCert sysCert){
        return getJsonObject(reqAckProof, sysCert);
    }


    private JSONObject getJsonObject(ReqAckProof reqAckProof, SysCert sysCert) {
        String tranId = SnowIdGenerator.getId();
        TranClient userClient = TranClient.getClient(sysCert.getCreditCode(), sysCert.getCertName(), sysCert.getPrivateKey(), chainCodeName, sysCert.getPassword());
        Peer.Transaction tran = userClient
                .getTranCreator()
                .createInvokeTran(tranId, userClient.getCertId(), userClient.getChaincodeId(), functionName, JSONUtil.toJsonStr(reqAckProof),0,"");
        return tranPostClient.postSignedTran(tran);
    }
}
