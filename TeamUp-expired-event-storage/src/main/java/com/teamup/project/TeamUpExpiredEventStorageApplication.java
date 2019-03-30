package com.teamup.project;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@RibbonClient(name = "expired-event-service", configuration = RibbonExpiredEventConfig.class) 
public class TeamUpExpiredEventStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamUpExpiredEventStorageApplication.class, args);
	}

}
