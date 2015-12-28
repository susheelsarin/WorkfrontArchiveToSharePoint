package com.aurotech.workfront.sharepoint.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.aurotech.workfront.sharepoint.exception.IntegrationException;


public class EncryptionUtil {

    /*
     * NOTE: Never change this encryption key. Because all data encrypted with
     * old key will not be recovered
     */
    private static final String STRING_KEY = "aurotechcorp.com";

    private static final String ALGORITHM = "AES";

    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    private static Key getCipherKey() {

        // Create key and cipher
        Key aesKey = new SecretKeySpec(STRING_KEY.getBytes(), ALGORITHM);

        return aesKey;
    }

    public static String encrypt(String strToEncrypt) {
        if (StringUtils.isBlank(strToEncrypt)) {
            return null;
        }

        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, getCipherKey());

            final String encryptedString = Base64.encodeBase64String(cipher
                    .doFinal(strToEncrypt.getBytes()));

            return encryptedString;

        } catch (Exception e) {
            throw new IntegrationException("Error occured while encrypting.", e);
        }

    }

    public static String decrypt(String strToDecrypt) {
        if (StringUtils.isBlank(strToDecrypt)) {
            return null;
        }

        try {

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, getCipherKey());

            final String decryptedString = new String(cipher.doFinal(Base64
                    .decodeBase64(strToDecrypt)));

            return decryptedString;
        } catch (Exception e) {
            throw new IntegrationException("Error occured while decrypting.", e);
        }
    }

}
