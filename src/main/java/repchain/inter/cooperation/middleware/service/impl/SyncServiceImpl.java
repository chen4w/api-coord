package repchain.inter.cooperation.middleware.service.impl;

import cn.hutool.core.map.MapUtil;
import com.rcjava.protos.Peer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.middleware.constant.EhCacheConstant;
import repchain.inter.cooperation.middleware.service.SyncService;
import repchain.inter.cooperation.middleware.utils.EhcacheManager;


/**
 * @author lhc
 * @version 1.0
 * @className SyncServiceImpl
 * @date 2021年11月01日 4:18 下午
 * @description 描述
 */
public class SyncServiceImpl implements SyncService {

    private static final Logger logger = LoggerFactory.getLogger(CommunicationServerImpl.class);

    @Override
    public void saveBlock(Peer.Block block) {
        logger.info("同步区块成功，区块高度为：{}，开始数据库相关操作", block.getHeight());
        EhcacheManager.put(EhCacheConstant.REPCHAIN, EhCacheConstant.BLOCK, block.getHeight());
        logger.info("区块：{} 保存成功", block.getHeight());
    }
}
