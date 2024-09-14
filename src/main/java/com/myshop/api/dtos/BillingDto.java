package com.myshop.api.dtos;

import java.sql.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingDto {
    
    private List<ProductDto> products;
    
    private Date billDate;
    
    private Double totalAmount;

    private Long billId;
}
