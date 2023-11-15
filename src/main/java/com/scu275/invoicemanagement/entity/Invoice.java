package com.scu275.invoicemanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoiceId;
    @ManyToOne
    private Customer customer;

    private String productDescription;

    private int quantity;

    private double price;

    private double tax;

    private String status;


    @CreatedDate
    @Column(updatable = false,nullable = false)
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @LastModifiedDate
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date modificationDate;

    @ManyToOne
    private User createUser;




}
