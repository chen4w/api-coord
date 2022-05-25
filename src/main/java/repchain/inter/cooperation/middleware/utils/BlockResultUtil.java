package repchain.inter.cooperation.middleware.utils;

import com.google.protobuf.ByteString;
import cn.hutool.json.JSONUtil;
import com.rcjava.protos.Peer;
//import com.rcjava.util.OperLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author lhc
 * @version 1.0
 * @className BlockResultUtil
 * @date 2021年07月08日 1:23 下午
 * @description 描述
 */
public class BlockResultUtil {

    public static Map<String, Object> toMap(ByteString ol) {
        String order = ol.toStringUtf8();
        return JSONUtil.parseObj(order);
    }

    public static <T> T toBean(ByteString ol, Class<T> clazz) {
        String order =  ol.toStringUtf8();
        return JSONUtil.toBean(order, clazz);
    }

    public static Double toDouble(ByteString ol) {
        String value =  ol.toStringUtf8();
        return Double.parseDouble(value);
    }


    public static Integer toInteger(ByteString ol) {
        String value =  ol.toStringUtf8();
        return Integer.parseInt(value);
    }

//    public static String getCommonId(ByteString ol, String type) {
//        String key = ol.getKey();
//        String endValue = key.split(type)[0];
//        String[] ids = endValue.split("_");
//        return ids[0];
//    }
}
