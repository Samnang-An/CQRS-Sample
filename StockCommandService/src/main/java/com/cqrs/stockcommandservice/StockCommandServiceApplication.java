package com.cqrs.stockcommandservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StockCommandServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(StockCommandServiceApplication.class, args);
  }

}
