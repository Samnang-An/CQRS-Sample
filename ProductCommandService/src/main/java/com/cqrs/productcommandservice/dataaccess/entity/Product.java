package com.cqrs.productcommandservice.dataaccess.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document( collection = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  @Transient
  public static final String SEQUENCE_NAME = "product_sequence";

  @Id
  private long  prodNum;
  private String name;
  private double price;
}
