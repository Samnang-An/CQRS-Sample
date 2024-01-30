package com.cqrs.stockcommandservice.dataaccess.repository;

import com.cqrs.stockcommandservice.dataaccess.entity.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StockRepository extends MongoRepository<Stock, Long> {

}
