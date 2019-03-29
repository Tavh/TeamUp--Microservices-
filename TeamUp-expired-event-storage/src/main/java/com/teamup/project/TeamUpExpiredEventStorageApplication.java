package com.teamup.project;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TeamUpExpiredEventStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamUpExpiredEventStorageApplication.class, args);
		
		// Java properties that allow micro services
		Properties systemProps = System.getProperties();
		systemProps.put("javax.net.ssl.keyStorePassword","passwordForKeystore");
		systemProps.put("javax.net.ssl.keyStore","pathToKeystore.ks");
		systemProps.put("javax.net.ssl.trustStore", "pathToTruststore.ts");
		systemProps.put("javax.net.ssl.trustStorePassword","passwordForTrustStore");
		System.setProperties(systemProps);
	}

}
