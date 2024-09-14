package com.myshop.api.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSales {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long product_sale_id;

    String productName;

    int quantity;

    double price;

    Date billedDate;

    Long billId;

    public ProductSales(String productName, int quantity, double price, Date billedDate, Long billId) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.billedDate = billedDate;
        this.billId = billId;
    }

}
