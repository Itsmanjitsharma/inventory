package com.myshop.api.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshop.api.dtos.BillingDto;
import com.myshop.api.entity.Billing;
import com.myshop.api.service.BillingService;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@RestController
@RequestMapping("/api/bills")
public class BillingController {
    private static final Logger logger = LoggerFactory.getLogger(BillingController.class);

    @Autowired
    private BillingService billingService;


    @PostMapping
    public ResponseEntity<Billing> generateBill(@RequestBody BillingDto bill, @RequestParam Double totalAmount) {
        logger.info("Generating bill for products: {}", bill.getProducts());
        Billing billing = billingService.generateBill(bill, totalAmount);
        return new ResponseEntity<>(billing, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BillingDto>> getAllBills() {
            logger.info("fetching all bills details");
            List<BillingDto> bills = billingService.getAllBills();
            logger.info("fetching all bills details :{}",bills);
            return new ResponseEntity<>(bills, HttpStatus.OK);
    
    }

    @GetMapping("/{billId}")
    public ResponseEntity<Billing> getBill(@PathVariable Long billId) {
        logger.info("Fetching bill with ID: {}", billId);
        Billing billing = billingService.getBill(billId);
        return new ResponseEntity<>(billing, HttpStatus.OK);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Billing>> getBillsForDateRange(@RequestParam Date startDate,
            @RequestParam Date endDate) {
        logger.info("Fetching bills for date range: {} - {}", startDate, endDate);
        List<Billing> bills = billingService.getBillsForDateRange(startDate, endDate);
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }
}
