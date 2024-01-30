package com.crqs.productquerycommandservice.dataaccess.service.impl;

import com.crqs.productquerycommandservice.controller.dto.ProductDto;
import com.crqs.productquerycommandservice.dataaccess.entity.Product;
import com.crqs.productquerycommandservice.dataaccess.repository.ProductRepository;
import com.crqs.productquerycommandservice.dataaccess.service.ProductService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Override
  public ProductDto addProduct(long id, String name, double price) {
    Product product = Product.builder()
        .prodNum(id)
        .name(name)
        .price(price)
        .build();
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

  @Override
  public ProductDto updateProductStock(long id, int q) {
    Product product = productRepository.findById(id).get();
    product.setQuantity(q);
    return ProductDto.from(productRepository.save(product));
  }

  @Override
  public Optional<ProductDto> fetchProduct(long id) {
    return Optional.ofNullable(ProductDto.from(productRepository.findById(id).get()));
  }

  @Override
  public ProductDto fetchProductByName(String name) {
    return ProductDto.from(productRepository.findByName(name));
  }

  @Override
  public List<ProductDto> getAllProducts() {
    return productRepository.findAll().stream().map(ProductDto::from).collect(Collectors.toList());
  }

  @Override
  public void deleteStock(Long id) {
    Product product = productRepository.findById(id).get();
    product.setQuantity(0);
    productRepository.save(product);
  }
}
