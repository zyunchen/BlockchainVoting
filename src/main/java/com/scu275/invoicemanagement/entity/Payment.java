package com.scu275.invoicemanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentId;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    private double amount;

    private Date paymentDate;

    private String paymentStatus;
}
