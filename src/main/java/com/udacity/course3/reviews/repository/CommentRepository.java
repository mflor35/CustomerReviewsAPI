package com.udacity.course3.reviews.repository;

import java.util.List;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * CommentRepository
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByReview(Review review);
}