package repchain.inter.cooperation.middleware.service.impl;

import com.rcjava.protos.Peer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.middleware.constant.EhCacheConstant;
import repchain.inter.cooperation.middleware.model.tran.ApiDefinition;
import repchain.inter.cooperation.middleware.model.tran.ApiServAndAck;
import repchain.inter.cooperation.middleware.model.tran.ReqAckProof;
import repchain.inter.cooperation.middleware.service.SyncService;
import repchain.inter.cooperation.middleware.utils.BlockResultUtil;
import repchain.inter.cooperation.middleware.utils.MyCacheManager;



/**
 * @author lhc
 * @version 1.0
 * @className SyncServiceImpl
 * @date 2021年11月01日 4:18 下午
 * @description 描述
 */
public class SyncServiceImpl implements SyncService {

    private static final Logger logger = LoggerFactory.getLogger(SyncServiceImpl.class);

    @Override
    public void saveBlock(Peer.Block block) {
        long height = block.getHeader().getHeight();
        logger.info("同步区块成功，区块高度为：{}，开始数据库相关操作", height);
        MyCacheManager.putHeight(height);
        logger.info("区块：{} 保存成功", height);
    }
//
//    @Override
//    public void defInterface(Peer.OperLog ol) {
//        ApiDefinition apiDefinition = BlockResultUtil.toBean(ol, ApiDefinition.class);
//        String id = apiDefinition.getId();
//        MyCacheManager.put(EhCacheConstant.API_DEFINITION, id, apiDefinition);
//    }
//
//    @Override
//    public void register(Peer.OperLog ol) {
//        ApiServAndAck apiServAndAck = BlockResultUtil.toBean(ol, ApiServAndAck.class);
//        String id = apiServAndAck.getId();
//        MyCacheManager.put(EhCacheConstant.API_SERV_AND_ACK, id, apiServAndAck);
//    }
//
//    @Override
//    public void ackProof(Peer.OperLog ol) {
//        ReqAckProof reqAckProof = BlockResultUtil.toBean(ol, ReqAckProof.class);
//        MyCacheManager.delete(EhCacheConstant.REQ_ACK_PROOF, reqAckProof.getCid()+"_"+reqAckProof.getHash()+"_"+reqAckProof.getTm_create());
//    }
}
