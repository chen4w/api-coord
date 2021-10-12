import cn.hutool.core.io.file.FileReader;
import cn.hutool.http.HttpUtil;
import model.yml.Repchain;
import model.yml.RepchainConfig;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import utils.YamlUtils;

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
        System.out.println(YamlUtils.repchainConfig);
    }
}
