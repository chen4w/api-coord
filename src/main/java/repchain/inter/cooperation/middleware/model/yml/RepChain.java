package repchain.inter.cooperation.middleware.model.yml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lhc
 * @version 1.0
 * @className RepChain
 * @date 2021年10月29日 2:27 下午
 * @description 描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RepChain {
    private String host;
    private Long height;
    private String creditCode;
    private String certName;
    private String cert;
    private String privateKey;
    private String password;
    private List<Service> services;
}
