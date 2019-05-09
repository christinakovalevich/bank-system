package com.kovalevich.banksystembgpb

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BankSystemBgpbApplication {

    private final static Logger log = LoggerFactory.getLogger(this.class)
    private final static url = 'http://localhost:8080/bank-system'

    static void main(String[] args) {
        SpringApplication.run(BankSystemBgpbApplication.class, args)
        log.info(url)
    }

}
