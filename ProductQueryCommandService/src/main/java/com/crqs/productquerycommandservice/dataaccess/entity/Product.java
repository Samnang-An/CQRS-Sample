package com.crqs.productquerycommandservice.dataaccess.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productQuery")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  @Id
  private long prodNum;
  private String name;
  private double price;
  private int quantity;
}
