package repchain.inter.cooperation.middleware.model.tran;

import lombok.*;

import java.io.Serializable;

/**
 * @author lhc
 * @version 1.0
 * @className ApiDefinition
 * @date 2021年11月02日 9:43 上午
 * @description 描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class ApiDefinition implements Serializable {
    private static final long serialVersionUID = 371221659255220102L;
    private String id;
    private String type;
    private String version;
    private String name;
    private String algo_hash;
    private String algo_sign;
    private String para;
    private String serv;
    private String serv_doc;
    private String callback;
    private String callback_doc;
}
