package com.myshop.api.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myshop.api.entity.ProductHistory;
import com.myshop.api.repository.ProductHistoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductHistoryService {
    
    private static final Logger logger = LoggerFactory.getLogger(ProductHistoryService.class);
    
    @Autowired
    private ProductHistoryRepository productHistoryRepository;
    
   public List<ProductHistory> findByProductName(String productName){
        logger.info("Finding product history by name: {}", productName);
        return productHistoryRepository.findByName(productName);

    }
    
   public List<ProductHistory> findByProductId(Long productId){
        logger.info("Finding product history by id: {}", productId);
        return productHistoryRepository.findByProductId(productId);
    }

public List<ProductHistory> findAll() {
     logger.info("Finding all product history");
     return productHistoryRepository.findAll(); 
  }
}

