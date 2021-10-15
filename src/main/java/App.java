import cn.hutool.core.io.file.FileReader;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import model.Header;
import model.yml.InterCo;
import model.yml.Repchain;
import model.yml.RepchainConfig;
import model.yml.Service;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import sync.request.SyncClient;
import utils.SnowIdGenerator;
import utils.YamlUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author lhc
 * @version 1.0
 * @className App
 * @date 2021年08月25日 11:43 上午
 * @description 描述
 */
public class App {

    public static void main(String[] args) {
        try {
            // 获取yml文件中的信息
            RepchainConfig repchainConfig = YamlUtils.repchainConfig;
            List<InterCo> interCoList = repchainConfig.getRepchain().getInterCo();
            InterCo interCo = interCoList.get(0);
            List<Service> services = interCo.getServices();
            Service service = services.get(0);
            Header header = SyncClient.customHeader(service, SnowIdGenerator.getId(), 1, false);
            Db.use().insert(Entity.create("header").parseBean(header));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void test(String str) {
        str = str + "aaa";
    }
}
