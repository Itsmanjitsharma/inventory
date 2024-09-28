package com.myshop.api.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myshop.api.dtos.BillingDto;
import com.myshop.api.dtos.ProductDto;
import com.myshop.api.dtos.ProductSales;
import com.myshop.api.entity.Billing;
import com.myshop.api.entity.Product;
import com.myshop.api.exception.ProductException;
import com.myshop.api.repository.BillingRepository;
import com.myshop.api.repository.ProductSaleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BillingService {
    private static final Logger logger = LoggerFactory.getLogger(BillingService.class);

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private ProductSaleRepository productSaleRepository;
    
    @Autowired
    private ProductService productService;
        
    public Billing generateBill(BillingDto bill,Double amount) {
        logger.info("Generating bill for products: {}", bill.getProducts());
        List<ProductSales> products = new ArrayList<ProductSales>();
        LocalDate billDate = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(billDate);
        Billing billing = billingRepository.save(new Billing(sqlDate,amount));
        for (ProductDto productDto : bill.getProducts()) {
            Product existingProduct = productService.getProductById(productDto.getProductid());
            if (existingProduct != null) {
                if (existingProduct.getQuantity() >= productDto.getQuantity()) {
                    existingProduct.setQuantity(existingProduct.getQuantity() - productDto.getQuantity());
                    productService.updateProductAfterBilling(existingProduct.getProductid(), existingProduct);
                    products.add(new ProductSales(productDto.getName(),
                                                  productDto.getQuantity(),
                                                  productDto.getSellPrice(),
                                                  sqlDate,
                                                  billing.getBillId()));
                } else {
                    throw new ProductException("1001","Insufficient quantity for product ");
                }
                productSaleRepository.saveAll(products);
            } else {
                // Handle product not found
                throw new ProductException("1001","Product not found with ID ");
            }
        }
        logger.info("Bill generated with Id: {}", billing.getBillId());
        return billing;
    }
    
    public  List<BillingDto> getAllBills() {
        logger.info("Fetching all bills");
        List<Billing> billings =  billingRepository.findAll();
        List<Long> billIds = billings.stream().map(Billing::getBillId).collect(Collectors.toList());
        List<ProductSales> productSales = productSaleRepository.findAllByBillIdIn(billIds);
         List<BillingDto> billingDtos = new ArrayList<BillingDto>();
        for (Billing billing : billings) {
            BillingDto billingDto = new BillingDto();
            billingDto.setBillId(billing.getBillId());
            billingDto.setBillDate(billing.getBillDate());
            billingDto.setTotalAmount(billing.getTotalAmount());
            List<ProductDto> productDtos = new ArrayList<ProductDto>();
            for (ProductSales productSale : productSales) {
                if (productSale.getBillId() == billing.getBillId()) {
                    ProductDto productDto = new ProductDto();
                    productDto.setProductid(productSale.getProduct_sale_id());
                    productDto.setQuantity(productSale.getQuantity());
                    productDto.setSellPrice(productSale.getPrice());
                    productDto.setName(productSale.getProductName());
                    productDtos.add(productDto);
                }
            }
            billingDto.setProducts(productDtos);
            billingDtos.add(billingDto);
        }
        logger.info("Fetched all bills");
        return billingDtos;
    }
    
    public Billing getBill(Long billId) {
        logger.info("Fetching bill with Id: {}", billId);
        return billingRepository.findById(billId).orElseThrow();
    }
    
    public List<Billing> getBillsForDateRange(Date startDate, Date endDate) {
        logger.info("Fetching bills for date range: {} - {}", startDate, endDate);
        return billingRepository.findByBillDateBetween(startDate, endDate);
    }
}
