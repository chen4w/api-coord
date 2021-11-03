package repchain.inter.cooperation.middleware.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import repchain.inter.cooperation.middleware.model.tran.Signature;

/**
 * @author lhc
 * @version 1.0
 * @className InterCoResult
 * @date 2021年10月19日 11:41 上午
 * @description 描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class InterCoResult {

    private Integer code;
    private String msg;
    private Object data;
    private String cid;
}
