package com.qb.poc.reviews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
// since use Eureka for now disable k8 discovery client.
//@EnableDiscoveryClient
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
