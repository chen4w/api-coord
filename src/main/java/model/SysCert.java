package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author lhc
 * @version 1.0
 * @className SysCert
 * @date 2021年07月09日 2:18 下午
 * @description 用户身份信息对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SysCert implements Serializable {
    private static final long serialVersionUID = 1695879045437578092L;
    private String cert;
    private String privateKey;
    private String creditCode;
    private String certName;
    private String password;
}
