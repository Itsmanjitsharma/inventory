package com.myshop.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myshop.api.entity.ProductHistory;

public interface ProductHistoryRepository extends JpaRepository<ProductHistory, Long> {

    List<ProductHistory> findByProductId(Long productId);

    List<ProductHistory> findByName(String productName);
    
}
