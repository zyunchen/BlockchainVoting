package com.scu275.invoicemanagement.entity;

import com.scu275.invoicemanagement.dto.PaymentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    public List<Payment> findAllByInvoiceInvoiceId(Long invoiceId);

    @Transactional
    public void deleteByInvoiceInvoiceId(Long invoiceId);
}
