package com.myshop.api;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.myshop.api.controller.BillingController;
import com.myshop.api.dtos.BillingDto;
import com.myshop.api.entity.Billing;
import com.myshop.api.service.BillingService;

import java.util.Arrays;
import java.util.List;
import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BillingControllerTests {

    @Mock
    private BillingService billingService;

    @InjectMocks
    private BillingController billingController;

    @Test
    public void testGenerateBill() {
        // Arrange
        BillingDto bill = new BillingDto();
        //bill.setTotalAmount(100.0);

        Billing billing = new Billing();
        billing.setBillId(1L);
        billing.setTotalAmount(100.0);

        when(billingService.generateBill(bill,100.00)).thenReturn(billing);

        // Act
        ResponseEntity<Billing> result = billingController.generateBill(bill, 100.0);

        // Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(billing, result.getBody());
        verify(billingService).generateBill(bill,100.00);
    }

    @Test
    public void testGetAllBills() {
        // Arrange
        LocalDate billDate = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(billDate);

        List<Billing> bills = Arrays.asList(new Billing(1L,sqlDate,100.00), new Billing(2L,sqlDate, 200.0));
       // when(billingService.getAllBills()).thenReturn(bills);

        // Act
        ResponseEntity<List<BillingDto>> result = billingController.getAllBills();

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(bills, result.getBody());
        verify(billingService).getAllBills();
    }

    @Test
    public void testGetBill() {
        // Arrange
        LocalDate billDate = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(billDate);
        Billing billing = new Billing(1L,sqlDate,100.00);
        when(billingService.getBill(1L)).thenReturn(billing);

        // Act
        ResponseEntity<Billing> result = billingController.getBill(1L);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(billing, result.getBody());
        verify(billingService).getBill(1L);
    }

    @Test
    public void testGetBillsForDateRange() {
        // Arrange
       
        LocalDate currentDate = LocalDate.now();
LocalDate oneMonthBack = currentDate.minusMonths(1);

java.sql.Date currentSqlDate = java.sql.Date.valueOf(currentDate);
java.sql.Date oneMonthBackSqlDate = java.sql.Date.valueOf(oneMonthBack);




List<Billing> bills = Arrays.asList(new Billing(1L,currentSqlDate, 100.0), new Billing(2L,oneMonthBackSqlDate, 200.0));
        when(billingService.getBillsForDateRange(currentSqlDate, oneMonthBackSqlDate)).thenReturn(bills);

        // Act
        ResponseEntity<List<Billing>> result = billingController.getBillsForDateRange(currentSqlDate, oneMonthBackSqlDate);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(bills, result.getBody());
        verify(billingService).getBillsForDateRange(currentSqlDate, oneMonthBackSqlDate);
    }
}


