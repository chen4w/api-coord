package model.yml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lhc
 * @version 1.0
 * @className InterCo
 * @date 2021年10月12日 4:10 下午
 * @description 描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class InterCo {

    private String creditCode;
    private String certName;
    private String cert;
    private String privateKey;
    private String algo_hash;
    private String algo_sign;
    private List<Service> services;
}
