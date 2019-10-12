package com.udacity.course3.reviews.repository;

import java.util.List;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ReviewRepository
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByProduct(Product product);
}