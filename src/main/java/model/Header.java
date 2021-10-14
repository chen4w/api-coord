package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.tran.Signature;

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
public class Header {
    private String cid;
    private String e_from;
    private String e_to;
    private String method;
    private boolean b_req;
    private boolean b_end;
    private int seq;
    private Long tm_create;
}
