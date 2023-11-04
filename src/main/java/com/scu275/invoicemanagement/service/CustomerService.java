package com.scu275.invoicemanagement.service;

import com.scu275.invoicemanagement.common.result.Result;
import com.scu275.invoicemanagement.dto.CustomerDto;
import com.scu275.invoicemanagement.entity.Customer;
import com.scu275.invoicemanagement.entity.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Result<String> createCustomer(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customerRepository.save(customer);
        return Result.success("create customer successfully");

    }

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }
}
