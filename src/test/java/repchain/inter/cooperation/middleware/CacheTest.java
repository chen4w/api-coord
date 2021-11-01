package repchain.inter.cooperation.middleware;

import org.junit.jupiter.api.Test;
import repchain.inter.cooperation.middleware.utils.EhcacheManager;

/**
 * @author lhc
 * @version 1.0
 * @className CacheTest
 * @date 2021年11月01日 3:26 下午
 * @description 缓存测试用例
 */
public class CacheTest {

    /**
     * @author lhc
     * @description // 缓存写操作
     * @date 2021/11/1 4:15 下午
     * @params []
     * @return void
     **/
    @Test
    public void CacheWrite() {
        System.setProperty(net.sf.ehcache.CacheManager.ENABLE_SHUTDOWN_HOOK_PROPERTY,"true");
        EhcacheManager.put("RepChain", "1", "test1");
        EhcacheManager.put("RepChain", "2", "test2");
        EhcacheManager.put("RepChain", "3", "test3");
    }

    /**
     * @author lhc
     * @description // 缓存读操作
     * @date 2021/11/1 4:15 下午
     * @params []
     * @return void
     **/
    @Test
    public void CacheRead() {
        System.setProperty(net.sf.ehcache.CacheManager.ENABLE_SHUTDOWN_HOOK_PROPERTY,"true");
        Object value = EhcacheManager.getValue("RepChain", "3");
        System.out.println(value);
    }
}
