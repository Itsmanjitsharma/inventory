package com.myshop.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import com.myshop.api.entity.Product;
import com.myshop.api.exception.ProductException;
import com.myshop.api.repository.ProductRepository;
import com.myshop.api.service.ProductService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testGetAllProducts() {
        // Arrange
        List<Product> products = Arrays.asList(new Product(1L, "Product 1", "Description 1", 10.99, 12.99,10),
                new Product(2L, "Product 2", "Description 2", 20.99, 22.99,20));
        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<Product> result = productService.getAllProducts();

        // Assert
        assertEquals(products, result);
        verify(productRepository).findAll();
    }

    @Test
    public void testGetProductById() {
        // Arrange
        Product product = new Product(1L, "Product 1", "Description 1", 10.99, 12.99,10);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Act
        Product result = productService.getProductById(1L);

        // Assert
        assertEquals(product, result);
        verify(productRepository).findById(1L);
    }

    @Test
    public void testGetProductByIdNotFound() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ProductException.class, () -> productService.getProductById(1L));
        verify(productRepository).findById(1L);
    }

    @Test
    public void testCreateProduct() {
        // Arrange
        List<Product> products = Arrays.asList(new Product(null, "Product 1", "Description 1", 10.99, 12.99,10),
                new Product(null, "Product 2", "Description 2", 20.99, 22.99,20));
        when(productRepository.saveAll(products)).thenReturn(products);

        // Act
        List<Product> result = productService.createProduct(products);

        // Assert
        assertEquals(products, result);
        verify(productRepository).saveAll(products);
    }

    @Test
    public void testUpdateProduct() {
        // Arrange
        Product product = new Product(1L, "Product 1", "Description 1", 10.99, 12.99,10);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Act
        Product result = productService.updateProduct(1L, product);

        // Assert
        assertEquals(product, result);
        verify(productRepository).findById(1L);
        verify(productRepository).save(product);
    }

    @Test
    public void testUpdateProductNotFound() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ProductException.class, () -> productService.updateProduct(1L, new Product()));
        verify(productRepository).findById(1L);
    }

    @Test
    public void testDeleteProduct() {
        // Act
        productService.deleteProduct(1L);

        // Assert
        verify(productRepository).deleteById(1L);
    }

    @Test
    public void testDeleteProductNotFound() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EmptyResultDataAccessException.class, () -> productService.deleteProduct(1L));
        verify(productRepository).findById(1L);
    }

    @Test
    public void testFieldValidation_NameIsNull() {
        // Arrange
        Product product = new Product(null, null, "Description 1", 10.99,12.99, 10);

        // Act and Assert
        assertThrows(ProductException.class, () -> productService.fieldValidation(product));
    }

    @Test
    public void testFieldValidation_NameIsEmpty() {
        // Arrange
        Product product = new Product(null, "", "Description 1", 10.99, 12.99,10);

        // Act and Assert
        assertThrows(ProductException.class, () -> productService.fieldValidation(product));
    }

    @Test
    public void testFieldValidation_PriceIsNull() {
        // Arrange
        Product product = new Product(null, "Product 1", "Description 1", null,null, 10);

                // Act and Assert
                assertThrows(ProductException.class, () -> productService.fieldValidation(product));
            }
        
            @Test
            public void testFieldValidation_PriceIsNaN() {
                // Arrange
                Product product = new Product(null, "Product 1", "Description 1", Double.NaN,Double.NaN, 10);
        
                // Act and Assert
                assertThrows(ProductException.class, () -> productService.fieldValidation(product));
            }
        
            @Test
            public void testFieldValidation_QuantityIsZero() {
                // Arrange
                Product product = new Product(null, "Product 1", "Description 1", 10.99, 12.99,0);
        
                // Act and Assert
                assertThrows(ProductException.class, () -> productService.fieldValidation(product));
            }
        
            @Test
            public void testFieldValidation_QuantityIsNaN() {
                // Arrange
                Product product = new Product(null, "Product 1", "Description 1", 10.99, 12.99,0);
                // Act and Assert
                assertThrows(ProductException.class, () -> productService.fieldValidation(product));
            }
        }