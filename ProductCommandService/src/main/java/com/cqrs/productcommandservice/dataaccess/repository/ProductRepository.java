package com.cqrs.productcommandservice.dataaccess.repository;


import com.cqrs.productcommandservice.dataaccess.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, Long> {

}
