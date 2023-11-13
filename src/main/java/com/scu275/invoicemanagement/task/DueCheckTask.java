package com.scu275.invoicemanagement.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DueCheckTask {
//    @Scheduled(cron = "0 0 12 * * ?")
    @Scheduled(cron = "*/30 * * * * ?")

    public void dueCheck() {
        System.out.println("begin to check due date");
    }
}
