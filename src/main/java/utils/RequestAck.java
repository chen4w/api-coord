package utils;

import com.rcjava.client.TranPostClient;
import model.ReqAckProof;

/**
 * @author lhc
 * @version 1.0
 * @className RequestAck
 * @date 2021年10月11日 3:33 下午
 * @description 描述
 */
public class RequestAck {

    private String host;
    private TranPostClient tranPostClient;

    public RequestAck(String host) {
        this.host = host;
        this.tranPostClient = new TranPostClient(host);
    }


    public void rb(ReqAckProof reqAckProof) {

    }

    public void ri(ReqAckProof reqAckProof) {

    }

    public void re(ReqAckProof reqAckProof) {

    }
}
