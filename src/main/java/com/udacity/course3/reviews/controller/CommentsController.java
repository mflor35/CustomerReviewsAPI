package com.udacity.course3.reviews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.entity.MongoComment;
import com.udacity.course3.reviews.repository.CommentMongoRepository;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    // DONE: Wire needed JPA repositories here
    private ReviewRepository reviewRepository;
    private CommentRepository commentRepository;
    private CommentMongoRepository commentMongoRepo;

    @Autowired
    public CommentsController(ReviewRepository reviewRepository, CommentRepository commentRepository, CommentMongoRepository commentMongoRepository) {
        this.reviewRepository = reviewRepository;
        this.commentRepository = commentRepository;
        this.commentMongoRepo = commentMongoRepository;
    }

    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MongoComment> createCommentForReview(@Valid @PathVariable("reviewId") Integer reviewId, @RequestBody Comment comment) {
        Optional<Review> oReview = reviewRepository.findById(reviewId);
        if(!oReview.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        comment.setReview(oReview.get());
        comment = commentRepository.save(comment);
        MongoComment mongoComment = new MongoComment(comment.getTitle(), comment.getBody(), comment.getId());

        return ResponseEntity.ok(commentMongoRepo.save(mongoComment));
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MongoComment>> listCommentsForReview(@Valid @PathVariable("reviewId") Integer reviewId) {
        List<MongoComment> mongoComments = new ArrayList<>();
        Review commentedReview = new Review(reviewId);
        for (Comment comment : commentRepository.findAllByReview(commentedReview)) {
            Optional<MongoComment> oCommentMongo = commentMongoRepo.findById(comment.getId());
            if (oCommentMongo.isPresent()) {
                mongoComments.add(oCommentMongo.get());
            }
            
        }
        return ResponseEntity.ok(mongoComments);
    }
}