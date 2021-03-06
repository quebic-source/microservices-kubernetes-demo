package com.qb.poc.reviews.controller;

import com.qb.poc.reviews.App;
import com.qb.poc.reviews.config.AppConfig;
import com.qb.poc.reviews.exception.ResourceNotFoundException;
import com.qb.poc.reviews.model.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
@RequestMapping("/k8")
public class K8DemoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(K8DemoController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private AppConfig appConfig;

    @GetMapping("/discovery")
    public List<List<ServiceInstance>> discovery() {
        LOGGER.info("discovery");
        List<List<ServiceInstance>> services = new ArrayList<>();

        discoveryClient.getServices().forEach(s->{
            services.add(discoveryClient.getInstances(s));
        });

        return services;
    }

    @GetMapping("/appConfig")
    public String appConfig() {
        LOGGER.info("appConfig");
        return appConfig.toString();
    }
}
