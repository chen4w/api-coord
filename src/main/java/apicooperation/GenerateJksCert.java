package apicooperation;

import java.io.File;

/**
 * @author zyf
 */
public class GenerateJksCert {

    // 账户认证者
    static String accountApproverId = "637996965623261114";
    static String accountApproverSslName = "accountSslA";
    static String accountApproverSignName = "accountSignA";
    // 接口定义者
    static String apiRegisterId = "669776584506098770";
    static String apiRegisterSslName = "apiRegSslA";
    static String apiRegisterSignName = "apiRegSignA";
    // 接口请求者
    static String apiReqId = "275825005723476137";
    static String apiReqSslName = "apiReqSslA";
    static String apiReqSignName = "apiReqSignA";
    // 接口服务方
    static String apiAckId = "312143999852503256";
    static String apiAckSslName = "apiAckSslA";
    static String apiAckSignName = "apiAckSignA";

    private static String jksDir = "api/jks/";
    private static String cersDir = "api/certs/";


    public static void main(String[] args) throws Exception {

        new File(jksDir).mkdirs();
        new File(cersDir).mkdirs();

        genJksAndCertsFile(accountApproverId, accountApproverSslName);
        genJksAndCertsFile(accountApproverId, accountApproverSignName);
        genJksAndCertsFile(apiRegisterId, apiRegisterSslName);
        genJksAndCertsFile(apiRegisterId, apiRegisterSignName);
        genJksAndCertsFile(apiReqId, apiReqSslName);
        genJksAndCertsFile(apiReqId, apiReqSignName);
        genJksAndCertsFile(apiAckId, apiAckSslName);
        genJksAndCertsFile(apiAckId, apiAckSignName);
    }

    /**
     * 生成单个JksFile与证书Cert
     *
     * @param certAlias
     * @throws Exception
     */
    public static void genJksAndCertsFile(String randomStr, String certAlias) throws Exception {
        GenerateJks.genJksFile(jksDir, randomStr + "." + certAlias, "123", certAlias);
        GenerateJks.exportCertFromJks(randomStr + "." + certAlias, jksDir, cersDir);
    }
}
