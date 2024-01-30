package com.cqrs.productcommandservice.dataaccess.service.impl;

import com.cqrs.productcommandservice.controller.dto.ProductDto;
import com.cqrs.productcommandservice.dataaccess.entity.Product;
import com.cqrs.productcommandservice.dataaccess.repository.ProductRepository;
import com.cqrs.productcommandservice.dataaccess.service.ProductService;
import com.cqrs.productcommandservice.dataaccess.service.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private SequenceGenerator sequenceGenerator;

  @Override
  public ProductDto addProduct(String name, double price) {
    Product product = new Product(sequenceGenerator.generate(Product.SEQUENCE_NAME), name, price);
    return ProductDto.from(productRepository.save(product));
  }

  @Override
  public void deleteProduct(long id) {
    productRepository.deleteById(id);
  }

  @Override
  public ProductDto updateProduct(long id, String name, double price) {
    Product product = productRepository.findById(id).get();
    product.setName(name);
    product.setPrice(price);
    return ProductDto.from(productRepository.save(product));
  }
}
