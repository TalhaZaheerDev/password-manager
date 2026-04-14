package com.talha.passwordmanager.util;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.spec.KeySpec;
import java.util.Base64;

public class CryptoUtil {

    private static final String ALGO = "AES";
    private static final String KDF = "PBKDF2WithHmacSHA256";
    private static final String SALT = "some_salt"; // later per-user
    private static final int ITERATIONS = 65536;

    private static SecretKey generateKey(String masterPassword) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance(KDF);
        KeySpec spec = new PBEKeySpec(masterPassword.toCharArray(), SALT.getBytes(), ITERATIONS, 128);
        SecretKey tmp = factory.generateSecret(spec);
        return new SecretKeySpec(tmp.getEncoded(), ALGO);
    }

    public static String encrypt(String data, String masterPassword) throws Exception {
        SecretKey key = generateKey(masterPassword);

        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    }

    public static String decrypt(String encrypted, String masterPassword) throws Exception {
        SecretKey key = generateKey(masterPassword);

        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.DECRYPT_MODE, key);

        return new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)));
    }
}