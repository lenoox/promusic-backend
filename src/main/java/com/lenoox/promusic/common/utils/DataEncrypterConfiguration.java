package com.lenoox.promusic.common.utils;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import java.util.Base64;
import java.util.Objects;

@ConfigurationProperties(prefix = "security.data-encryption")
@ConstructorBinding
@Value
class DataEncrypterConfiguration {
    EncryptionKey encryptionKey;
    @Value
    static class EncryptionKey {
        byte[] value;
        public EncryptionKey(String value) {
            Objects.requireNonNull(value);
            this.value = Base64.getDecoder().decode(value);
        }
    }
}
