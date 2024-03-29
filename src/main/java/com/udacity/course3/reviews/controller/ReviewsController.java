package com.udacity.course3.reviews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.udacity.course3.reviews.entity.MongoReview;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewMongoRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    // DONE: Wire JPA repositories here
    private ReviewRepository reviewRepository;
    private ProductRepository productRepository;
    private ReviewMongoRepository reviewMongoRepo;

    @Autowired
    ReviewsController(ReviewRepository reviewRepository, ProductRepository productRepository, 
    ReviewMongoRepository reviewMongoRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
        this.reviewMongoRepo = reviewMongoRepository; 
    }

    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation. 2.
     * Check for existence of product. 3. If product not found, return NOT_FOUND. 4.
     * If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MongoReview> createReviewForProduct(@Valid @PathVariable("productId") Integer productId,
            @RequestBody Review review) {
        Optional<Product> oProduct = productRepository.findById(productId);
        
        if(!oProduct.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        review.setProduct(oProduct.get());
        review = reviewRepository.save(review);
        MongoReview mongoReview = new MongoReview(review.getTitle(), review.getBody(), review.getId());

        return ResponseEntity.ok(reviewMongoRepo.save(mongoReview));

    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MongoReview>> listReviewsForProduct(@Valid @PathVariable("productId") Integer productId) {
        Product reviewedProduct = new Product(productId);
        List<MongoReview> mongoReviews = new ArrayList<>();
        for (Review review : reviewRepository.findByProduct(reviewedProduct)) {
            Optional<MongoReview> oReviewMongo = reviewMongoRepo.findById(review.getId());
            if(oReviewMongo.isPresent()) {
                mongoReviews.add(oReviewMongo.get());
            }
        }

        return ResponseEntity.ok(mongoReviews);
    }
}