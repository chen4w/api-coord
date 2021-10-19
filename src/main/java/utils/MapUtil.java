package utils;

import java.util.Map;

/**
 * @author lhc
 * @version 1.0
 * @className MapUtil
 * @date 2021年10月18日 3:07 下午
 * @description 比较两个map的值是否相等
 */
public class MapUtil {


    private static boolean mapCompare(Map<Object,Object> source, Map<Object,Object> target) {
        boolean isChange = false;
        for (Map.Entry<Object, Object> entry1 : source.entrySet()) {
            String m1value = entry1.getValue() == null ? "" : (String) entry1.getValue();
            String m2value = target.get(entry1.getKey()) == null ? "" : (String) target.get(entry1.getKey());
            if (!m1value.equals(m2value)) {
                isChange = true;
            }
            System.out.println(isChange);
        }
        return isChange;
    }
}
