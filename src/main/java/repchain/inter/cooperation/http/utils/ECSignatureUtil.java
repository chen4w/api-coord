package repchain.inter.cooperation.http.utils;

import com.rcjava.util.CertUtil;
import com.rcjava.util.KeyUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bouncycastle.openssl.PEMParser;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.X509Certificate;

/**
 * @author zyf
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ECSignatureUtil {

    /**
     * 默认算法
     */
    private String signAlgorithm = "SHA256withECDSA";

    /**
     * 用私钥进行签名
     *
     * @param privateKey 私钥
     * @param message    原始数据
     * @return
     */
    public byte[] sign(PrivateKey privateKey, byte[] message) {
        return this.sign(privateKey, message, signAlgorithm);
    }

    /**
     * 用私钥进行签名
     *
     * @param privateKey    私钥
     * @param message       原始数据
     * @param signAlgorithm 签名算法
     * @return
     */
    public byte[] sign(PrivateKey privateKey, byte[] message, String signAlgorithm) {
        try {
            Signature s1 = Signature.getInstance(signAlgorithm);
            s1.initSign(privateKey);
            s1.update(message);
            return s1.sign();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new byte[0];
    }

    /**
     * 验证签名
     *
     * @param signature 签名数据
     * @param message   原始数据
     * @param publicKey 公钥
     * @return
     */
    public Boolean verify(byte[] signature, byte[] message, PublicKey publicKey) {
        return this.verify(signature, message, publicKey, signAlgorithm);
    }

    /**
     * 验证签名
     *
     * @param signature       签名数据
     * @param message         原始数据
     * @param x509Certificate 证书
     * @return
     */
    public Boolean verify(byte[] signature, byte[] message, X509Certificate x509Certificate) {
        return this.verify(signature, message, x509Certificate.getPublicKey());
    }

    /**
     * 验证签名
     *
     * @param signature     签名数据
     * @param message       原始数据
     * @param publicKey     公钥
     * @param signAlgorithm 签名算法
     * @return
     */
    public Boolean verify(byte[] signature, byte[] message, PublicKey publicKey, String signAlgorithm) {
        try {
            Signature s2 = Signature.getInstance(signAlgorithm);
            s2.initVerify(publicKey);
            s2.update(message);
            return s2.verify(signature);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
//        PEMParser parser = new PEMParser(new StringReader("-----BEGIN PRIVATE KEY-----\n" +
//                "MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQgd0X+dDwH+cR5eluF\n" +
//                "huC/3rJTjKFxh9Unv7DLeqsZv2KgCgYIKoZIzj0DAQehRANCAAQqfTn1r0s5E0L1\n" +
//                "4rD7KgZ8/HgkOFN7R0SgCkXUNuQ9EuNGGW8VNbdnVvQvz0v0M+I2Rqmelr28m8t9\n" +
//                "MyJTfN92\n" +
//                "-----END PRIVATE KEY-----"));
        PEMParser parser = new PEMParser(new StringReader("-----BEGIN ENCRYPTED PRIVATE KEY-----\n" +
                "MIHUMD8GCSqGSIb3DQEFDTAyMBoGCSqGSIb3DQEFDDANBAg1+P5snkrhWgIBZDAU\n" +
                "BggqhkiG9w0DBwQIenlxow1YzdIEgZB8q8RyYheLMgYbsNO12ZStj8NZfNd+2GC4\n" +
                "XU8C2inwh1qpEfjaFQqhvr9nY2BvlE5WPO33yYAVv7PUVGyGZterykrI6c2XUfPN\n" +
                "W45jFOzme6y8lhbVpefg0GU7mU/AITL5gOV7iJcQG0iyvplHP4TEjVFj3zM+ow4b\n" +
                "cBuNWzA6z01Mq4DJCwJwPNxi9H2yc0U=\n" +
                "-----END ENCRYPTED PRIVATE KEY-----"));
        PrivateKey privateKey = KeyUtil.generatePrivateKey(parser, false, "123456");
        System.out.println(privateKey);
        X509Certificate x509Certificate = CertUtil.generateX509Cert("-----BEGIN CERTIFICATE-----\n" +
                "MIIBYzCCAQigAwIBAgIEXe8B6zAKBggqhkjOPQQDAjA5MREwDwYDVQQKDAhyZXBj\n" +
                "aGFpbjEOMAwGA1UECwwFaXNjYXMxFDASBgNVBAMMC3N1cGVyX2FkbWluMB4XDTE5\n" +
                "MTIxMDAyMjQ0M1oXDTIwMTIwOTAyMjQ0M1owOTERMA8GA1UECgwIcmVwY2hhaW4x\n" +
                "DjAMBgNVBAsMBWlzY2FzMRQwEgYDVQQDDAtzdXBlcl9hZG1pbjBZMBMGByqGSM49\n" +
                "AgEGCCqGSM49AwEHA0IABK9m+cb8jaYQ+ts/hK4INuQbOwAwoIhVa3uaRxsOsgoR\n" +
                "+QaPuwcZAIbGWSa9bn8oGjSBDQutmE5XONbdiDwPRtwwCgYIKoZIzj0EAwIDSQAw\n" +
                "RgIhAKZto+39OFced9YDaXYkOLrLcKD+8RbF57vzHpJrnFd1AiEAhNEK8MOsAlhM\n" +
                "eRZmlXsq4KsvQFs+Wav9N9qJ+GGRpCs=\n" +
                "-----END CERTIFICATE-----");
        ECSignatureUtil ecSignatureUtil = new ECSignatureUtil();
        ecSignatureUtil.setSignAlgorithm("SHA256withECDSA");
        byte[] sign = ecSignatureUtil.sign(privateKey, "test".getBytes(StandardCharsets.UTF_8));
        System.out.println(ecSignatureUtil.verify(sign, "test".getBytes(StandardCharsets.UTF_8), x509Certificate));
//        System.out.println(((PEMKeyPair)parser.readObject()).get);
    }
}
