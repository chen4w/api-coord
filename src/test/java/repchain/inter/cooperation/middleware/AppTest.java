package repchain.inter.cooperation.middleware;

import static org.junit.jupiter.api.Assertions.assertTrue;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.Page;
import cn.hutool.db.PageResult;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import repchain.inter.cooperation.middleware.client.ReqOption;
import repchain.inter.cooperation.middleware.model.yml.MiddleConfig;
import repchain.inter.cooperation.middleware.utils.SqliteUtil;
import repchain.inter.cooperation.middleware.utils.YamlUtils;

import java.sql.SQLException;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    /**
     * 测试yml读取
     */
    @Test
    public void testYaml() {
        Yaml yaml = new Yaml(new Constructor(MiddleConfig.class));
        YamlUtils yamlUtils = new YamlUtils();
        String path = yamlUtils.path.substring(0, yamlUtils.path.lastIndexOf("/"));
        FileReader fileReader = new FileReader(path + "/application-middle.yml");
        MiddleConfig middleConfig = yaml.load(fileReader.getInputStream());
        System.out.println(JSONUtil.toJsonPrettyStr(middleConfig));
    }

    /**
     * 测试yml读取
     */
    @Test
    public void testConvertToMap() throws SQLException {
        final Db db = SqliteUtil.getInstance();
        PageResult<Entity> result = db.page(Entity.create("persistence"), new Page(5, 10));
        System.out.println(result.isLast());
    }
}
