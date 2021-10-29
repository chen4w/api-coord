package repchain.inter.cooperation.middleware.pool.grpc;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @author lhc
 * @version 1.0
 * @className ComClientFactory
 * @date 2021年10月29日 3:22 下午
 * @description 描述
 */
public class ComClientFactory extends BasePooledObjectFactory<ComClientSingle> {

    private String host;

    private int port;

    public ComClientFactory(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public ComClientSingle create() {
        return new ComClientSingle(this.host, this.port);
    }

    @Override
    public PooledObject<ComClientSingle> wrap(ComClientSingle comClientSingle) {
        return  new DefaultPooledObject<>(comClientSingle);
    }

    @Override
    public void destroyObject(PooledObject<ComClientSingle> p) throws Exception {
        p.getObject().shutdown();
        super.destroyObject(p);
    }
}
