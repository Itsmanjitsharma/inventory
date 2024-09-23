package com.myshop.api.entity;

import javax.validation.constraints.NotBlank;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productid;

    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "Product name is required")
    private String pgroup;

    @NotBlank(message = "Description name is required")
    private String description;

    @NotBlank(message = "Purchase Price name is required")
    private Double purchagePrice;

    @NotBlank(message = "Sell Price name is required")
    private Double sellPrice;


    public Product(String name, String description, Double purchagePrice, Double sellPrice,String pgroup,String imageUrl) {
        this.name = name;
        this.description = description;
        this.purchagePrice = purchagePrice;
        this.sellPrice = sellPrice;
        this.pgroup = pgroup;
        this.imageUrl = imageUrl;
    }

    @NotBlank(message = "Quantity name is required")
    private int quantity;

    @NotBlank(message = "Product Image is required")
    private String imageUrl;

    
}