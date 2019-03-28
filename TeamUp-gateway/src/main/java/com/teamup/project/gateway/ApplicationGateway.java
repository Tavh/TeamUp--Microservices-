package com.teamup.project.gateway;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@EnableConfigurationProperties(URIConfiguration.class)
@RestController
public class ApplicationGateway {

//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder,URIConfiguration uriConfiguration) {
//    	String httpUri = uriConfiguration.getHttpbin();
//        return builder.routes()
//            .route(p -> p
//                .path("/get")
//                .filters(f -> f.hystrix(config -> config.setFallbackUri("forward:/fallback")))
//                .uri("http://localhost:8080/secondURL"))
//                .build();
//    }
}
