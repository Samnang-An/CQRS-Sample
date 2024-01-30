package com.cqrs.stockcommandservice.dataaccess.service;


import com.cqrs.stockcommandservice.controller.dto.StockDto;

public interface StockService {

  StockDto addStock(long prodNum, int q);

  void deleteStock(long id);

}