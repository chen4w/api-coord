package repchain.inter.cooperation.middleware.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long id;
    private String cid;
    private String e_from;
    private String e_to;
    private String method;
    private Boolean b_req;
    private Boolean b_end;
    private Integer seq;
    private Long tm_create;
    private String callback_url;
    private String callback_method;
    private String validStr;
    private String signData;
    private String data;
    private Integer state;
    private String httpType;
    private String url;
}
