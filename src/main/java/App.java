import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import model.Header;
import model.yml.InterCo;
import model.yml.RepchainConfig;
import model.yml.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.SnowIdGenerator;
import sync.single.request.SyncClient;
import utils.YamlUtils;

import java.sql.SQLException;
import java.util.List;

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
