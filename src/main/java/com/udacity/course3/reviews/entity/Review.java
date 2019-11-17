package com.udacity.course3.reviews.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

/**
 * Review
 */
@Entity
public class Review {

        @ManyToOne
        private Product product;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Column(name = "title")
        @NotEmpty(message = "Please provide a review's title")
        private String title;
        @Column(name = "body")
        @NotEmpty(message = "Please provide a review's body")
        private String body;

        public Review() { }
        public Review(Integer id) {
            this.id = id;
        }
        public Review( String title, String body, Integer id) {
            this.body = body;
            this.title = title;
            this.id = id;
        }

        /**
         * @param body the body to set
         */
        public void setBody(String body) {
            this.body = body;
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
         * @return the product
         */
        public Product getProduct() {
            return product;
        }

        /**
         * @param product the product to set
         */
        public void setProduct(Product product) {
            this.product = product;
        }

        /**
         * @param title the title to set
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * @return the title
         */
        public String getTitle() {
            return title;
        }
        
}