package com.udacity.course3.reviews.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.udacity.course3.reviews.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ProductRepository
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByName(String name);
}