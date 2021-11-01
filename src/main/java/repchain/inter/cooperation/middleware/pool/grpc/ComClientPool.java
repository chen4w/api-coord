package repchain.inter.cooperation.middleware.pool.grpc;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repchain.inter.cooperation.middleware.model.yml.ComClient;
import repchain.inter.cooperation.middleware.utils.YamlUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lhc
 * @version 1.0
 * @className ComClientPool
 * @date 2021年10月29日 3:37 下午
 * @description 描述
 */
public class ComClientPool {

    private static final Logger logger = LoggerFactory.getLogger(ComClientPool.class);

    public static ConcurrentHashMap<String,GenericObjectPool<ComClientSingle>> poolMap = new ConcurrentHashMap<>();

    /**
     * @author lhc
     * @description // 创建连接池
     * @date 2021/10/29 4:04 下午
     * @params [host, port]
     * @return void
     **/
    private static GenericObjectPool<ComClientSingle> createPool(String host,int port){
        // 连接池的配置，从yml文件中读取
        ComClient comClient = YamlUtils.middleConfig.getMiddleware().getComClient();
        // 连接池创建
        GenericObjectPool<ComClientSingle> objectPool = new GenericObjectPool<>(new ComClientFactory(host, port), comClient);
        // 存入hash
        poolMap.put(host + port, objectPool);
        return objectPool;
    }

    private static ComClientSingle createClient(String host,int port){
        return new ComClientSingle(host, port);
    }

    /**
     * 从连接池获取对象
     */
    public static ComClientSingle borrowObject(String host,int port){
        try {
            // 获取线程池
            GenericObjectPool<ComClientSingle> objectPool = poolMap.get(host + port);
            if (objectPool == null) {
                objectPool = createPool(host, port);
            }
            ComClientSingle clientSingle = objectPool.borrowObject();
            logger.debug("总创建线程数:"+objectPool.getCreatedCount());
            return clientSingle;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        //连接池失败则主动创建
        return createClient(host,port);
    }

    /**
     * 还回连接池获取的对象
     */
    public static void returnObject(ComClientSingle comClientSingle,String host,int port){
        logger.debug("returnObject");
        try {
            // 获取线程池
            GenericObjectPool<ComClientSingle> objectPool = poolMap.get(host + port);
            objectPool.returnObject(comClientSingle);
            logger.debug("总创建线程数，{}",objectPool.getCreatedCount());
        } catch (Exception e) {
            logger.error("线程池异常",e);
            try {
                comClientSingle.shutdown();
            } catch (InterruptedException interruptedException) {
                logger.error("关闭连接异常",interruptedException);
            }
        }
    }
}
