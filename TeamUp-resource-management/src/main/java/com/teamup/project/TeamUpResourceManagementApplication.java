package com.teamup.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import java.util.Properties;


@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class TeamUpResourceManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamUpResourceManagementApplication.class, args);
	}


}
