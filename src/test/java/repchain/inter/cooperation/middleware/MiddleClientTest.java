package repchain.inter.cooperation.middleware;

import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import repchain.inter.cooperation.middleware.client.HttpType;
import repchain.inter.cooperation.middleware.client.MiddlewareClient;
import repchain.inter.cooperation.middleware.client.PerReq;
import repchain.inter.cooperation.middleware.client.ReqOption;
import repchain.inter.cooperation.middleware.model.InterCoResult;
import repchain.inter.cooperation.middleware.utils.GetFileSHA256;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lhc
 * @version 1.0
 * @className MiddleClientTest
 * @date 2021年11月03日 11:29 上午
 * @description 描述
 */
public class MiddleClientTest {

    @Test()
    public void msg() {
        // 构建请求参数
        Map<String, Object> map = new HashMap<>(1);
        map.put("loginName", "12110107bi45jh675g");
        ReqOption option = new ReqOption();
        option.setReqSave(ReqOption.TRUE);
        // 发送请求，并获取返回结果
        InterCoResult result = MiddlewareClient
                // 填写中间件地址及端口号，及超时时间
                .create("http://localhost:8888", 50000)
                // 请求类型，根据接口定义设置
                .setHttpType(HttpType.GET)
                // 中间件中的服务id，根据yml文件配置填写
                .setServiceId("1")
                // 设置访问的url
                .setUrl("/user/sgUser/valid")
                // 设置传输的数据
                .setForm(map)
                // 发送数据
                .msg(option);
        System.out.println(JSONUtil.toJsonPrettyStr(result));
    }

    @Test()
    public void multi() {
        // 1.第一次请求
        Map<String, Object> map = new HashMap<>(1);
        map.put("loginName", "12110107bi45jh675g");
        // 传输设置对象
        ReqOption reqOption = new ReqOption();
        // 设置是否为最后一次请求，默认为true
        reqOption.setIsEnd(ReqOption.FALSE);
        InterCoResult result = MiddlewareClient
                // 填写中间件地址及端口号，及超时时间
                .create("http://localhost:8888", 50000)
                // 请求类型，根据接口定义设置
                .setHttpType(HttpType.GET)
                // 中间件中的服务id，根据yml文件配置填写
                .setServiceId("1")
                // 设置访问的url
                .setUrl("/user/sgUser/valid")
                // 设置传输的数据
                .setForm(map)
                // 发送数据
                .msg(reqOption);
        System.out.println(JSONUtil.toJsonPrettyStr(result));
        // 2. 第二次请求
        reqOption = new ReqOption();
        // 由于是同一次请求，所以请求id需要一致，获取上一次请求id
        reqOption.setCid(result.getCid());
        // 设置请求序号，默认为1
        reqOption.setSeq(2);
        // 设置是否为最后一次请求，默认为true
        reqOption.setIsEnd(ReqOption.FALSE);
        // 设置请求参数
        map = new HashMap<>(1);
        map.put("loginName", "yewu");
        result = MiddlewareClient
                // 填写中间件地址及端口号，及超时时间
                .create("http://localhost:8888", 50000)
                // 请求类型，根据接口定义设置
                .setHttpType(HttpType.GET)
                // 中间件中的服务id，根据yml文件配置填写
                .setServiceId("1")
                // 设置访问的url
                .setUrl("/user/sgUser/valid")
                // 设置传输的数据
                .setForm(map)
                // 发送数据
                .msg(reqOption);
        System.out.println(JSONUtil.toJsonPrettyStr(reqOption));
        System.out.println(JSONUtil.toJsonPrettyStr(result));
        // 3. 第三次请求
        reqOption = new ReqOption();
        // 由于是同一次请求，所以请求id需要一致，获取上一次请求id
        reqOption.setCid(result.getCid());
        // 设置请求序号，默认为1
        reqOption.setSeq(3);
        // 设置请求参数
        map = new HashMap<>(1);
        map.put("loginName", "test");
        result = MiddlewareClient
                // 填写中间件地址及端口号，及超时时间
                .create("http://localhost:8888", 50000)
                // 请求类型，根据接口定义设置
                .setHttpType(HttpType.GET)
                // 中间件中的服务id，根据yml文件配置填写
                .setServiceId("1")
                // 设置访问的url
                .setUrl("/user/sgUser/valid")
                // 设置传输的数据
                .setForm(map)
                // 发送数据
                .msg(reqOption);
        System.out.println(JSONUtil.toJsonPrettyStr(reqOption));
        System.out.println(JSONUtil.toJsonPrettyStr(result));
    }

