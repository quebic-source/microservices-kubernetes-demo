package com.qb.poc.reviews.config;

public class PoolConfig {

    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PoolConfig{" +
                "size=" + size +
                '}';
    }
}
