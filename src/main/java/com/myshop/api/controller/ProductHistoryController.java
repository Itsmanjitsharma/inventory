package com.myshop.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myshop.api.entity.ProductHistory;
import com.myshop.api.service.ProductHistoryService;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/history")
public class ProductHistoryController {

    private static final Logger logger = LoggerFactory.getLogger(ProductHistoryController.class);

    @Autowired
    ProductHistoryService productHistoryService;

    @GetMapping("/productname/{productName}")
    public List<ProductHistory> getProductHistoryByProductName(@PathVariable String productName) {
        logger.info("Get product history by name: {}", productName);
        List<ProductHistory> histories = productHistoryService.findByProductName(productName);
        logger.debug("Found {} product histories", histories.size());
        return histories;
    }
    
    @GetMapping("/productid/{productId}")
    public List<ProductHistory> getProductHistoryByProductId(@PathVariable Long productId) {
        logger.info("Get product history by id: {}", productId);
        List<ProductHistory> histories = productHistoryService.findByProductId(productId);
        logger.debug("Found {} product histories", histories.size());
        return histories;
    }
    @GetMapping
    public List<ProductHistory> getAllProductHistory() {
        logger.info("Get all product histories");
        List<ProductHistory> histories = productHistoryService.findAll();
        logger.debug("Found {} product histories", histories.size());
        return histories;
    }
    
}