    @Test
    public void file() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("loginName", "12110107bi45jh675g");
        InterCoResult result = MiddlewareClient
                // 填写中间件地址及端口号，及超时时间
                .create("http://localhost:8888", 50000)
                // 请求类型，根据接口定义设置
                .setHttpType(HttpType.GET)
                // 中间件中的服务id，根据yml文件配置填写
                .setServiceId("1")
                // 设置访问的url
                .setUrl("/user/sgUser/valid")
                // 设置传输的数据
                .setForm(map)
                // 设置文件字段
                .setFileField("file")
                // file
                .setFile(new File("/Users/lhc/Downloads/canal.adapter-1.1.5.tar.gz"))
                // 发送数据
                .sendFile();
//        System.out.println("upload");
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("file", FileUtil.file("/Users/lhc/Downloads/scientist_pub.sql"));
////        paramMap.put("file", FileUtil.file("/Users/lhc/Downloads/IMG_0148.PNG"));
//        System.out.println("upload");
//        String result= HttpUtil.post("http://localhost:8888/file", paramMap);

//        String result = HttpRequest.post("http://localhost:8888/file").timeout(300000).form("file",FileUtil.file("/Users/lhc/Downloads/scientist_pub.sql")).setChunkedStreamingMode(2048).execute().body();
//        String result = HttpRequest.post("http://localhost:8888/file").timeout(30000).form("file",FileUtil.file("/Users/lhc/Downloads/apache-jmeter-5.4.1.tgz")).setChunkedStreamingMode(2048).execute().body();
//        HttpRequest.post("http://localhost:8888/file").timeout(30000).form("file",FileUtil.file("/Users/lhc/Downloads/coord.sql")).setChunkedStreamingMode(2048).execute().body();
        System.out.println(JSONUtil.toJsonPrettyStr(result));
    }

    @Test
    public void test256() {
        File file1 = new File("/Volumes/DATA/ideaProject/api-coord/tmp/file/c4139f94cc794b9390d8662c7c9a4b811458354018019553280");
        File file2 = new File("/Users/lhc/Downloads/canal.adapter-1.1.5.tar.gz");
        boolean flag = GetFileSHA256.getFileSha256(file1).equals(GetFileSHA256.getFileSha256(file2));
        System.out.println(flag);
    }

    @Test()
    public void login() {

        Map<String, Object> map = new HashMap<>(1);
//        map.put("loginName", "12110107bi45jh675g");
        ReqOption option = new ReqOption();
        option.setReqSave(ReqOption.TRUE);
        // 发送请求，并获取返回结果
        InterCoResult result = MiddlewareClient
                // 填写中间件地址及端口号，及超时时间
                .create("http://localhost:8888", 50000)
                // 请求类型，根据接口定义设置
                .setHttpType(HttpType.GET)
                // 中间件中的服务id，根据yml文件配置填写
                .setServiceId("1")
                // 设置访问的url
                .setUrl("/user/atmo/now")
                // 设置传输的数据
//                .setForm(map)
                .setHeader("X-Access-Token", "ZZNau4dNSIzVLKxMRVLFlw")
                // 发送数据
                .msg(option);
        System.out.println(JSONUtil.toJsonPrettyStr(result));

    }

    @Test()
    public void upload() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("editor", "Jack");
        ReqOption reqOption = new ReqOption();
        reqOption.setReqSave(ReqOption.TRUE);
        reqOption.setFileSave(ReqOption.TRUE);
        // 发送请求，并获取返回结果
        InterCoResult result = MiddlewareClient
                // 填写中间件地址及端口号，及超时时间
                .create("http://localhost:8888", 5000000)
                // 请求类型，根据接口定义设置
                .setHttpType(HttpType.POST)
                // 中间件中的服务id，根据yml文件配置填写
                .setServiceId("1")
                // 设置访问的url
                .setUrl("/user/test/upload")
                // 设置传输的数据
                .setForm(map)
                .setFileField("file")
                .setFile(new File("/Users/lhc/Downloads/Docker.dmg"))
                // 发送数据
                .sendFile(reqOption);
        System.out.println(JSONUtil.toJsonPrettyStr(result));
    }

    @Test()
    public void download() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("username", "Jack");
        ReqOption reqOption = new ReqOption();
        reqOption.setReqSave(ReqOption.TRUE);
        reqOption.setFileSave(ReqOption.TRUE);
        // 发送请求，并获取返回结果
        InterCoResult result = MiddlewareClient
                // 填写中间件地址及端口号，及超时时间
                .create("http://localhost:8888", 500000)
                // 请求类型，根据接口定义设置
                .setHttpType(HttpType.GET)
                // 中间件中的服务id，根据yml文件配置填写
                .setServiceId("1")
                // 设置访问的url
                .setUrl("/user/test/download")
                // 设置传输的数据
                .setForm(map)
                // 发送数据
                .download(reqOption);
        System.out.println(JSONUtil.toJsonPrettyStr(result));
    }

    @Test()
    public void async() {
        // 构建请求参数
        Map<String, Object> map = new HashMap<>(1);
        map.put("pageSize", 1);
        map.put("pageNo", 10);
        // 设置请求配置对象
        ReqOption option = new ReqOption();
        // 设置为异步请求
        option.setSync(ReqOption.FALSE);
        // 设置异步请求应答接口地址
        option.setCallbackUrl("/test");
        // 异步请求结束标志设置为false
        option.setIsEnd(ReqOption.FALSE);
        // 设置异步请求接口应答地址请求类型
        option.setCallbackMethod(HttpType.GET.toString());
        // 发送请求，并获取返回结果
        InterCoResult result = MiddlewareClient
                // 填写中间件地址及端口号，及超时时间
                .create("http://localhost:8888", 50000)
                // 请求类型，根据接口定义设置
                .setHttpType(HttpType.GET)
                // 中间件中的服务id，根据yml文件配置填写
                .setServiceId("1")
                // 设置访问的url
                .setUrl("/user/test/async")
                // 设置传输的数据
                .setForm(map)
                // 发送数据
                .msg(option);
        System.out.println(JSONUtil.toJsonPrettyStr(result));
    }

    @Test()
    public void asyncUpload() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("editor", "Jack");
        ReqOption option = new ReqOption();
        // 设置为异步请求
        option.setSync(ReqOption.FALSE);
        // 设置异步请求应答接口地址
        option.setCallbackUrl("/test");
        // 设置异步请求接口应答地址请求类型
        option.setCallbackMethod(HttpType.GET.toString());
        // 发送请求，并获取返回结果
        InterCoResult result = MiddlewareClient
                // 填写中间件地址及端口号，及超时时间
                .create("http://localhost:8888", 500000)
                // 请求类型，根据接口定义设置
                .setHttpType(HttpType.GET)
                // 中间件中的服务id，根据yml文件配置填写
                .setServiceId("1")
                // 设置访问的url
                .setUrl("/user/test/asyncUpload")
                // 设置传输的数据
                .setForm(map)
                .setFileField("file")
                .setFile(new File("/Users/lhc/Downloads/apache-tomcat-9.0.55.tar.gz"))
                // 发送数据
                .sendFile(option);
        System.out.println(JSONUtil.toJsonPrettyStr(result));
    }

    @Test()
    public void asyncDownload1() {
        // 构建请求参数
        Map<String, Object> map = new HashMap<>(1);
        map.put("editor", "Jack");
        map.put("file", "maven");
        // 设置请求配置对象
        ReqOption option = new ReqOption();
        // 设置为异步请求
        option.setSync(ReqOption.FALSE);
        // 设置异步请求应答接口地址
        option.setCallbackUrl("/test");
        // 设置异步请求接口应答地址请求类型
        option.setCallbackMethod(HttpType.GET.toString());
        // 发送请求，并获取返回结果
        InterCoResult result = MiddlewareClient
                // 填写中间件地址及端口号，及超时时间
                .create("http://localhost:8888", 50000)
                // 请求类型，根据接口定义设置
                .setHttpType(HttpType.GET)
                // 中间件中的服务id，根据yml文件配置填写
                .setServiceId("1")
                // 设置访问的url
                .setUrl("/user/test/asyncDownload")
                // 设置传输的数据
                .setForm(map)
                // 发送数据
                .msg(option);
        System.out.println(JSONUtil.toJsonPrettyStr(result));
    }

    @Test()
    public void getData() {
        InterCoResult result = MiddlewareClient
                // 填写中间件地址及端口号，及超时时间
                .create("http://localhost:8888", 50000)
                .data(PerReq.builder().cid("xxx").pageNo(0).pageSize(10).build());
        System.out.println(JSONUtil.toJsonPrettyStr(result));
    }
}
