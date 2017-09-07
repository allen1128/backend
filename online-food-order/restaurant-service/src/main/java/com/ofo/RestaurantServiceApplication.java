package com.ofo;

import com.ofo.domain.Address;
import com.ofo.domain.Restaurant;
import com.ofo.service.RestaurantService;
import com.ofo.service.RestaurantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XL on 8/26/2017.
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class RestaurantServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantServiceApplication.class, args);
    }
}
