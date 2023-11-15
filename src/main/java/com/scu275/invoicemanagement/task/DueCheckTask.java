package com.scu275.invoicemanagement.task;

import com.scu275.invoicemanagement.entity.Invoice;
import com.scu275.invoicemanagement.entity.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class DueCheckTask {

    @Autowired
    private InvoiceRepository invoiceRepository;
//    @Scheduled(cron = "0 0 12 * * ?")
    @Scheduled(cron = "*/30 * * * * ?")

    public void dueCheck() {
        System.out.println("begin to check due date");
//        Date date = new Date();
//        List<Invoice> invoices = invoiceRepository.findInvoicesDueTodayOrEarlier(date);
//        Date currentDate = new Date();
//
//        for (Invoice invoice : invoices) {
//            if (invoice.getDueDate().before(currentDate) && InvoiceStatus.PENDING.equals(invoice.getStatus())) {
//                invoice.setStatus(InvoiceStatus.OVERDUE);
//                invoiceService.saveInvoice(invoice);
//            }
//        }
    }
}
