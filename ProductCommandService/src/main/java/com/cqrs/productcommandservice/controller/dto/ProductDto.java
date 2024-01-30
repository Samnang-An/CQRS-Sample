package com.cqrs.productcommandservice.controller.dto;

import com.cqrs.productcommandservice.dataaccess.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

  private long id;
  private String name;
  private double price;

  public static ProductDto from(Product product) {
    return new ProductDto(product.getProdNum(), product.getName(), product.getPrice());
  }
}
