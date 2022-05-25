package repchain.inter.cooperation.middleware.service.impl;

import com.google.protobuf.ByteString;
import com.rcjava.client.ChainInfoClient;
import com.rcjava.exception.SyncBlockException;
import com.rcjava.protos.Peer;
import com.rcjava.sync.SyncInfo;
import com.rcjava.sync.SyncListener;
import com.rcjava.sync.SyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.middleware.constant.EhCacheConstant;
import repchain.inter.cooperation.middleware.constant.InterfaceConstant;
import repchain.inter.cooperation.middleware.model.yml.RepChain;
import repchain.inter.cooperation.middleware.service.BlockSync;
import repchain.inter.cooperation.middleware.utils.MyCacheManager;
import repchain.inter.cooperation.middleware.utils.KeyUtils;
import repchain.inter.cooperation.middleware.utils.YamlUtils;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author lhc
 * @version 1.0
 * @className BlockSyncImpl
 * @date 2021年10月27日 9:51 上午
 * @description 描述
 */
public class BlockSyncImpl implements BlockSync, SyncListener {


    private final Logger logger = LoggerFactory.getLogger(BlockSyncImpl.class);

    private final repchain.inter.cooperation.middleware.service.SyncService syncService = new SyncServiceImpl();

    /**
     * 同步区块
     */
    @Override
    public void start() {
        RepChain repchain = YamlUtils.middleConfig.getRepchain();
        String host = repchain.getHost();
        long locHeight = repchain.getHeight();
        Long height = MyCacheManager.getHeight();
        long tempLocHeight;
        if (height != null) {
            tempLocHeight = Math.max(locHeight, height);
        } else {
            tempLocHeight = locHeight;
        }
        SyncInfo syncInfo;
        if (tempLocHeight == 0) {
            syncInfo = new SyncInfo(0, "");
        } else if (tempLocHeight > 0) {
            String locBlkHash = new ChainInfoClient(host).getBlockByHeight(tempLocHeight).getHeader().getHashPresent().toStringUtf8();
            syncInfo = new SyncInfo(tempLocHeight, locBlkHash);
        } else {
            logger.error("本地设置区块高度不能小于0");
            throw new RuntimeException("本地设置区块高度不能小于0");
        }
        SyncService repChainSyncService = SyncService.newBuilder().setHost(host).setSyncInfo(syncInfo).setSyncListener(this).build();
        Thread thread = new Thread(repChainSyncService::start, "SyncServiceThread");
        thread.start();
    }

    @Override
    public void onSuccess(Peer.Block block) throws SyncBlockException {
        try {
            // 将区块高度持久化
            syncService.saveBlock(block);
            List<String> list = new LinkedList<>();
            block.getTransactionsList().forEach(result -> list.add(result.getCid().getChaincodeName()));
            // 合约数据同步操作
            AtomicInteger i = new AtomicInteger();
            block.getTransactionResultsList().forEach(result -> {
                Map<String, ByteString> stringMap = result.getStatesSetMap();
                for (Map.Entry<String, ByteString> entry : stringMap.entrySet()) {
                    String key = entry.getKey();
                    ByteString value = entry.getValue();
                    if (InterfaceConstant.NAME.equals(KeyUtils.getChainCode(key))) {
                        // 接口定义
                        if (KeyUtils.startsWith(key, InterfaceConstant.DEF)) {
                            syncService.defInterface(value);
                        }
                        // 接口登记
                        if (KeyUtils.startsWith(key, InterfaceConstant.REGISTER) || KeyUtils.startsWith(key, InterfaceConstant.INVOKE_REGISTER)) {
                            syncService.register(value);
                        }
                        // 接口存证
                        if (KeyUtils.startsWith(key, InterfaceConstant.ACK_PROOF)) {
                            syncService.ackProof(value);
                        }
                    }
                    logger.info("key:"+entry.getKey());
                    try {
                        logger.info("value:"+entry.getValue().toString("utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                }
//                result.getOlList().forEach(ol -> {
//                    String key = ol.getKey();
//                    // 接口协同合约
//                    if (InterfaceConstant.NAME.equals(chainCodeName)) {
//                        // 接口定义
//                        if (KeyUtils.startsWith(key, InterfaceConstant.DEF)) {
//                            syncService.defInterface(ol);
//                        }
//                        // 接口登记
//                        if (KeyUtils.startsWith(key, InterfaceConstant.REGISTER) || KeyUtils.startsWith(key, InterfaceConstant.INVOKE_REGISTER)) {
//                            syncService.register(ol);
//                        }
//                        // 接口存证
//                        if (KeyUtils.startsWith(key, InterfaceConstant.ACK_PROOF)) {
//                            syncService.ackProof(ol);
//                        }
//                    }
//                });
                i.getAndIncrement();
            });
        } catch (Exception ex) {
            throw new SyncBlockException(ex);
        }
    }

    @Override
    public void onError(SyncBlockException syncBlockException) {
        logger.error("同步区块出现错误：{}", syncBlockException.getMessage(), syncBlockException);
    }
}
