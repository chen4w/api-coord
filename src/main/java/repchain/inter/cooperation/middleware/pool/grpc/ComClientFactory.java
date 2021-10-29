package repchain.inter.cooperation.middleware.pool.grpc;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @author lhc
 * @version 1.0
 * @className ComClientFactory
 * @date 2021年10月29日 3:22 下午
 * @description grpc客户端工厂
 */
public class ComClientFactory extends BasePooledObjectFactory<ComClientSingle> {

    private String host;

    private int port;

    public ComClientFactory(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * @author lhc
     * @description // 线程池中创建gRPC客户端
     * @date 2021/10/29 5:27 下午
     * @params []
     * @return repchain.inter.cooperation.middleware.pool.grpc.ComClientSingle
     **/
    @Override
    public ComClientSingle create() {
        return new ComClientSingle(this.host, this.port);
    }

    /**
     * @author lhc
     * @description // common-pool2 中创建了 DefaultPooledObject 对象对对象池中对象进行的包装。
     *                 将我们自定义的对象放置到这个包装中，工具会统计对象的状态、创建时间、更新时间、返回时间、出借时间、使用时间等等信息进行统计
     * @date 2021/10/29 5:29 下午
     * @params [comClientSingle]
     * @return org.apache.commons.pool2.PooledObject<repchain.inter.cooperation.middleware.pool.grpc.ComClientSingle>
     **/
    @Override
    public PooledObject<ComClientSingle> wrap(ComClientSingle comClientSingle) {
        return  new DefaultPooledObject<>(comClientSingle);
    }

    /**
     * @author lhc
     * @description // 关闭连接，销毁客户端
     * @date 2021/10/29 5:29 下午
     * @params [p]
     * @return void
     **/
    @Override
    public void destroyObject(PooledObject<ComClientSingle> p) throws Exception {
        p.getObject().shutdown();
        super.destroyObject(p);
    }
}
