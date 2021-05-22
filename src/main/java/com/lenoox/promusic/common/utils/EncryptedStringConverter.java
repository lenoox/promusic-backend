package com.lenoox.promusic.common.utils;

import javax.persistence.AttributeConverter;

public class EncryptedStringConverter implements AttributeConverter<String, String> {
    private final DataEncrypter dataEcrypter;
    EncryptedStringConverter(DataEncrypter dataEcrypter) {
        this.dataEcrypter = dataEcrypter;
    }
    @Override
    public String convertToDatabaseColumn(String attribute) {
        String encrypted = dataEcrypter.encrypt(attribute);
        return encrypted;
    }
    @Override
    public String convertToEntityAttribute(String dbData) {
        String decrypted = dataEcrypter.decrypt(dbData);
        return decrypted;
    }
}
