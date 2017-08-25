package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by XL on 8/25/2017.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RunningLocationDistributionApplication {
    public static void main(String [] args) throws  Exception{
        SpringApplication.run(RunningLocationDistributionApplication.class, args);
    }
}
