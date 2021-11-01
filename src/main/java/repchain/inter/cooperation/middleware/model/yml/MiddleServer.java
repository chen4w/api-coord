package repchain.inter.cooperation.middleware.model.yml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lhc
 * @version 1.0
 * @className MServer
 * @date 2021年11月01日 10:51 上午
 * @description 描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MiddleServer {
    Integer port;
    Integer corePoolSize;
    Integer maxPoolSize;
    Integer workQueue;
}
