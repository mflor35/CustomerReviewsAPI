package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.MongoComment;

import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * CommentMongoRepository
 */
public interface CommentMongoRepository  extends MongoRepository<MongoComment, Integer>{
    
    
}