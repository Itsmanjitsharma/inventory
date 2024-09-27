package com.myshop.api.dtos;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long productid;
    
    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "Description name is required")
    private String description;

    @NotBlank(message = "Price name is required")
    private Double sellPrice;

    @NotBlank(message = "Quantity name is required")
    private int quantity;

    
}