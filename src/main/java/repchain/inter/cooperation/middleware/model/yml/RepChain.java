package repchain.inter.cooperation.middleware.model.yml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lhc
 * @version 1.0
 * @className RepChain
 * @date 2021年10月29日 2:27 下午
 * @description 描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RepChain {
    private String host;
    private Long height;
    private String creditCode;
    private String certName;
    private String chainNetworkId;
    private String cert;
    private String privateKey;
    private String password;
    private List<Service> services;
    private Integer blockTime;

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
