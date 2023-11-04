package com.scu275.invoicemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class InvoiceManagement {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceManagement.class, args);
    }

}
