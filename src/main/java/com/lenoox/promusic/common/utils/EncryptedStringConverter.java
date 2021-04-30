package com.lenoox.promusic.common.utils;

import lombok.extern.java.Log;
import javax.persistence.AttributeConverter;

@Log
public class EncryptedStringConverter implements AttributeConverter<String, String> {
    private final DataEncrypter dataEcrypter;
    EncryptedStringConverter(DataEncrypter dataEcrypter) {
        this.dataEcrypter = dataEcrypter;
    }
    @Override
    public String convertToDatabaseColumn(String attribute) {
        //log.info("attribute: "+ attribute);
        String encrypted = dataEcrypter.encrypt(attribute);
        //log.info("encrypted: "+ encrypted);
        return encrypted;
    }
    @Override
    public String convertToEntityAttribute(String dbData) {
        //log.info("dbdata: "+ dbData);
        String decrypted = dataEcrypter.decrypt(dbData);
        //log.info("decrypted: "+ decrypted);
        return decrypted;
    }
}
