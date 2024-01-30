package com.cqrs.productcommandservice.controller;

import com.cqrs.productcommandservice.controller.dto.ProductDto;
import com.cqrs.productcommandservice.dataaccess.service.ProductService;
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
public class ProductController {

  @Autowired
  private ProductService productService;
  @Autowired
  ProductQueryFeignClient productQueryFeignClient;

  @GetMapping("/get")
  public String getString(){
    String string = productQueryFeignClient.getString();
    return "product command:" + string;
  }

  @PostMapping("/addProduct")
  public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto product) {
    ProductDto addedProduct = productService.addProduct(product.getName(), product.getPrice());
    productQueryFeignClient.addProduct(addedProduct);
    return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDto> updateProduct(@PathVariable long id,
      @RequestBody ProductDto updatedProduct) {
    ProductDto product = productService.updateProduct(id, updatedProduct.getName(),updatedProduct.getPrice());
    productQueryFeignClient.updateProduct(id, product);
    return product != null ?
        ResponseEntity.ok(product) :
        ResponseEntity.notFound().build();
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    productQueryFeignClient.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }

  @FeignClient("product-query-command")
  interface ProductQueryFeignClient {
    @PostMapping("/addProduct")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto product);

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable long id,
        @RequestBody ProductDto updatedProduct);

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id);

    @GetMapping("/get")
    String getString();
  }
}
