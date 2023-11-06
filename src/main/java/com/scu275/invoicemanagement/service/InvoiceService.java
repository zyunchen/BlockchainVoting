package com.scu275.invoicemanagement.service;

import com.scu275.invoicemanagement.common.result.Result;
import com.scu275.invoicemanagement.dto.InvoiceDto;
import com.scu275.invoicemanagement.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Result<String> createInvocie(InvoiceDto invoiceDto){
        Invoice invoice = new Invoice();
        saveDto(invoiceDto, invoice);
        return Result.success("create invoice success");
    }


    public List<Invoice> getAll(){
        return invoiceRepository.findAll();
    }


    public Result<Invoice> getInvoiceById(Long id){
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if(invoice.isPresent()){
            return Result.success(invoice.get());
        }else{
            return Result.failed("can not find invoice");
        }



    }

    public Result<String> modifyInvoice(Long id, InvoiceDto invoiceDto){
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if(invoice.isPresent()){
            Invoice invoiceEntity = invoice.get();
            saveDto(invoiceDto, invoiceEntity);
            return Result.success("update successfully");
        }else{
            return Result.failed("can not find original invoice");
        }
    }

    public Result<String> deleteInvoice(Long id){
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if(invoice.isPresent()){
            invoiceRepository.deleteById(id);
            return Result.success("Delete successfully");
        }else{
            return Result.failed("can not find original invoice");
        }
    }

    private void saveDto(InvoiceDto invoiceDto, Invoice invoice){
        invoice.setPrice(invoiceDto.getPrice());

        Optional<User> user = userRepository.findById(invoiceDto.getCreateUserId());
        if (user.isPresent()){
            invoice.setCreateUser(user.get());
        }

        Optional<Customer> customer = customerRepository.findById(invoiceDto.getCustomerId());
        if (customer.isPresent()){
            invoice.setCustomer(customer.get());
        }

        invoice.setTax(invoiceDto.getTax());
        invoice.setProductDescription(invoiceDto.getProductDescription());
        invoice.setQuantity(invoiceDto.getQuantity());
        invoiceRepository.save(invoice);
    }
}
