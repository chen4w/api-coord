package repchain.inter.cooperation.middleware.service;

import java.util.List;

/**
 * @author lhc
 * @version 1.0
 * @className Persistence
 * @date 2021年11月08日 5:01 下午
 * @description 持久化接口
 */
public interface Persistence {

    void init();

    void save();

    void saveWithFile();

    List list();

    void getOne(String key);
}
