package repchain.inter.cooperation.middleware.client;

import cn.hutool.core.convert.Convert;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import repchain.inter.cooperation.middleware.model.InterCoResult;

import java.util.Map;

/**
 * @author lhc
 * @version 1.0
 * @className MiddlewareClient
 * @date 2021年10月28日 4:12 下午
 * @description 描述
 */
public class MiddlewareClient {

    private String middlewareUrl;
    private int timeout;

    public MiddlewareClient(String middlewareUrl,int timeout) {
        this.middlewareUrl = middlewareUrl;
        this.timeout = timeout;
    }

    public InterCoResult msg(String serviceId, String url, HttpType httpType, Map<String, Object> map) {
        ReqOption reqOption = new ReqOption();
        return getInterCoResult(serviceId, url, httpType, map, reqOption);
    }

    private InterCoResult getInterCoResult(String serviceId, String url, HttpType httpType, Map<String, Object> map, ReqOption reqOption) {
        reqOption.setServiceId(serviceId);
        reqOption.setUrl(url);
        reqOption.setMethod(httpType.toString());
        map.put("cid", reqOption.getCid());
        reqOption.setData(JSONUtil.toJsonStr(map));
        Map<String,Object> form = Convert.toMap(String.class, Object.class, reqOption);
        String resultStr = HttpRequest.post(middlewareUrl+"/msg")
                .form(form)
                .timeout(this.timeout)
                .execute().body();
        return JSONUtil.toBean(resultStr, InterCoResult.class);
    }


    public InterCoResult msg(String serviceId, String url, HttpType httpType, Map<String, Object> map, ReqOption reqOption) {
        return getInterCoResult(serviceId, url, httpType, map, reqOption);
    }

}
