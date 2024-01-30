package com.cqrs.productcommandservice.dataaccess.service;


import com.cqrs.productcommandservice.controller.dto.ProductDto;

public interface ProductService {

  ProductDto addProduct(String name, double price);

  void deleteProduct(long id);

  ProductDto updateProduct(long id, String name, double price);

}
