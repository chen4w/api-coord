package repchain.inter.cooperation.middleware.model.tran;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lhc
 * @version 1.0
 * @className Signature
 * @date 2021年10月13日 9:17 上午
 * @description 描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Signature implements Serializable {

    private static final long serialVersionUID = 104654482955643156L;
    private String eid;
    private String cert_name;
    private String hash;
    private Long timeCreate;
    private String sign;
}
