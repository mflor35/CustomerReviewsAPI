package com.udacity.course3.reviews.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

/**
 * Comment
 */
@Entity
public class Comment {

    @ManyToOne
    private Review review;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    @NotEmpty(message = "Please provide a comment's title")
    private String title;
    @Column(name = "body")
    @NotEmpty(message = "Please provide a comment's body")
    private String body;

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param review the review to set
     */
    public void setReview(Review review) {
        this.review = review;
    }
    
    /**
     * @return the review
     */
    public Review getReview() {
        return review;
    }
}