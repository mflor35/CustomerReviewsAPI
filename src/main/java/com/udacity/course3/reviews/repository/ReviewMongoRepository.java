package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.MongoReview;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * ReviewMongoRepository
 */
public interface ReviewMongoRepository extends MongoRepository<MongoReview, Integer>{

    
}