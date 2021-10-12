package model.yml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lhc
 * @version 1.0
 * @className RepchainConfig
 * @date 2021年10月12日 4:07 下午
 * @description 描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RepchainConfig implements Serializable {

    private static final long serialVersionUID = -4629492706640762657L;
    private Repchain repchain;

}
