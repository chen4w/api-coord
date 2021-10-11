package single.response;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;

/**
 * @author lhc
 * @version 1.0
 * @className Server
 * @date 2021年10月11日 3:03 下午
 * @description 同步单次请求服务端代码
 */
public class Server {

    /**
     * @author lhc
     * @description // 服务断启动
     * @date 3:11 下午 2021/10/11
     * @params [args]
     * @return void
     **/
    public static void main(String[] args) {
        // 创建简易服务器，端口为8888
        HttpUtil.createServer(8888)
                .addAction("/info", (req, res) -> {
                    String name = req.getParam("name");
                    if (StrUtil.isBlank(name)) {
                        res.write("no name");
                    } else {
                        res.write("Hello " + name);
                    }
                })
                .start();
    }
}
