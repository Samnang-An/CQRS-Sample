package com.cqrs.stockcommandservice.controller.dto;

import com.cqrs.stockcommandservice.dataaccess.entity.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {

  private long prodNum;
  private int quantity;

  public static StockDto from(Stock stock) {
    return new StockDto(stock.getProdNum(), stock.getQuantity());
  }
}
