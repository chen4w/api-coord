package repchain.inter.cooperation.middleware;

import org.junit.jupiter.api.Test;
import repchain.inter.cooperation.middleware.constant.EhCacheConstant;
import repchain.inter.cooperation.middleware.utils.MyCacheManager;

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
        MyCacheManager.put("RepChain", "1", "test1");
        MyCacheManager.put("RepChain", "2", "test2");
        MyCacheManager.put("RepChain", "3", "test3");
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
//        Object value = MyCacheManager.getValue("RepChain", "3");
//        System.out.println(value);
    }

    /**
     * @author lhc
     * @description // 计算缓存数量
     * @date 2021/11/2 10:02 上午
     * @params []
     * @return void
     **/
    @Test
    public void CountCache(){
//        Object value = MyCacheManager.getCacheSize(EhCacheConstant.REQ_ACK_PROOF);
//        System.out.println("Number is :"+value);
    }
}
