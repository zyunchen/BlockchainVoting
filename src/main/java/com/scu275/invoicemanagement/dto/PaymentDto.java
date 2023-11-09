package com.scu275.invoicemanagement.dto;

import com.scu275.invoicemanagement.entity.Invoice;
import lombok.Data;

@Data
public class PaymentDto {

    private Long invoiceId;

    private double amount;

}
