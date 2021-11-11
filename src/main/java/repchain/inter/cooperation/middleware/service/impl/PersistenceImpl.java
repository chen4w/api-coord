package repchain.inter.cooperation.middleware.service.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.DSFactory;
import cn.hutool.json.JSONUtil;
import cn.hutool.setting.Setting;
import repchain.inter.cooperation.middleware.model.yml.MyDatasource;
import repchain.inter.cooperation.middleware.service.Persistence;
import repchain.inter.cooperation.middleware.utils.SqliteUtil;
import repchain.inter.cooperation.middleware.utils.YamlUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * @author lhc
 * @version 1.0
 * @className PersistenceImpl
 * @date 2021年11月08日 5:04 下午
 * @description 描述
 */
public class PersistenceImpl implements Persistence {

    private final Db db = SqliteUtil.getInstance();


    @Override
    public void init() {
    }

    @Override
    public void save() {

    }

    @Override
    public void saveWithFile() {

    }

    @Override
    public List<Entity> list() {
        try {
            List<Entity>list = db.findAll("datasource_config");
            System.out.println(JSONUtil.toJsonPrettyStr(list));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void getOne(String key) {

    }
}
