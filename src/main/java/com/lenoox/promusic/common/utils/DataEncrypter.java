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
    private static final String SECRET = "testowy-key-1234";
    private final Key key;
    private final Cipher cipher;

    private final DataEncrypterConfiguration dataEncrypterConfiguration;
    DataEncrypter(DataEncrypterConfiguration dataEncrypterConfiguration)
            throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.dataEncrypterConfiguration = dataEncrypterConfiguration;
        key = new SecretKeySpec(SECRET.getBytes(), AES);
        cipher = Cipher.getInstance(AES);
    }
    String encrypt(String attribute) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(attribute.getBytes()));
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            throw new IllegalStateException(e);
        }
    }
    String decrypt(String dbData) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(dbData)));
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new IllegalStateException(e);
        }
    }
}
