package repchain.inter.cooperation.middleware.utils;

import cn.hutool.db.Db;
import cn.hutool.db.ds.DSFactory;
import cn.hutool.setting.Setting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.middleware.model.yml.MyDatasource;

/**
 * @author lhc
 * @version 1.0
 * @className SqliteUtil
 * @date 2021年11月11日 11:25 上午
 * @description 描述
 */
public class SqliteUtil {

    private static final Logger logger = LoggerFactory.getLogger(MyCacheManager.class);

    private static class ManagerHolder {
        public final static Db DB = getInstance();

        public static Db getInstance() {
            Setting setting = new Setting();
            MyDatasource myDatasource = YamlUtils.getDatasource();
            setting.put("url", myDatasource.getUrl());
            setting.put("pass", myDatasource.getPass());
            setting.put("user", myDatasource.getUser());
            setting.put("driver", myDatasource.getDriver());
            setting.put("showSql", myDatasource.getShowSql());
            setting.put("formatSql", myDatasource.getFormatSql());
            setting.put("showParams", myDatasource.getShowParams());
            setting.put("sqlLevel", myDatasource.getSqlLevel());
            return Db.use(DSFactory.create(setting).getDataSource());
        }
    }

    public static Db getInstance() {
        return SqliteUtil.ManagerHolder.DB;
    }

}
