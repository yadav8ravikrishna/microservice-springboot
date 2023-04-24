package com.microservice.apigateway;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p->p.path("/get")
                        .filters(f->f
                                .addRequestHeader("myHeader","myURI")
                                .addRequestParameter("param","myValue"))
                        .uri("http://httpbin.org:80"))
                .route(p->p.path("/currency-exchange/**")
                        .uri("lb://CURRENCY-EXCHANGE"))
                .route(p->p.path("/currency-conversion/**")
                        .uri("lb://CURRENCY-CONVERSION"))
                .build();
    }
}
