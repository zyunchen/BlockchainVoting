package com.scu275.invoicemanagement.dto;

import com.scu275.invoicemanagement.entity.Invoice;
import lombok.Data;

import java.util.Date;

@Data
public class PaymentDto {

    private Long invoiceId;

    private double amount;

    private Date creationDate;

}
