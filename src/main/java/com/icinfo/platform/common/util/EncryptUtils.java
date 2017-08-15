package com.icinfo.platform.common.util;

import com.icinfo.platform.system.model.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具
 */
public class EncryptUtils{
    private static final String[] hexDigits = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public EncryptUtils() {
    }

    /** @deprecated */
    @Deprecated
    public static String GetMD5Code(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] byteArray = str.getBytes("UTF-8");
        return md5(byteArray);
    }

    public static String md5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] byteArray = str.getBytes("UTF-8");
        return md5(byteArray);
    }

    public static String md5(byte[] bytes) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] md5Bytes = md5.digest(bytes);
        StringBuffer sb = new StringBuffer();
        byte[] arr$ = md5Bytes;
        int len$ = md5Bytes.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            byte _byte = arr$[i$];
            sb.append(hexDigits[_byte >>> 4 & 15]);
            sb.append(hexDigits[_byte & 15]);
        }

        return sb.toString();
    }

    // 随机数生成器
    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    // 密码hash算法
    private static final String PASSWORD_HASH_ALGORITHM_NAME = "md5";

    // 密码迭代次数
    private static int PASSWORD_HASH_ITERATIONS = 2;

    /**
     * 用户密码加密
     *
     * @param user 用户信息
     */
    public static void encryptPassword(User user) {
        String salt = randomNumberGenerator.nextBytes().toHex();
        user.setEncryptSalt(salt);
        user.setPassword(encryptPassword(user.getPassword(),
                user.getEncryptSalt()));
    }

    /**
     * 密码加密
     *
     * @param password 原始密码
     * @param salt     hash盐值
     * @return
     */
    public static String encryptPassword(String password, String salt) {
        return new SimpleHash(PASSWORD_HASH_ALGORITHM_NAME, password,
                ByteSource.Util.bytes(salt), PASSWORD_HASH_ITERATIONS)
                .toHex();
    }
}
