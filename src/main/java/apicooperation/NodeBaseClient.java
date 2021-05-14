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
public enum NodeBaseClient {

    // 初始化客户端
    Node1Client("121000005l35120456", "node1"), Node2Client("12110107bi45jh675g", "node2"),
    Node3Client("122000002n00123567", "node3"), Node4Client("921000005k36123789", "node4"),
    Node5Client("921000006e0012v696", "node5"), SuperClient("951002007l78123233", "super_admin");

    private String nodeName;
    private Peer.CertId certId;
    private Peer.ChaincodeId contractAssetsId;
    private TranCreator tranCreator;

    NodeBaseClient(String creditCode, String certName) {
        nodeName = creditCode + "." + certName;
        // 标识账户证书准备使用node1对应的私钥
        certId = Peer.CertId.newBuilder().setCreditCode(creditCode).setCertName(certName).build();
        // 获取私钥，这里使用jks，也可以使用pem方式来构建，标准的PrivateKey即可
        PrivateKey privateKey = null;
        if (certName != "super_admin") {
            privateKey = CertUtil.genX509CertPrivateKey(new File(String.format("jks/%s.%s.jks", creditCode, certName)), "123", nodeName).getPrivateKey();
        } else {
            privateKey = CertUtil.genX509CertPrivateKey(new File(String.format("jks/%s.%s.jks", creditCode, certName)), "super_admin", nodeName).getPrivateKey();
        }
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

    public Peer.ChaincodeId getContractAssetsId() {
        return contractAssetsId;
    }

    public void setContractAssetsId(Peer.ChaincodeId contractAssetsId) {
        this.contractAssetsId = contractAssetsId;
    }

    public TranCreator getTranCreator() {
        return tranCreator;
    }

    public void setTranCreator(TranCreator tranCreator) {
        this.tranCreator = tranCreator;
    }
}
