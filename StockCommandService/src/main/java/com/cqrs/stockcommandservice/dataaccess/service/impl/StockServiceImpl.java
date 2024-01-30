package com.cqrs.stockcommandservice.dataaccess.service.impl;

import com.cqrs.stockcommandservice.controller.dto.StockDto;
import com.cqrs.stockcommandservice.dataaccess.entity.Stock;
import com.cqrs.stockcommandservice.dataaccess.repository.StockRepository;
import com.cqrs.stockcommandservice.dataaccess.service.StockService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

  @Autowired
  private StockRepository stockRepository;

  @Override
  public StockDto addStock(long prodNum, int q) {
    Optional<Stock> byId = stockRepository.findById(prodNum);
    Stock stock = byId.orElse(new Stock(prodNum,q));
    stock.setQuantity(q);
    return StockDto.from(stockRepository.save(stock));
  }

  @Override
  public void deleteStock(long id) {
    stockRepository.deleteById(id);
  }
}
