package repchain.inter.cooperation.middleware.model.yml;

import lombok.*;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @author lhc
 * @version 1.0
 * @className CClient
 * @date 2021年11月01日 9:29 上午
 * @description 描述
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ComClient extends GenericObjectPoolConfig {
    private Long timeout;
}
