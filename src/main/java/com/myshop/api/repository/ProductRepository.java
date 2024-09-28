package com.myshop.api.repository;

// ProductRepository.java

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myshop.api.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String string);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.quantity = :quantity WHERE p.productid = :productId")
    int updateQuantity(int quantity, Long productId);
}