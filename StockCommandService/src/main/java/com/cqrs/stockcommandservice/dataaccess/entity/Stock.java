package com.cqrs.stockcommandservice.dataaccess.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document( collection = "stock")
@Data
@AllArgsConstructor
public class Stock {

  @Id
  private long  prodNum;
  private int quantity;
}
