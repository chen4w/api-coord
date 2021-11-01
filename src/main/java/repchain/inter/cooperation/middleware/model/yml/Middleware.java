package repchain.inter.cooperation.middleware.model.yml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lhc
 * @version 1.0
 * @className Middleware
 * @date 2021年10月29日 2:27 下午
 * @description 描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Middleware {
    private RecClient recClient;
    private ComClient comClient;
    private MiddleServer recServer;
    private MiddleServer comServer;
}
