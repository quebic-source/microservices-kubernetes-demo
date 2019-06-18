package com.qb.poc.products.controller;

import com.qb.poc.products.exception.ResourceNotFoundException;
import com.qb.poc.products.model.Product;
import com.qb.poc.products.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsController.class);

    private RestTemplate restTemplate;
    private final Map<Integer, Product> products;

    @Autowired
    public ProductsController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        products = new HashMap<>();
        IntStream.range(1, 3)
                .forEach(i -> products.put(i, new Product(i, "product-" + i)));
    }

    @GetMapping
    public List<Product> findAll() {
        LOGGER.info("findAll");
        return getAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") int id) {
        LOGGER.info("findById {}", id);
        return getById(id);
    }

    private List<Product> getAll() {
        return products.entrySet().stream()
                .map(p -> p.getValue())
                .map(p -> setReview(p))
                .collect(Collectors.toList());
    }

    private Product getById(int id) {
        return Optional.ofNullable(setReview(products.get(id))).orElseThrow(ResourceNotFoundException::new);
    }

    private Product setReview(Product product){
        product.setReview(getReviewById(product.getId()));
        return product;
    }

    private Review getReviewById(int id) {
        String url = String.format("http://%s:%s/reviews/%d", getReviewServiceHost(), getReviewServicePort(), id);

        try {
            ResponseEntity<Review> response = restTemplate.getForEntity(url, Review.class);
            return response.getStatusCode().is2xxSuccessful() ? response.getBody() : null;
        } catch (RestClientException e) {
            return null;
        }
    }

    private String getReviewServiceHost(){
        String host = System.getenv("REVIEWS_SVC_HOST");
        return StringUtils.isEmpty(host) ? "localhost" : host;
    }

    private String getReviewServicePort(){
        String port = System.getenv("REVIEWS_SVC_PORT");
        return StringUtils.isEmpty(port) ? "8080" : port;
    }
}