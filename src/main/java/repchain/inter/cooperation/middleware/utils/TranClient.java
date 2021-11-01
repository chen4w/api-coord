package repchain.inter.cooperation.middleware.utils;


import com.rcjava.protos.Peer;
import com.rcjava.tran.TranCreator;
import com.rcjava.util.KeyUtil;
import org.bouncycastle.openssl.PEMParser;

import java.io.StringReader;
import java.security.PrivateKey;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author lhc
 * @version 1.0
 * @className Signature
 * @date 2021年10月13日 9:17 上午
 * @description 交易构建客户端
 */
public class TranClient {

    private String certFullName;
    private Peer.CertId certId;
    private Peer.ChaincodeId chainCodeId;
    private TranCreator tranCreator;
    private static final ConcurrentHashMap<String, TranClient> userClientMap = new ConcurrentHashMap<>();

    public TranClient(String creditCode, String certName,String privateKeyPem,String chainCodeName,String password) {
        this.chainCodeId = Peer.ChaincodeId.newBuilder().setChaincodeName(chainCodeName).setVersion(1).build();
        certFullName = creditCode + "." + certName;
        certId = Peer.CertId.newBuilder().setCreditCode(creditCode).setCertName(certName).build();
        PEMParser parser = new PEMParser(new StringReader(privateKeyPem));
        try {
            PrivateKey privateKey = KeyUtil.generatePrivateKey(parser, false, password);
            tranCreator = TranCreator.newBuilder().setPrivateKey(privateKey).setSignAlgorithm("sha1withecdsa").build();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static TranClient getClient(String creditCode, String certName,String privateKeyPem,String chainCodeName,String password) {
        if (userClientMap.containsKey(creditCode+chainCodeName)) {
            return userClientMap.get(creditCode+chainCodeName);
        } else {
            TranClient userClient = new TranClient(creditCode, certName,privateKeyPem,chainCodeName,password);
            userClientMap.put(creditCode+chainCodeName, userClient);
            return userClientMap.get(creditCode+chainCodeName);
        }
    }

    public String getCertFullName() {
        return certFullName;
    }

    public void setCertFullName(String certFullName) {
        this.certFullName = certFullName;
    }

    public Peer.CertId getCertId() {
        return certId;
    }

    public void setCertId(Peer.CertId certId) {
        this.certId = certId;
    }

    public Peer.ChaincodeId getChaincodeId() {
        return chainCodeId;
    }

    public void setChaincodeId(Peer.ChaincodeId chainCodeId) {
        this.chainCodeId = chainCodeId;
    }

    public TranCreator getTranCreator() {
        return tranCreator;
    }

    public void setTranCreator(TranCreator tranCreator) {
        this.tranCreator = tranCreator;
    }
}


