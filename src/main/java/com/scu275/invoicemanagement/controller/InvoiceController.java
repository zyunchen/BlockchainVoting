package com.scu275.invoicemanagement.controller;

import com.scu275.invoicemanagement.common.result.Result;
import com.scu275.invoicemanagement.dto.InvoiceDto;
import com.scu275.invoicemanagement.dto.SignUpDto;
import com.scu275.invoicemanagement.entity.Invoice;
import com.scu275.invoicemanagement.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;
    @PostMapping("/")
    @Operation(summary = "create", description = "create an invoice ")
    public Result<String> createInvoice(@RequestBody InvoiceDto invoiceDto) {
        return invoiceService.createInvocie(invoiceDto);
    }

    @GetMapping("/{id}")
    public  Result<Invoice> getInvocieById(@PathVariable Long id){
        return invoiceService.getInvoiceById(id);
    }
}
