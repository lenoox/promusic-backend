package com.lenoox.promusic.common.utils;

import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
class DataEncrypter {
    private static final String AES = "AES";
    private final Key key;

    DataEncrypter(DataEncrypterConfiguration dataEncrypterConfiguration) {
        key = new SecretKeySpec(dataEncrypterConfiguration.getEncryptionKey().getValue(), AES);
    }
    String encrypt(String attribute) {
        try {
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(attribute.getBytes()));
        } catch (IllegalBlockSizeException |
                BadPaddingException |
                InvalidKeyException |
                NoSuchPaddingException |
                NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
    String decrypt(String dbData) {
        try {
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(dbData)));
        } catch (InvalidKeyException |
                BadPaddingException |
                IllegalBlockSizeException |
                NoSuchPaddingException |
                NoSuchAlgorithmException  e) {
            throw new IllegalStateException(e);
        }
    }
}
