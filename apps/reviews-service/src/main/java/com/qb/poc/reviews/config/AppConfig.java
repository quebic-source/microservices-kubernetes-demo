package com.qb.poc.reviews.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private String message;

    private PoolConfig pool;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PoolConfig getPool() {
        return pool;
    }

    public void setPool(PoolConfig pool) {
        this.pool = pool;
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                "message='" + message + '\'' +
                ", pool=" + pool +
                '}';
    }
}
