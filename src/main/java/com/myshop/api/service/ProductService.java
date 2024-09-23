package com.myshop.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// ProductService.java

import org.springframework.stereotype.Service;

import com.myshop.api.entity.Product;
import com.myshop.api.entity.ProductHistory;
import com.myshop.api.exception.ProductException;
import com.myshop.api.repository.ProductHistoryRepository;
import com.myshop.api.repository.ProductRepository;

import java.util.List;
import java.util.ArrayList;
import java.time.*;


@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final ProductHistoryRepository productHistoryRepository;

    public ProductService(ProductRepository productRepository,ProductHistoryRepository productHistoryRepository) {
        this.productRepository = productRepository;
        this.productHistoryRepository = productHistoryRepository;
    }

    public List<Product> getAllProducts() {
        logger.info("Getting all products");
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        logger.info("Getting product by id: {}", id);
        return productRepository.findById(id).orElse(null);
    }


    public List<Product> createProduct(List<Product> product) {
        logger.info("Creating new products: {}", product);
        LocalDate date = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);

        List<ProductHistory> productHistories = new ArrayList<ProductHistory>();
        List<Product> saveProduct = new ArrayList<Product>();

        for(Product prod : product){
            fieldValidation(prod);
        }
        
        for(Product proudct3 : product){
            Product existingProduct = productRepository.findByName(proudct3.getName());
            Product productAdded = new Product();
            if(existingProduct!=null){
                productAdded =  updateProduct(existingProduct.getProductid(),proudct3);
            }else{
                productAdded =  productRepository.save(proudct3);
            }
            productHistories.add(new ProductHistory(productAdded.getProductid(),productAdded.getName() ,sqlDate, productAdded.getQuantity(),productAdded.getSellPrice(),productAdded.getPurchagePrice(),productAdded.getPgroup()));
            saveProduct.add(productAdded);
        }
        productHistoryRepository.saveAll(productHistories);
        return saveProduct;

    }

    public Product updateProduct(Long id, Product product) {
        logger.info("Updating product id: {}", id);
        fieldValidation(product);
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setProductid(id);
                    existingProduct.setName(product.getName());
                    existingProduct.setDescription(product.getDescription());
                    existingProduct.setPurchagePrice(product.getPurchagePrice());
                    existingProduct.setSellPrice(product.getSellPrice());
                    existingProduct.setPgroup(product.getPgroup());
                    existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new ProductException("2001","Product not found"));
    }


    public void deleteProduct(Long id) {
        logger.info("Deleting product id: {}", id);
        productRepository.deleteById(id);
    }

    public void fieldValidation(Product product){
        logger.info("Validating product: {}", product);
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new ProductException("1001","Product name is required");
        }
        if (product.getPurchagePrice() == null || product.getPurchagePrice().isNaN()) {
            throw new ProductException("1002","Product Purchage Price is required");
        }
        if (product.getQuantity() == 0 || product.getPurchagePrice().isNaN()) {
            throw new ProductException("1003","Product Purchage Price is required");
        }
    }
}
