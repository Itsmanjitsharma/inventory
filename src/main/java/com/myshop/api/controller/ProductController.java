package com.myshop.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myshop.api.entity.Product;
import com.myshop.api.service.ProductService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        logger.debug("Getting all products");
        List<Product> products = productService.getAllProducts();
        logger.info("Returning all products: {}", products);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        logger.debug("Getting product id: {}", id);
        Product product = productService.getProductById(id);
        logger.info("Returning product id: {}: {}", id, product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<Product>> createProduct(@RequestBody @Valid List<Product> product) {
        logger.debug("Creating new products: {}", product);
        List<Product> newProduct = productService.createProduct(product);
        logger.info("Returning new products: {}", newProduct);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        logger.debug("Updating product id: {}", id);
        Product updatedProduct = productService.updateProduct(id, product);
        logger.info("Returning updated product id: {}: {}", id, updatedProduct);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
        logger.debug("Deleting product id: {}", id);
        productService.deleteProduct(id);
        logger.info("Deleted product id: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
