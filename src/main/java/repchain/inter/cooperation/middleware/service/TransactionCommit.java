package repchain.inter.cooperation.middleware.service;

import repchain.inter.cooperation.middleware.model.tran.ReqAckProof;

/**
 * @author lhc
 * @version 1.0
 * @className RepChainCommit
 * @date 2021年10月27日 9:25 上午
 * @description 描述
 */
public interface TransactionCommit {
    void saveProof(ReqAckProof reqAckProof);
}
