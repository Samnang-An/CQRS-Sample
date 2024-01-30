package com.cqrs.stockcommandservice.controller;

import com.cqrs.stockcommandservice.controller.dto.StockDto;
import com.cqrs.stockcommandservice.dataaccess.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

  @Autowired
  private ProductQueryFeignClient productQueryFeignClient;

  @Autowired
  private StockService stockService;

  @PostMapping("/updateStock")
  public ResponseEntity<StockDto> addStock(@RequestBody StockDto stockDto) {
    StockDto addedProduct = stockService.addStock(stockDto.getProdNum(), stockDto.getQuantity());
    productQueryFeignClient.updateStock(stockDto.getProdNum(),stockDto.getQuantity());
    return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
    stockService.deleteStock(id);
    productQueryFeignClient.deleteStock(id);
    return ResponseEntity.noContent().build();
  }

  @FeignClient("product-query-command")
  interface ProductQueryFeignClient {
    @PutMapping("/stock/{id}/{quantity}")
    public ResponseEntity<Void> updateStock(@PathVariable long id, @PathVariable int quantity);

    @DeleteMapping("/stock/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id);

  }
}
