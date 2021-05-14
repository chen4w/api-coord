package apicooperation;

import com.rcjava.protos.Peer;
import com.rcjava.tran.TranCreator;
import com.rcjava.util.CertUtil;

import java.io.File;
import java.security.PrivateKey;

/**
 * 节点客户端
 *
 * @author zyf
 */
public enum ApiBaseClient {

    // 初始化接口参与方的客户端
    ApproverClient("637996965623261114", "accountSignA"), ApiRegisterClient("669776584506098770", "apiRegSignA"),
    ApireqClient("275825005723476137", "apiReqSignA"), ApiServClient("312143999852503256", "apiAckSignA");

    private String nodeName;
    private Peer.CertId certId;
    private Peer.ChaincodeId apiCooperationId = Peer.ChaincodeId.newBuilder().setChaincodeName("InterfaceCooperation").setVersion(1).build();
    private TranCreator tranCreator;

    ApiBaseClient(String creditCode, String certName) {
        nodeName = creditCode + "." + certName;
        certId = Peer.CertId.newBuilder().setCreditCode(creditCode).setCertName(certName).build();
        // 获取私钥，这里使用jks，也可以使用pem方式来构建，标准的PrivateKey即可
        PrivateKey privateKey = CertUtil.genX509CertPrivateKey(new File(String.format("api/jks/%s.%s.jks", creditCode, certName)), "123", nodeName).getPrivateKey();
        tranCreator = TranCreator.newBuilder().setPrivateKey(privateKey).setSignAlgorithm("sha1withecdsa").build();
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Peer.CertId getCertId() {
        return certId;
    }

    public void setCertId(Peer.CertId certId) {
        this.certId = certId;
    }

    public Peer.ChaincodeId getApiCooperationId() {
        return apiCooperationId;
    }

    public void setApiCooperationId(Peer.ChaincodeId apiCooperationId) {
        this.apiCooperationId = apiCooperationId;
    }

    public TranCreator getTranCreator() {
        return tranCreator;
    }

    public void setTranCreator(TranCreator tranCreator) {
        this.tranCreator = tranCreator;
    }
}
