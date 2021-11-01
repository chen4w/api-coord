package repchain.inter.cooperation.middleware.model.yml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lhc
 * @version 1.0
 * @className Service
 * @date 2021年11月01日 11:41 上午
 * @description 描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Service {

    private String serviceId;
    private String e_from;
    private String from_cert;
    private String e_to;
    private String to_cert;
}
