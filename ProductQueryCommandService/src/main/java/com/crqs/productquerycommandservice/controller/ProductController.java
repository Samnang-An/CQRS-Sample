package com.crqs.productquerycommandservice.controller;

import com.crqs.productquerycommandservice.controller.dto.ProductDto;
import com.crqs.productquerycommandservice.dataaccess.service.ProductService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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

  @GetMapping("/get")
  public String getString() {
    return "product Query command:";
  }

  @GetMapping
  public List<ProductDto> getAllProducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
    Optional<ProductDto> product = productService.fetchProduct(id);
    return product.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("/addProduct")
  public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto product) {
    System.out.println("Product Query Command");
    ProductDto addedProduct = productService.addProduct(product.getId(), product.getName(),
        product.getPrice());
    return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDto> updateProduct(@PathVariable long id,
      @RequestBody ProductDto updatedProduct) {
    ProductDto product = productService.updateProduct(id, updatedProduct.getName(),
        updatedProduct.getPrice());
    return product != null ?
        ResponseEntity.ok(product) :
        ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/stock/{id}/{quantity}")
  public ResponseEntity<Void> updateStock(@PathVariable long id, @PathVariable int quantity) {
    productService.updateProductStock(id, quantity);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/stock/{id}")
  public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
    productService.deleteStock(id);
    return ResponseEntity.noContent().build();
  }
}
