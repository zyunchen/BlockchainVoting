package com.scu275.invoicemanagement.dto;

import com.scu275.invoicemanagement.entity.Customer;
import com.scu275.invoicemanagement.entity.User;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

@Data
public class InvoiceDto {
//    private Customer customerId;

    private String productDescription;

    private int quantity;

    private double price;
    private double paidAmount;

    private double tax;

    private Date dueDate;

    private Date creationDate;

    private Date modificationDate;

    private Long createUserId;

    private Long customerId;
}
