package model.yml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lhc
 * @version 1.0
 * @className Service
 * @date 2021年10月12日 4:13 下午
 * @description 描述
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Service implements Serializable {

    private static final long serialVersionUID = -7849738826572360811L;
    private String e_from;
    private String e_to;
    private String from_host;
    private String from_port;
    private String to_host;
    private String to_port;
    private String from_cert;

    public String handleCert() {
        String start = "-----BEGIN CERTIFICATE-----";
        String end = "-----END CERTIFICATE-----";
        from_cert = from_cert.replaceAll(start, "");
        from_cert = from_cert.replaceAll(end, "");
        from_cert = from_cert.replaceAll(" ", "\n");
        return start + from_cert + end + "\n";
    }
}
