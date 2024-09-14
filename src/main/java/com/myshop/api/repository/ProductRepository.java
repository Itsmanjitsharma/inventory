package com.myshop.api.repository;

// ProductRepository.java

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myshop.api.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String string);
}