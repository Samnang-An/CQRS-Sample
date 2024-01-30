package com.crqs.productquerycommandservice.dataaccess.repository;

import com.crqs.productquerycommandservice.dataaccess.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, Long> {

  Product findByName(String name);

}
