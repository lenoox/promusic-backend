package com.lenoox.promusic;

import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@SpringBootApplication
@ConfigurationPropertiesScan
@Log
public class ProMusicApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		/*KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(256);
		SecretKey secretKey = keyGen.generateKey();
		String encoded = Base64.getEncoder().encodeToString(secretKey.getEncoded());
		log.info(encoded);*/
		SpringApplication.run(ProMusicApplication.class, args);
	}

}
