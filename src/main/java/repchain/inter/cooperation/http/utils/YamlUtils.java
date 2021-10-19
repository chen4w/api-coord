package repchain.inter.cooperation.http.utils;

import cn.hutool.core.io.file.FileReader;
import repchain.inter.cooperation.http.model.yml.Repchain;
import repchain.inter.cooperation.http.model.yml.RepchainConfig;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/**
 * @author lhc
 * @version 1.0
 * @className YamlUtils
 * @date 2021年10月12日 11:18 上午
 * @description 描述
 */
public class YamlUtils {

    public static RepchainConfig repchainConfig;

    public String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile();

    static {
        Yaml yaml = new Yaml(new Constructor(RepchainConfig.class));
        YamlUtils yamlUtils = new YamlUtils();
        String path = yamlUtils.path.substring(0, yamlUtils.path.lastIndexOf("/"));
        FileReader fileReader = new FileReader(path + "/application.yml");
        repchainConfig = yaml.load(fileReader.getInputStream());
    }
}