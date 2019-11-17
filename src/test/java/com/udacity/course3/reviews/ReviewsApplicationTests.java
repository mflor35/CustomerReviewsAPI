package com.udacity.course3.reviews;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.notNull;

import java.util.Optional;

import javax.sql.DataSource;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewsApplicationTests {

    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private TestEntityManager testEntityManager;

    @Autowired private ReviewRepository reviewRepository;
	@Autowired private ProductRepository productRepository;
	@Autowired private CommentRepository commentRepository;


  @Test
  public void injectedComponentsAreNotNull(){
    assertNotNull(dataSource);
    assertNotNull(jdbcTemplate);
    assertNotNull(testEntityManager);
    assertNotNull(reviewRepository);
    assertNotNull(productRepository);
    assertNotNull(commentRepository);
  }
	@Test
    public void testAddingProductReviewComment() {
        Product product = new Product("Test Product Name", "Test Product Description", 1.0);
        Review review = new Review("Test Review Title", "Test Review Body",1);
        Comment comment = new Comment("Test Comment Title", "Test Comment Body",1);
        Integer id = testEntityManager.persistAndGetId(product, Integer.class);
        review.setProduct(product);
        comment.setReview(review);
        productRepository.save(product);
        reviewRepository.save(review);
        commentRepository.save(comment);
        assertNotNull(id);
        assertNotNull(product);

        Optional<Product> optProduct = productRepository.findByName("Test Product Name");
        Optional<Review> optReview = reviewRepository.findById(1);
        Optional<Comment> optComment = commentRepository.findById(1);
        
        assertTrue(optComment.isPresent());
        assertTrue(optReview.isPresent());
        assertTrue(optProduct.isPresent());
        assertEquals(optProduct.get().getDescription(), product.getDescription());
        assertEquals(optProduct.get().getPrice(), product.getPrice());
	}


}