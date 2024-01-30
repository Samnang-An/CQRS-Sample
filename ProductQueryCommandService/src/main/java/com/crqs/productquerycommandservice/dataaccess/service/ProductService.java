package com.crqs.productquerycommandservice.dataaccess.service;

import com.crqs.productquerycommandservice.controller.dto.ProductDto;
import java.util.List;
import java.util.Optional;

public interface ProductService {

  ProductDto addProduct(long id, String name, double price);

  void deleteProduct(long id);

  ProductDto updateProduct(long id, String name, double price);

  ProductDto updateProductStock(long id, int q);

  Optional<ProductDto> fetchProduct(long id);

  ProductDto fetchProductByName(String name);

  List<ProductDto> getAllProducts();

  void deleteStock(Long id);
}
