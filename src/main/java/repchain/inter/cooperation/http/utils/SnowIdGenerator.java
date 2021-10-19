package repchain.inter.cooperation.http.utils;

import cn.hutool.core.lang.Singleton;
import cn.hutool.core.lang.Snowflake;

/**
 * @author lhc
 * @version 1.0
 * @className SnowIdGenerator
 * @date 2021年10月13日 9:27 上午
 * @description 描述
 */
public class SnowIdGenerator {

    private static class SingletonHolder {
        public final static Snowflake SNOWFLAKE = Singleton.get(Snowflake.class);
    }

    public static Snowflake getInstance() {
        return SingletonHolder.SNOWFLAKE;
    }

    public static String getId(){
        Snowflake snowflake = getInstance();
        long id = snowflake.nextId();
        return String.valueOf(id);
    }
}
