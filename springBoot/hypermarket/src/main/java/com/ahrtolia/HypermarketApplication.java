package com.ahrtolia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value="com.ahrtolia.dao")
@SpringBootApplication
public class HypermarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(HypermarketApplication.class, args);
    }
}
