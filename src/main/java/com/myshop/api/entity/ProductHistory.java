package com.myshop.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.*;

@Entity
@Data
@NoArgsConstructor

public class ProductHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long productHistoryId;

    long productId;
    String name;
    Date updateDate;
    int quantity;
    double sellPrice;
    double purchagePrice;
    String pgroup;
    

    public ProductHistory(long productId, String name, Date updateDate, int quantity, double sellPrice,
    double purchagePrice,String pgroup) {
        this.productId = productId;
        this.name = name;
        this.updateDate = updateDate;
        this.quantity = quantity;
        this.sellPrice = sellPrice;
        this.purchagePrice = purchagePrice;
        this.pgroup = pgroup;
    }
       
}
