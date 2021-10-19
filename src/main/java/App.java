import repchain.inter.cooperation.http.model.yml.InterCo;
import repchain.inter.cooperation.http.model.yml.RepchainConfig;
import repchain.inter.cooperation.http.model.yml.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lhc
 * @version 1.0
 * @className App
 * @date 2021年08月25日 11:43 上午
 * @description 描述
 */
public class App {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(App.class);
        logger.info("Hello World");
    }

    public static void test(String str) {
        str = str + "aaa";
    }
}
