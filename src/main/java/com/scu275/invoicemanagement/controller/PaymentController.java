package com.scu275.invoicemanagement.controller;

import com.scu275.invoicemanagement.common.result.Result;
import com.scu275.invoicemanagement.dto.PaymentDto;
import com.scu275.invoicemanagement.entity.Payment;

import com.scu275.invoicemanagement.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @PostMapping("/")
    @Operation(summary = "create", description = "create an payment ")
    public Result<String> createPayment(@RequestBody PaymentDto paymentDto) {
        return paymentService.createPayment(paymentDto);
    }

    @GetMapping("/{invoiceId}")
    @Operation(summary = "get payments by invoice Id", description = "get payments by invoice id ")
    public  List<Payment> getPaymentsByInvoiceId(@PathVariable Long invoiceId){
        return paymentService.getPaymentsByInvoiceId(invoiceId);
    }
}
