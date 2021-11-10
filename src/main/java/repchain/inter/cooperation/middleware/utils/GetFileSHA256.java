package repchain.inter.cooperation.middleware.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * @author lhc
 * @version 1.0
 * @className GetFileSHA256
 * @date 2021年11月10日 9:47 上午
 * @description 描述
 */
public class GetFileSHA256 {

    public static void main(String[] args) {
        File file = new File("/Users/lhc/Downloads/canal.adapter-1.1.5.tar.gz");
        System.out.println("文件 " + file + " SHA256值是:" + getFileSha256(file));
    }

    public static String getFileSha256(File file) {
        String str = "";
        try {
            str = getHash(file, "SHA-256");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    private static String getHash(File file, String hashType) throws Exception {
        InputStream fis = new FileInputStream(file);
        byte buffer[] = new byte[1024];
        MessageDigest md5 = MessageDigest.getInstance(hashType);
        for (int numRead = 0; (numRead = fis.read(buffer)) > 0; ) {
            md5.update(buffer, 0, numRead);
        }
        fis.close();
        return toHexString(md5.digest());
    }

    private static String toHexString(byte b[]) {
        StringBuilder sb = new StringBuilder();
        for (byte aB : b) {
            sb.append(Integer.toHexString(aB & 0xFF));
        }
        return sb.toString();
    }
}
