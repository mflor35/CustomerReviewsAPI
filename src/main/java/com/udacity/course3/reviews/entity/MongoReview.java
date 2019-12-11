package com.udacity.course3.reviews.entity;

import org.springframework.data.annotation.Id;

/**
 * MongoReview
 */
public class MongoReview {

    @Id
    private Integer id;
    private String title;
    private String body;

    public MongoReview() { }

    public MongoReview(String title, String body, Integer id) {
        this.title = title;
        this.body = body;
        this.id = id;
    } 

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }
    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return String.format("{id:%i, title:%s, body:%s }", id, title, body);
    }
}