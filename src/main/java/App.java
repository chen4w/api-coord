import cn.hutool.http.HttpUtil;

/**
 * @author lhc
 * @version 1.0
 * @className App
 * @date 2021年08月25日 11:43 上午
 * @description 描述
 */
public class App {

    public static void main(String[] args) {
        HttpUtil.createServer(8888)
                .addAction("/", (req, res)->{
                    res.write("Hello Hutool Server");
                })
                .start();
    }
}
