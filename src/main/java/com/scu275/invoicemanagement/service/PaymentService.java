package com.scu275.invoicemanagement.service;

import com.scu275.invoicemanagement.common.result.Result;
import com.scu275.invoicemanagement.dto.InvoiceDto;
import com.scu275.invoicemanagement.dto.PaymentDto;
import com.scu275.invoicemanagement.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public Result<String> createPayment(PaymentDto paymentDto){
        Payment payment = new Payment();
        saveDto(paymentDto, payment);
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
