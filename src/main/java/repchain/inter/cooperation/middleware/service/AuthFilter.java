package repchain.inter.cooperation.middleware.service;

import repchain.inter.cooperation.middleware.model.Header;

/**
 * @author lhc
 * @version 1.0
 * @className AuthFilter
 * @date 2021年10月27日 9:16 上午
 * @description 描述
 */
public interface AuthFilter {
    boolean validAuth(Header header, String algo_sign,boolean isReq);
}
