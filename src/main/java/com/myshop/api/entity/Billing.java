package com.myshop.api.entity;

import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;
    
    @Column(nullable = false)
    private Date billDate;
    
    private Double totalAmount;
    
    public Billing(Date billDate, Double totalAmount) {
        this.billDate = billDate;
        this.totalAmount = totalAmount;
    }

}
