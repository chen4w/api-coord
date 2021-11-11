package repchain.inter.cooperation.middleware.service.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.json.JSONUtil;
import repchain.inter.cooperation.middleware.model.Header;
import repchain.inter.cooperation.middleware.service.Persistence;
import repchain.inter.cooperation.middleware.utils.SqliteUtil;

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
    public void saveData(Header header) {

    }

    @Override
    public void saveResult(Header header, Object result) {

    }

    @Override
    public void saveWithFile() {

    }


    @Override
    public void getOne(String key) {

    }
}
