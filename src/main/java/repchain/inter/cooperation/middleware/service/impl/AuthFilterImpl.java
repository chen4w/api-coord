package repchain.inter.cooperation.middleware.service.impl;

import repchain.inter.cooperation.middleware.model.Header;
import repchain.inter.cooperation.middleware.service.AuthFilter;
import repchain.inter.cooperation.middleware.utils.TransTools;

/**
 * @author lhc
 * @version 1.0
 * @className AuthFilterImpl
 * @date 2021年10月27日 9:51 上午
 * @description 描述
 */
public class AuthFilterImpl implements AuthFilter {

    @Override
    public boolean validAuth(Header header, String algo_sign,boolean isReq) {
        return TransTools.validAuth(header,algo_sign,isReq);
    }
}
