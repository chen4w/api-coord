package repchain.inter.cooperation.middleware.utils;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.middleware.constant.EhCacheConstant;

import java.sql.SQLException;


/**
 * @author lhc
 * @version 1.0
 * @className EhcacheManager
 * @date 2021年11月01日 3:07 下午
 * @description 描述
 */

public class MyCacheManager {

    private static final Logger logger = LoggerFactory.getLogger(MyCacheManager.class);

    private static final Db db = SqliteUtil.getInstance();

    /**
     * @return void
     * @author lhc
     * @description // 添加元素
     * @date 2021/11/1 3:15 下午
     * @params [cacheName, key]
     **/
    public static void put(String cacheName, String key, Object value) {
        Entity entity;
        try {
            entity = db.queryOne("SELECT value FROM " + cacheName + " where key = ?", key);
            if (entity==null||entity.isEmpty()) {
                db.insert(
                        Entity.create(cacheName)
                                .set("key", key)
                                .set("value", JSONUtil.toJsonStr(value))
                );
            } else {
                db.update(
                        Entity.create().set("value", JSONUtil.toJsonStr(value)),
                        Entity.create(cacheName).set("key", key)
                );
            }
        } catch (Exception throwable) {
            logger.error(throwable.getMessage(), throwable);
        }

    }

    /**
     * @author lhc
     * @description // 删除元素
     * @date 2021/11/2 9:29 上午
     * @params [cacheName, key]
     * @return void
     **/
    public static void delete(String cacheName, String key) {
        try {
            db.del(
                    Entity.create(cacheName).set("key", key)
            );
        } catch (Exception throwable) {
            logger.error(throwable.getMessage(), throwable);
        }
    }

    /**
     * @author lhc
     * @description // 根据key获取value
     * @date 2021/11/1 3:15 下午
     * @params [cacheName, key]
     * @return java.lang.Object
     **/
    public static <T> T getValue(String cacheName, Object key,Class<T> target) {
        try {
            Entity entity = db.queryOne("SELECT value FROM " + cacheName + " where key = ?", key);
            if (entity.isEmpty()) {
                return null;
            }
            return JSONUtil.toBean((String) entity.get("value"),target);
        } catch (Exception throwable) {
            logger.error(throwable.getMessage(), throwable);
        }
        return null;
    }

    public static Long getHeight() {
        Entity entity = null;
        try {
            entity = db.queryOne("SELECT value FROM Repchain  where key = ?", EhCacheConstant.BLOCK);
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage(), throwable);
        }
        if (entity == null || entity.isEmpty()) {
            return null;
        } else {
            return Long.valueOf((String)entity.get("value"));
        }
    }

    public static void putHeight(Long height) {
        Entity entity;
        try {
            entity = db.queryOne("SELECT value FROM Repchain where key = ?", EhCacheConstant.BLOCK);
            if (entity==null||entity.isEmpty()) {
                db.insert(
                        Entity.create("Repchain")
                                .set("key", EhCacheConstant.BLOCK)
                                .set("value",height)
                );
            } else {
                db.update(
                        Entity.create().set("value", height),
                        Entity.create("Repchain").set("key", EhCacheConstant.BLOCK)
                );
            }
        } catch (Exception throwable) {
            logger.error(throwable.getMessage(), throwable);
        }

    }
}
