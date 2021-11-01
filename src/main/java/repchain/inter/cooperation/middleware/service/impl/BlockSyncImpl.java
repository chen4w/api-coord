package repchain.inter.cooperation.middleware.service.impl;

import com.rcjava.client.ChainInfoClient;
import com.rcjava.exception.SyncBlockException;
import com.rcjava.protos.Peer;
import com.rcjava.sync.SyncInfo;
import com.rcjava.sync.SyncListener;
import com.rcjava.sync.SyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.middleware.constant.EhCacheConstant;
import repchain.inter.cooperation.middleware.model.yml.RepChain;
import repchain.inter.cooperation.middleware.service.BlockSync;
import repchain.inter.cooperation.middleware.utils.EhcacheManager;
import repchain.inter.cooperation.middleware.utils.YamlUtils;


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
        Long height = (Long) EhcacheManager.getValue(EhCacheConstant.REPCHAIN, EhCacheConstant.BLOCK);
        long tempLocHeight;
        if (height != null) {
            tempLocHeight = Math.max(locHeight,height);
        } else {
            tempLocHeight = locHeight;
        }
        SyncInfo syncInfo;
        if (tempLocHeight == 0) {
            syncInfo = new SyncInfo(0, "");
        } else if (tempLocHeight > 0) {
            String locBlkHash = new ChainInfoClient(host).getBlockByHeight(tempLocHeight).getHashOfBlock().toStringUtf8();
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
//            // true为电力合约，false为用户合约
//            AtomicBoolean flag = new AtomicBoolean(true);
//            block.getTransactionsList().forEach(result -> {
//                if (ElectricBaseClient.CERT.equals(result.getCid().getChaincodeName())) {
//                    flag.set(false);
//                }
//            });
//            if (flag.get()) {
//                // 电力合约操作
//                block.getTransactionResultsList().forEach(result -> result.getOlList().forEach(ol -> {
//                    if (ol.getKey().startsWith(ElectricConstant.ORDER)) {
//                        // 订单操作
//                        blockSyncService.orderOperation(ol);
//                    } else if (ol.getKey().startsWith(ElectricConstant.CASH)) {
//                        // 用户余额操作
//                        blockSyncService.changeCash(ol);
//                    } else if (ol.getKey().startsWith(ElectricConstant.ROLE)) {
//                        // 用户授权操作
//                        blockSyncService.role(ol);
//                    } else if (ol.getKey().startsWith(ElectricConstant.DEAL)) {
//                        // 交易操作
//                        blockSyncService.deal(ol);
//                    } else if (ol.getKey().startsWith(ElectricConstant.CHARGE_ELECTRICITY)) {
//                        // 电量变动
//                        blockSyncService.changeElectric(ol);
//                    } else if (ol.getKey().startsWith(ElectricConstant.CONTRACT)) {
//                        // 同步合同生成
//                        blockSyncService.contract(ol);
//                    } else if (ol.getKey().startsWith(ElectricConstant.ELECTRIC_PRICE)) {
//                        // 设置电量
//                        blockSyncService.electricPrice(ol);
//                    }
//                }));
//            } else {
//                // 用户注册及证书
//                block.getTransactionResultsList().forEach(result ->
//                        result.getOlList().forEach(blockSyncService::userRegister));
//            }
        } catch (Exception ex) {
            throw new SyncBlockException(ex);
        }
    }

    @Override
    public void onError(SyncBlockException syncBlockException) {
        logger.error("同步区块出现错误：{}", syncBlockException.getMessage(), syncBlockException);
    }
}
