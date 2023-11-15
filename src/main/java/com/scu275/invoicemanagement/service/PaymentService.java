package com.scu275.invoicemanagement.service;

import com.scu275.invoicemanagement.common.result.Result;
import com.scu275.invoicemanagement.dto.InvoiceDto;
import com.scu275.invoicemanagement.dto.PaymentDto;
import com.scu275.invoicemanagement.entity.*;
import com.scu275.invoicemanagement.mail.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private MailServiceImpl mailService;

    @Autowired
    private CustomerRepository customerRepository;

    public Result<String> createPayment(PaymentDto paymentDto){
        Payment payment = new Payment();
        saveDto(paymentDto, payment);
        List<Payment> payments = paymentRepository.findAllByInvoiceInvoiceId(paymentDto.getInvoiceId());
        double amount = 0.0;
        for ( Payment payment_ : payments) {
            amount += payment_.getAmount();
        }
        Invoice invoice = invoiceRepository.findById(paymentDto.getInvoiceId()).orElseThrow(() -> new RuntimeException("Invoice 不存在"));
        ;
        double amount_left = amount - invoice.getPrice();
        if ( amount_left >= 0) {
            invoiceRepository.updateStatusByInvoiceId(paymentDto.getInvoiceId(), Invoice.InvoiceStatus.PARTIAL.getStatus());
        } else{
            invoiceRepository.updateStatusByInvoiceId(paymentDto.getInvoiceId(), Invoice.InvoiceStatus.COMPLETED.getStatus());
        }

        Mail mail = new Mail();

        Customer customer = invoice.getCustomer();
        if (customer.getEmail() != null) {
            mail.setTo(customer.getEmail());
            mail.setSubject("Payment recipent");
            mail.setContent("Have received your payments and the amout is " + payment.getAmount());
            mailService.sendMail(mail);
        }

        return Result.success("create payment success");
    }

    public List<Payment> getPaymentsByInvoiceId(Long invoiceId){
        return paymentRepository.findAllByInvoiceInvoiceId(invoiceId);

    }


    private void saveDto(PaymentDto paymentDto, Payment payment){
        payment.setInvoice(invoiceRepository.findById(paymentDto.getInvoiceId()).get());
        payment.setAmount(paymentDto.getAmount());
        paymentRepository.save(payment);
    }

}
