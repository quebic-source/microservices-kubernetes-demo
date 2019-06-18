package com.qb.poc.products.config;

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
