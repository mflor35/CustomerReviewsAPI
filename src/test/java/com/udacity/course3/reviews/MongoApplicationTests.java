package com.udacity.course3.reviews;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import com.udacity.course3.reviews.repository.CommentMongoRepository;
import com.udacity.course3.reviews.repository.ReviewMongoRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.udacity.course3.reviews.entity.MongoComment;
import com.udacity.course3.reviews.entity.MongoReview;
/**
 * MongoApplicationTests
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class MongoApplicationTests {
    
    @Autowired private ReviewMongoRepository reviewMongoRepo;
    @Autowired private CommentMongoRepository commentMongoRepo;

    @Test
    public void assertInjectedComponentsNotNull() {
        assertNotNull(reviewMongoRepo);
        assertNotNull(commentMongoRepo);
    }

    @Test
    public void testMongoRepos() {
        MongoReview review = new MongoReview("Test Review Title", "Test Review Body", 1);
        MongoComment comment = new MongoComment("Test Comment Title", "Test Comment Body", 1);

        reviewMongoRepo.save(review);
        commentMongoRepo.save(comment);

        Optional<MongoReview> optReview = reviewMongoRepo.findById(1);
        Optional<MongoComment> optComment = commentMongoRepo.findById(1);

        assertTrue(optComment.isPresent());
        assertTrue(optReview.isPresent());
    }

}