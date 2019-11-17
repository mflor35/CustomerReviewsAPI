package com.udacity.course3.reviews.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * Product
 */
@Entity
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    @NotEmpty(message = "Please provide a product's name")
    private String name;
    @Column(name = "description")
    @NotEmpty(message = "Please provide a product's description")
    private String description;
    @Column(name = "price")
    @NotNull(message = "Please provide a product's price")
    private Double price;

    public Product() { }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(String name, String description, Double price) {
        this.price = price;
        this.name = name;
        this.description = description;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }
}