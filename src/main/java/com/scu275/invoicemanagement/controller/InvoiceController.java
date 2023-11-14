package com.scu275.invoicemanagement.controller;

import com.scu275.invoicemanagement.common.result.Result;
import com.scu275.invoicemanagement.dto.InvoiceDto;
import com.scu275.invoicemanagement.entity.Invoice;
import com.scu275.invoicemanagement.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Operation(summary = "get Invoice by Id", description = "get invoice by id ")
    public  Result<Invoice> getInvocieById(@PathVariable Long id){
        return invoiceService.getInvoiceById(id);
    }

    @GetMapping("/")
    @Operation(summary = "get all invoices", description = "get all invoices ")
    public List<Invoice> getInvocies(){
        return invoiceService.getAll();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "modify invoice by id ", description = "modify invoice by id ")
    public  Result<String> modifyInvoice(@PathVariable Long id, @RequestBody InvoiceDto invoiceDto) {
        return invoiceService.modifyInvoice(id, invoiceDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Invoice", description = "Delete invoice by id ")
    public  Result<String> deleteInvoice(@PathVariable Long id){
        return invoiceService.deleteInvoice(id);
    }

}
