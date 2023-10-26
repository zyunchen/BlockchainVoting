package com.scu275.invoicemanagement.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.scu275.invoicemanagement.entity") // 指定JPA仓库的位置
@EntityScan(basePackages = "com.scu275.invoicemanagement.entity")
public class JPAConfig {

}
