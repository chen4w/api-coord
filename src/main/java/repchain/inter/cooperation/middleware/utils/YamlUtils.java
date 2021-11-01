package repchain.inter.cooperation.middleware.utils;

import cn.hutool.core.io.file.FileReader;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import repchain.inter.cooperation.middleware.model.yml.MiddleConfig;

/**
 * @author lhc
 * @version 1.0
 * @className YamlUtils
 * @date 2021年10月12日 11:18 上午
 * @description 描述
 */
public class YamlUtils {

    public static MiddleConfig middleConfig;

    public String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile();

    static {
        Yaml yaml = new Yaml(new Constructor(MiddleConfig.class));
        YamlUtils yamlUtils = new YamlUtils();
        String path = yamlUtils.path.substring(0, yamlUtils.path.lastIndexOf("/"));
        FileReader fileReader = new FileReader(path + "/application-middle.yml");
        middleConfig = yaml.load(fileReader.getInputStream());
    }

    public static MiddleConfig getYamlNowTime() {
        Yaml yaml = new Yaml(new Constructor(MiddleConfig.class));
        YamlUtils yamlUtils = new YamlUtils();
        String path = yamlUtils.path.substring(0, yamlUtils.path.lastIndexOf("/"));
        FileReader fileReader = new FileReader(path + "/application-middle.yml");
        return yaml.load(fileReader.getInputStream());
    }
}
