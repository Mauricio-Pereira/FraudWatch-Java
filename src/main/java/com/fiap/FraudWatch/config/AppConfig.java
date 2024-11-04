package com.fiap.FraudWatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate RestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ExecutorService ExecutorService() {
        return Executors.newFixedThreadPool(10);
    }
}
