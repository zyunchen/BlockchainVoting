package com.scu275.invoicemanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(updatable = false,nullable = false)
    @Temporal(TemporalType.DATE)
    private Date creationDate;

}
