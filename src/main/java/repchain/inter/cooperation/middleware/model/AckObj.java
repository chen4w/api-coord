package repchain.inter.cooperation.middleware.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import repchain.inter.cooperation.middleware.model.tran.ApiDefinition;
import repchain.inter.cooperation.middleware.model.tran.ApiServAndAck;

/**
 * @author lhc
 * @version 1.0
 * @className AckObj
 * @date 2021年11月09日 2:54 下午
 * @description 描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AckObj {

    private ApiServAndAck to;
    private ApiServAndAck from;
    private ApiDefinition apiDefinition;
    private Header header;
}
