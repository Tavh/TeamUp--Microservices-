package com.teamup.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import java.util.Properties;


@SpringBootApplication
public class TeamUpResourceManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamUpResourceManagementApplication.class, args);
		
		// Java properties that allow micro services
		Properties systemProps = System.getProperties();
		systemProps.put("javax.net.ssl.keyStorePassword","passwordForKeystore");
		systemProps.put("javax.net.ssl.keyStore","pathToKeystore.ks");
		systemProps.put("javax.net.ssl.trustStore", "pathToTruststore.ts");
		systemProps.put("javax.net.ssl.trustStorePassword","passwordForTrustStore");
		System.setProperties(systemProps);
	}

	// Providing a RestTemplate bean
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}


}
