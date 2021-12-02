package repchain.inter.cooperation.middleware.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lhc
 * @version 1.0
 * @className PerReq
 * @date 2021年11月29日 11:46 上午
 * @description 描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PerReq {
    private String cid;
    private Integer pageSize;
    private Integer pageNo;
}
