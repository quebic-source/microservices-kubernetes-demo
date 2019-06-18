package com.qb.poc.reviews.model;

public class Review {
    private final int id;
    private final String text;
    private final String version;

    public Review(int id, String text) {
        this.id = id;
        this.text = text;
        this.version = System.getenv("VERSION");
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getVersion() {
        return version;
    }
}
