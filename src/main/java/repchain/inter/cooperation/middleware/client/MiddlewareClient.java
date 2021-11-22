package repchain.inter.cooperation.middleware.client;

import cn.hutool.core.convert.Convert;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import repchain.inter.cooperation.middleware.exception.ServiceException;
import repchain.inter.cooperation.middleware.model.InterCoResult;
import repchain.inter.cooperation.middleware.utils.GetFileSHA256;

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
    private Map<String, Object> headers = new HashMap<>();

    public static MiddlewareClient create(String host, int timeout) {
        return new MiddlewareClient(host, timeout);
    }

    public MiddlewareClient setUrl(String url) {
        this.url = url;
        return this;
    }

    public MiddlewareClient setHeader(String key, String value) {
        this.headers.put(key, value);
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
        this.filepath = file.getAbsolutePath();
        return this;
    }

    public MiddlewareClient(String host, int timeout) {
        this.host = host;
        this.timeout = timeout;
    }


    public InterCoResult msg() {
        ReqOption reqOption = new ReqOption();
        return getInterCoResult(serviceId, url, httpType, form, reqOption,"/msg",headers);
    }

    private InterCoResult getInterCoResult(String serviceId, String url, HttpType httpType, Map<String, Object> map, ReqOption reqOption,String middleUrl,Map<String,Object> headers) {
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
        if (!headers.isEmpty()) {
            reqOption.setHeaders(JSONUtil.toJsonStr(headers));
        }
        map.put("cid", reqOption.getCid());
        reqOption.setData(JSONUtil.toJsonStr(map));
        Map<String, Object> form = Convert.toMap(String.class, Object.class, reqOption);
        String resultStr = HttpRequest.post(host + middleUrl)
                .form(form)
                .timeout(this.timeout)
                .execute().body();
        return JSONUtil.toBean(resultStr, InterCoResult.class);
    }


    public InterCoResult msg(ReqOption reqOption) {
        return getInterCoResult(serviceId, url, httpType, form, reqOption,"/msg",headers);
    }

    public InterCoResult sendFile() {
        if (this.file==null) {
            throw new ServiceException("file can not be null");
        }
        ReqOption reqOption = new ReqOption();
        reqOption.setFilepath(this.filepath);
        reqOption.setFileHash(GetFileSHA256.getFileSha256(this.file));
        return getInterCoResult(serviceId, url, httpType, this.form, reqOption,"/file",headers);
    }

    public InterCoResult sendFile(ReqOption reqOption) {
        if (this.file==null) {
            throw new ServiceException("file can not be null");
        }
        reqOption.setFilepath(this.filepath);
        reqOption.setFileHash(GetFileSHA256.getFileSha256(this.file));
        return getInterCoResult(serviceId, url, httpType, this.form, reqOption,"/file",headers);
    }

    public InterCoResult download() {
        ReqOption reqOption = new ReqOption();
        return getInterCoResult(serviceId, url, httpType, this.form, reqOption,"/download",headers);
    }

    public InterCoResult download(ReqOption reqOption) {
        return getInterCoResult(serviceId, url, httpType, this.form, reqOption,"/download",headers);
    }
}
