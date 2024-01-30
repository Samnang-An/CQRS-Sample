package com.crqs.productquerycommandservice.controller.dto;

import com.crqs.productquerycommandservice.dataaccess.entity.Product;
import java.util.Objects;
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
  private int quantity;

  public static ProductDto from(Product product) {
    if(Objects.isNull(product)) return null;
    return new ProductDto(product.getProdNum(), product.getName(), product.getPrice(),
        product.getQuantity());
  }
}
