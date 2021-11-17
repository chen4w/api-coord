package repchain.inter.cooperation.middleware.service;

import cn.hutool.db.Entity;
import repchain.inter.cooperation.middleware.model.Header;

import java.sql.SQLException;
import java.util.List;


/**
 * @author lhc
 * @version 1.0
 * @className Persistence
 * @date 2021年11月08日 5:01 下午
 * @description 持久化接口
 */
public interface Persistence {


    void saveData(String cid,Header header,Object result,String sendFile,String downloadFile) throws SQLException;

    List<Entity> get(String key) throws SQLException;
}
