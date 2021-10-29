package repchain.inter.cooperation.middleware;

import static org.junit.jupiter.api.Assertions.assertTrue;

import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import repchain.inter.cooperation.middleware.model.yml.RepchainConfig;
import repchain.inter.cooperation.middleware.utils.YamlUtils;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    /**
     * 测试yml读取
     */
    @Test
    public void testYaml(){
        RepchainConfig repchainConfig = YamlUtils.getYamlNowTime();
        System.out.println(JSONUtil.toJsonPrettyStr(repchainConfig));
    }
}
