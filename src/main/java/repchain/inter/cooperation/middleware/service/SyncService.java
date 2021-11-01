package repchain.inter.cooperation.middleware.service;

import com.rcjava.protos.Peer;

/**
 * @author lhc
 * @version 1.0
 * @className SyncService
 * @date 2021年11月01日 4:18 下午
 * @description 描述
 */
public interface SyncService {
    void saveBlock(Peer.Block block);
}
