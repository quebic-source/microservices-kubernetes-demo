package com.qb.poc.reviews.controller;

import com.qb.poc.reviews.exception.ResourceNotFoundException;
import com.qb.poc.reviews.model.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewsController.class);

    private final Map<Integer, Review> reviews;

    public ReviewsController() {
        reviews = new HashMap<>();
        IntStream.range(1, 3).forEach(
                i -> reviews.put(i, new Review(i, "review-" + i)));
    }

    @GetMapping
    public List<Review> findAll() {
        LOGGER.info("findAll");
        return getAll().collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Review findById(@PathVariable("id") int id) {
        LOGGER.info("findById {}", id);
        return getById(id);
    }

    private Stream<Review> getAll() {
        return reviews.entrySet().stream().map(r -> r.getValue());
    }

    private Review getById(int id) {
        return Optional.ofNullable(reviews.get(id)).orElseThrow(ResourceNotFoundException::new);
    }
}
