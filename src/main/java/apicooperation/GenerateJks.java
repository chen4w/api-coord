package apicooperation;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Path;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.spec.ECGenParameterSpec;
import java.util.Date;
import java.util.Enumeration;

/**
 * 生成Jks，privateKey，自签名证书
 *
 * @author zyf
 */
public class GenerateJks {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    // 生成Jks的路径
    private static String jksDirectory = "newJks/";
    private static String certsDirectory = "newCerts/";
    // 多节点，子路径：multiJks
    private static String multiDir = "multi/";
    // 单节点，子路径：singleJks
    private static String singleDir = "single/";
    // 多节点
    private static File multiJksDir = new File(jksDirectory + multiDir);
    private static File multiCertsDir = new File(certsDirectory + multiDir);
    // 单节点
    private static File singleJksDir = new File(jksDirectory + singleDir);
    private static File singleCertDir = new File(certsDirectory + singleDir);

    /**
     * 清理目录
     */
    private static void cleanDirectory(File jksDir, File certsDir) {
        // 创建目录，如果存在了，那么清理干净
        if (!jksDir.exists()) {
            jksDir.mkdirs();
        }
        if (!certsDir.exists()) {
            certsDir.mkdirs();
        }
        // delete file
        for (File file : jksDir.listFiles()) {
            file.delete();
        }
        // delete file
        for (File file : certsDir.listFiles()) {
            file.delete();
        }
    }


    /**
     * 生成自签名Jks
     *
     * @param dir        生成jks的目录
     * @param alias      别名
     * @param password   密码
     * @param commonName 证书CN
     * @throws Exception
     */
    public static void genJksFile(String dir, String alias, String password, String commonName) throws Exception {

        // 使用secp256r1初始化
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "BC");
        keyPairGenerator.initialize(new ECGenParameterSpec("P-256"));

        // 生成keypair
        KeyPair keyPair = keyPairGenerator.genKeyPair();

        // 初始化Jks
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);

        // 生成self-signed V3Certificate
        X509CertificateHolder x509CertificateHolder = createTrustHolder(keyPair, "SHA256withECDSA", commonName);
        X509Certificate x509Certificate = new JcaX509CertificateConverter().setProvider("BC").getCertificate(x509CertificateHolder);
        keyStore.setKeyEntry(alias, keyPair.getPrivate(), password.toCharArray(), new Certificate[]{x509Certificate});
        FileOutputStream fileOutputStream = new FileOutputStream(Path.of(dir, alias + ".jks").toFile());
        keyStore.store(fileOutputStream, password.toCharArray());
        fileOutputStream.close();
    }

    /**
     * @param alias    eg. 121000005l35120456.zyf
     * @param jksPath  newJks/single
     * @param certPath newCerts/single
     * @throws Exception
     */
    public static void exportCertFromJks(String alias, String jksPath, String certPath) throws Exception {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        Path jksName = Path.of(jksPath, alias + ".jks");
        keyStore.load(new FileInputStream(jksName.toFile()), "123".toCharArray());
        Certificate cert = keyStore.getCertificate(alias);
        Path certName = Path.of(certPath, alias + ".cer");
        exportToPemFile(new File(certName.toString()), cert);
    }

    /**
     * @param keyPair    密钥对
     * @param sigAlg     使用sigAlg来签名证书
     * @param commonName
     * @return
     */
    public static X509CertificateHolder createTrustHolder(KeyPair keyPair, String sigAlg, String commonName) throws OperatorCreationException {

        X500NameBuilder x500NameBuilder = new X500NameBuilder(BCStyle.INSTANCE);
        X500Name x500Name = x500NameBuilder
                .addRDN(BCStyle.CN, commonName)
                .addRDN(BCStyle.OU, "iscas")
                .addRDN(BCStyle.O, "RepChain")
                .build();

        X509v3CertificateBuilder certificateBuilder = new JcaX509v3CertificateBuilder(
                x500Name,
                BigInteger.valueOf(System.currentTimeMillis()),
                CalculateDate(0),
                CalculateDate(24 * 365 * 5),
                x500Name,
                keyPair.getPublic()
        );

        ContentSigner contentSigner = new JcaContentSignerBuilder(sigAlg).setProvider("BC").build(keyPair.getPrivate());

        return certificateBuilder.build(contentSigner);
    }

    /**
     * @param hoursInFuture
     * @return
     */
    private static Date CalculateDate(int hoursInFuture) {
        long secondsNow = System.currentTimeMillis() / 1000;
        return new Date((secondsNow + (hoursInFuture * 60 * 60)) * 1000);
    }

    /**
     * 输出PEM字符串到文件中，PKCS1(if object instanceof PrivateKey == true)
     *
     * @param pemFile 文件路径
     * @param object  类型，i.e.,privateKey，publicKey，certificate
     * @throws IOException
     */
    private static void exportToPemFile(File pemFile, Object object) throws IOException {
        Writer writer = new FileWriter(pemFile);
        JcaPEMWriter pemWriter = new JcaPEMWriter(writer);
        pemWriter.writeObject(object);
        pemWriter.close();
        writer.close();
    }
}
