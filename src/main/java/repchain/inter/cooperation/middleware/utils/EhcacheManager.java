package repchain.inter.cooperation.middleware.utils;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @author lhc
 * @version 1.0
 * @className EhcacheManager
 * @date 2021年11月01日 3:07 下午
 * @description 描述
 */

public class EhcacheManager {

    private static class ManagerHolder {
        public final static CacheManager CACHE_MANAGER = CacheManager.getInstance();
    }

    public static CacheManager getInstance() {
        return EhcacheManager.ManagerHolder.CACHE_MANAGER;
    }


    /**
     * @return void
     * @author lhc
     * @description // 添加元素
     * @date 2021/11/1 3:15 下午
     * @params [cacheName, key]
     **/
    public static void put(String cacheName, String key, Object value) {
        Cache cache = getInstance().getCache(cacheName);
        cache.put(new Element(key, value));
        cache.flush();
    }

    /**
     * @author lhc
     * @description // 删除元素
     * @date 2021/11/2 9:29 上午
     * @params [cacheName, key]
     * @return void
     **/
    public static void delete(String cacheName, String key) {
        Cache cache = getInstance().getCache(cacheName);
        cache.remove(key);
        cache.flush();
    }

    /**
     * @author lhc
     * @description // 根据key获取value
     * @date 2021/11/1 3:15 下午
     * @params [cacheName, key]
     * @return java.lang.Object
     **/
    public static Object getValue(String cacheName, Object key) {
        try {
            Cache cache = getInstance().getCache(cacheName);
            Object result = cache.get(key).getObjectValue();
            cache.flush();
            return result;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * @author lhc
     * @description // 获取缓存Size
     * @date 2021/11/1 3:21 下午
     * @params [cacheName]
     * @return int
     **/
    public static int getCacheSize(String cacheName) {
        Cache cache = getInstance().getCache(cacheName);
        int size = cache.getSize();
        cache.flush();
        return size;
    }
}
