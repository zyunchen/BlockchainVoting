package com.scu275.invoicemanagement.mail;

import lombok.Data;

@Data
public class Mail {


    private String to;

    private String subject;

    private String content;

    private String filePath;
}
