package com.myshop.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myshop.api.dtos.ProductSales;

@Repository
public interface ProductSaleRepository extends JpaRepository<ProductSales,Long>{

    List<ProductSales> findAllByBillIdIn(List<Long> billIds);
      
}
