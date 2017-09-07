package com.ofo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by XL on 8/26/2017.
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ShoppingCartServiceApplication {
    public static void main(String[] args){
        SpringApplication.run(ShoppingCartServiceApplication.class, args);
    }
}
