package com.myshop.api.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myshop.api.entity.Billing;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {

    List<Billing> findByBillDateBetween(Date startDate, Date endDate);
    
}
