package com.scu275.invoicemanagement.task;

import com.scu275.invoicemanagement.entity.Invoice;
import com.scu275.invoicemanagement.entity.InvoiceRepository;
import com.scu275.invoicemanagement.mail.Mail;
import com.scu275.invoicemanagement.service.InvoiceService;
import com.scu275.invoicemanagement.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class DueCheckTask {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private MailService mailService;

    @Scheduled(cron = "*/30 * * * * ?")

    public void dueCheck() {
        System.out.println("begin to check due date");
        Date date = new Date();
        List<Invoice> invoices = invoiceRepository.findDueInvoices(Invoice.InvoiceStatus.OVERDUE.getStatus() , Invoice.InvoiceStatus.COMPLETED.getStatus());
        for (Invoice invoice : invoices) {
            if (!Invoice.InvoiceStatus.COMPLETED.equals(invoice.getStatus())) {
                invoice.setStatus(Invoice.InvoiceStatus.OVERDUE.getStatus());
                System.out.println("inovice id is " + invoice.getInvoiceId() + " is over due and due date is " + invoice.getDueDate());
                invoiceRepository.updateStatusByInvoiceId(invoice.getInvoiceId(), Invoice.InvoiceStatus.OVERDUE.getStatus());
                Mail mail = new Mail();
                mail.setTo(invoice.getCustomer().getEmail());
                mail.setSubject("Payment Over due reminder");
                mail.setContent("Hi, you have invoice not complete payment yet, please check");
                mailService.sendMail(mail);
            }
        }
    }
}
