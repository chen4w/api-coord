package repchain.inter.cooperation.middleware.utils;

/**
 * @author lhc
 * @version 1.0
 * @className KeyUtils
 * @date 2021年09月30日 11:05 上午
 * @description 描述
 */
public class KeyUtils {

    public static String getChainCode(String key) {
        String[] arr = key.split("_");
        return arr[1];
    }

    public static boolean startsWith(String key,String prefix) {
        String[] arr = key.split("_");
        String chainCode = arr[0] + "_" + arr[1] + "_";
        String prefixStr = key.replace(chainCode, "");
        return prefixStr.startsWith(prefix);
    }
}