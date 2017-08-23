package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Created by XL on 8/21/2017.
 */

@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
public class SimuationServiceApplication {
    public static void main(String[] args){
        SpringApplication.run(SimuationServiceApplication.class, args);
    }

    @Bean
    public AsyncTaskExecutor taskExecutor(){
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        return scheduler;
    }
}
