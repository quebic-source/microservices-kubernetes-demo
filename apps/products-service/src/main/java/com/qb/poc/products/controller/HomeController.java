package com.qb.poc.products.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/")
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsController.class);

    @GetMapping
    public String findAll() {
        LOGGER.info("home");
        return "products-service";
    }

}