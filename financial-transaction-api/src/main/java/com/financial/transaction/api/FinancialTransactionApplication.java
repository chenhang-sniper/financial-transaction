package com.financial.transaction.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {
        "com.financial.transaction.*"
})
@EnableCaching
public class FinancialTransactionApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinancialTransactionApplication.class, args);
    }
}