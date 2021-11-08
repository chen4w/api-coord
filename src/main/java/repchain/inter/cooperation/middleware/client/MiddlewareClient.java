package repchain.inter.cooperation.middleware.client;

import cn.hutool.core.convert.Convert;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import repchain.inter.cooperation.middleware.model.InterCoResult;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lhc
 * @version 1.0
 * @className MiddlewareClient
 * @date 2021年10月28日 4:12 下午
 * @description 描述
 */
public class MiddlewareClient {

    private String host;
    private int timeout;
    private String url;
    private HttpType httpType;
    private String serviceId;
    private Map<String, Object> form;
    private String filepath;
    private File file;
    private ReqOption reqOption;

    public static MiddlewareClient create(String host, int timeout) {
        return new MiddlewareClient(host, timeout);
    }

    public MiddlewareClient setUrl(String url) {
        this.url = url;
        return this;
    }

    public MiddlewareClient setHttpType(HttpType httpType) {
        this.httpType = httpType;
        return this;
    }

    public MiddlewareClient setServiceId(String serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public MiddlewareClient setForm(Map<String, Object> form) {
        this.form = form;
        return this;
    }

    public MiddlewareClient setFilepath(String filepath) {
        this.filepath = filepath;
        return this;
    }

    public MiddlewareClient setFile(File file) {
        this.file = file;
        return this;
    }

    public MiddlewareClient(String host, int timeout) {
        this.host = host;
        this.timeout = timeout;
    }

    public MiddlewareClient setReqOption(ReqOption reqOption) {
        this.reqOption = reqOption;
        return this;
    }

    public InterCoResult msg() {
        ReqOption reqOption = new ReqOption();
        return getInterCoResult(serviceId, url, httpType, form, reqOption);
    }

    private InterCoResult getInterCoResult(String serviceId, String url, HttpType httpType, Map<String, Object> map, ReqOption reqOption) {
        if (serviceId == null) {
            throw new NullPointerException("serviceId can not be null");
        }
        if (url == null) {
            throw new NullPointerException("url can not be null");
        }
        if (httpType == null) {
            throw new NullPointerException("httpType can not be null");
        }
        reqOption.setServiceId(serviceId);
        reqOption.setUrl(url);
        reqOption.setMethod(httpType.toString());
        if (map == null) {
            map = new HashMap<>(1);
        }
        map.put("cid", reqOption.getCid());
        reqOption.setData(JSONUtil.toJsonStr(map));
        Map<String, Object> form = Convert.toMap(String.class, Object.class, reqOption);
        String resultStr = HttpRequest.post(host + "/msg")
                .form(form)
                .timeout(this.timeout)
                .execute().body();
        return JSONUtil.toBean(resultStr, InterCoResult.class);
    }


    public InterCoResult msg(ReqOption reqOption) {
        return getInterCoResult(serviceId, url, httpType, form, reqOption);
    }

}
