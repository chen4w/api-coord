package repchain.inter.cooperation.middleware.service;

import repchain.inter.cooperation.middleware.model.Header;


/**
 * @author lhc
 * @version 1.0
 * @className Persistence
 * @date 2021年11月08日 5:01 下午
 * @description 持久化接口
 */
public interface Persistence {


    void saveData(Header header);

    void saveResult(Header header,Object result);

    void saveWithFile();

    Header getOne(String key);
}
