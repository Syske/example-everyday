package io.github.syske.example20210509;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @program: example-2021-05-09
 * @description: 2021-05-09 example 常用的加密算法
 * @author: syske
 * @create: 2021-05-10 07:10
 */
public class Example {
    /**
     * base64加密
     *
     * @param sourcesStr
     * @return
     */
    public String encodeBase64(String sourcesStr) {
        Base64.Encoder encoder = Base64.getEncoder();
        String encodeToString = encoder.encodeToString(sourcesStr.getBytes());
        return encodeToString;
    }

    /**
     * base64解码
     *
     * @param encodeBase64Str
     * @return
     */
    public String decodeBase64(String encodeBase64Str) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(encodeBase64Str);
        return new String(decode);
    }

    /**
     * md5加密
     * @param sourcesStr
     * @return
     */
    public String md5Encode(String sourcesStr) {
            byte[] secretBytes = null;
            try {
                secretBytes = MessageDigest.getInstance("md5").digest(
                        sourcesStr.getBytes());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("没有这个md5算法！");
            }
            String md5code = new BigInteger(1, secretBytes).toString(16);
            for (int i = 0; i < 32 - md5code.length(); i++) {
                md5code = "0" + md5code;
            }
            return md5code;
    }

}
