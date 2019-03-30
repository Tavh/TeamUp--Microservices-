package com.teamup.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import java.util.Properties;


@SpringBootApplication
@RibbonClient(name = "resource-management-service", configuration = RibbonResourceManagementConfig.class) 
public class TeamUpResourceManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamUpResourceManagementApplication.class, args);
	}

	// Providing a RestTemplate bean
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}


}
