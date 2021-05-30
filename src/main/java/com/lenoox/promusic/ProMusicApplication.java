package com.lenoox.promusic;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@SpringBootApplication
@ConfigurationPropertiesScan
@Log
public class ProMusicApplication {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		/*KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(256);
		SecretKey secretKey = keyGen.generateKey();
		String encoded = Base64.getEncoder().encodeToString(secretKey.getEncoded());
		log.info(encoded);*/
		SpringApplication.run(ProMusicApplication.class, args);
	}

}
