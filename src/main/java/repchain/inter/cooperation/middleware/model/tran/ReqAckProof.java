package repchain.inter.cooperation.middleware.model.tran;

import lombok.*;

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
@ToString
public class ReqAckProof {

    private String cid;
    private String e_from;
    private String e_to;
    private String method;
    private boolean b_req;
    private boolean b_end;
    private int seq;
    private String hash;
    private String hash_claim;
    private Long tm_create;
    private Signature sign_r;
    private Signature sign_c;

}
