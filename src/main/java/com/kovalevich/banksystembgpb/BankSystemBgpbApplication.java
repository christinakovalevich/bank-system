package com.kovalevich.banksystembgpb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankSystemBgpbApplication {

    private final static Logger log = LoggerFactory.getLogger(BankSystemBgpbApplication.class);

    private final static String url = "http://localhost:8080/bank-system";

    public static void main(String[] args) {
        SpringApplication.run(BankSystemBgpbApplication.class, args);
        log.debug(url);
    }

}
