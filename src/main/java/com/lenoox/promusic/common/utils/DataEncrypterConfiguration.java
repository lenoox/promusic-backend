package com.lenoox.promusic.common.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.data-encryption")
class DataEncrypterConfiguration {
    byte[] ecryptionKey;
}
