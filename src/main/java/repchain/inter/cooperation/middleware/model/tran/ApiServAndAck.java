package repchain.inter.cooperation.middleware.model.tran;

import lombok.*;

import java.io.Serializable;

/**
 * @author lhc
 * @version 1.0
 * @className ApiServAndAck
 * @date 2021年11月02日 9:41 上午
 * @description 描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class ApiServAndAck implements Serializable {
    private static final long serialVersionUID = -5139385293505016457L;
    private String id;
    private String name;
    private String d_id;
    private String version;
    private String e_id;
    private String addr;
    private Integer port;
}
