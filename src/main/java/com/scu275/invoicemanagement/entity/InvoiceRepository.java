package com.scu275.invoicemanagement.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    public List<Invoice> findByCreateUser_uId(long uid);

    @Modifying
    @Transactional
    @Query("UPDATE Invoice i SET i.status = :status WHERE i.invoiceId = :invoiceId")
    void updateStatusByInvoiceId(@Param("invoiceId") Long invoiceId, @Param("status") String status);

    @Query("SELECT i FROM Invoice i WHERE i.dueDate <= CURRENT_DATE AND (i.status IS NULL OR i.status NOT IN (:overdue, :completed))")

    List<Invoice> findDueInvoices(@Param("overdue") String overdue, @Param("completed") String completed);


}
