package repchain.inter.cooperation.middleware.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import repchain.inter.cooperation.middleware.model.tran.ReqAckProof;
import repchain.inter.cooperation.middleware.proto.Result;

/**
 * @author lhc
 * @version 1.0
 * @className MsgVo
 * @date 2021年11月02日 3:42 下午
 * @description 描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MsgVo {
    private Result result;
    private ReqAckProof reqAckProof;
}
