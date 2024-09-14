package com.myshop.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.myshop.api.controller.ProductController;
import com.myshop.api.entity.Product;
import com.myshop.api.service.ProductService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    public void testGetAllProducts() {
        // Arrange
        List<Product> products = Arrays.asList(new Product(1L, "Product 1", "Description 1", 10.99,12.99, 10),
                new Product(2L, "Product 2", "Description 2", 20.99,22.99, 20));
        when(productService.getAllProducts()).thenReturn(products);

        // Act
        ResponseEntity<List<Product>> response = productController.getAllProducts();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
        verify(productService).getAllProducts();
    }

    @Test
    public void testGetProductById() {
        // Arrange
        Product product = new Product(1L, "Product 1", "Description 1", 10.99, 12.99 ,10);
        when(productService.getProductById(1L)).thenReturn(product);

        // Act
        ResponseEntity<Product> response = productController.getProductById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService).getProductById(1L);
    }

    @Test
    public void testCreateProduct() {
        // Arrange
        List<Product> products = Arrays.asList(new Product(null, "Product 1", "Description 1", 10.99,12.99, 10),
                new Product(null, "Product 2", "Description 2", 20.99,22.99, 20));
        when(productService.createProduct(products)).thenReturn(products);

        // Act
        ResponseEntity<List<Product>> response = productController.createProduct(products);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(products, response.getBody());
        verify(productService).createProduct(products);
    }

    @Test
    public void testUpdateProduct() {
        // Arrange
        Product product = new Product(1L, "Product 1", "Description 1", 10.99, 12.99,10);
        when(productService.updateProduct(1L, product)).thenReturn(product);

        // Act
        ResponseEntity<Product> response = productController.updateProduct(1L, product);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService).updateProduct(1L, product);
    }

    @Test
    public void testDeleteProduct() {
        // Act
        ResponseEntity<HttpStatus> response = productController.deleteProduct(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService).deleteProduct(1L);
    }
}