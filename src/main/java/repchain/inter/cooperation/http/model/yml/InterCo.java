package repchain.inter.cooperation.http.model.yml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lhc
 * @version 1.0
 * @className InterCo
 * @date 2021年10月12日 4:10 下午
 * @description 描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class InterCo {

    private String creditCode;
    private String certName;
    private String cert;
    private String privateKey;
    private String algo_hash;
    private String algo_sign;
    private String password;
    private List<Service> services;

    public String handleCert() {
        String start = "-----BEGIN CERTIFICATE-----";
        String end = "-----END CERTIFICATE-----";
        cert = cert.replaceAll(start, "");
        cert = cert.replaceAll(end, "");
        cert = cert.replaceAll(" ", "\n");
        return start + cert + end + "\n";
    }

    public String handlePrivateKey() {
        String start = "-----BEGIN ENCRYPTED PRIVATE KEY-----";
        String end = "-----END ENCRYPTED PRIVATE KEY-----";
        privateKey = privateKey.replaceAll(start, "");
        privateKey = privateKey.replaceAll(end, "");
        privateKey = privateKey.replaceAll(" ", "\n");
        return start + privateKey + end + "\n";
    }
}
