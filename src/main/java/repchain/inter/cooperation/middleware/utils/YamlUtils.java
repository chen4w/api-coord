package repchain.inter.cooperation.middleware.utils;

import cn.hutool.core.io.file.FileReader;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import repchain.inter.cooperation.middleware.model.yml.MyDatasource;
import repchain.inter.cooperation.middleware.model.yml.MiddleConfig;
import repchain.inter.cooperation.middleware.model.yml.RepChain;
import repchain.inter.cooperation.middleware.model.yml.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author lhc
 * @version 1.0
 * @className YamlUtils
 * @date 2021年10月12日 11:18 上午
 * @description 描述
 */
public class YamlUtils {

    public static MiddleConfig middleConfig;
    public static String jarPath;

    public String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile();

    static {
        Yaml yaml = new Yaml(new Constructor(MiddleConfig.class));
        YamlUtils yamlUtils = new YamlUtils();
        jarPath = yamlUtils.path.substring(0, yamlUtils.path.lastIndexOf("/"));
        FileReader fileReader = new FileReader(jarPath + "/application-middle.yml");
        middleConfig = yaml.load(fileReader.getInputStream());
    }

    public static MiddleConfig getYamlNowTime() {
        Yaml yaml = new Yaml(new Constructor(MiddleConfig.class));
        YamlUtils yamlUtils = new YamlUtils();
        String path = yamlUtils.path.substring(0, yamlUtils.path.lastIndexOf("/"));
        FileReader fileReader = new FileReader(path + "/application-middle.yml");
        return yaml.load(fileReader.getInputStream());
    }

    public static Service getService(String serviceId) {
        List<Service> list = middleConfig.getRepchain().getServices();
        Optional<Service> opsService = list.stream().filter(service -> serviceId.equals(service.getServiceId())).findAny();
        return opsService.orElse(null);
    }

    public static String getFromCert(String id) {
        List<Service> list = middleConfig.getRepchain().getServices();
        Optional<Service> opsService = list.stream().filter(service -> id.equals(service.getE_from())).findAny();
        return opsService.map(Service::handleFromCert).orElse(null);
    }

    public static String getToCert(String id) {
        List<Service> list = middleConfig.getRepchain().getServices();
        Optional<Service> opsService = list.stream().filter(service -> id.equals(service.getE_to())).findAny();
        return opsService.map(Service::handleToCert).orElse(null);
    }

    public static MyDatasource getDatasource() {
        MyDatasource datasource = middleConfig.getDatasource();
        String url = datasource.getUrl();
        if (url.startsWith("jdbc:sqlite:")) {
            url = url.replace("jdbc:sqlite:", "");
            url = "jdbc:sqlite:"+jarPath + "/" + url;
            datasource.setUrl(url);
        }
        return datasource;
    }

    public static RepChain getRepchain() {
        return middleConfig.getRepchain();
    }
}
