package com.scu275.invoicemanagement.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    public List<Invoice> findByCreateUser_uId(long uid);
}
