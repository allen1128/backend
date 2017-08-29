package com.ofo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by XL on 8/26/2017.
 */

@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(EurekaServiceApplication.class, args);
    }
}
