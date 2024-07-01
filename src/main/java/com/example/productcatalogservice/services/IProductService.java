package com.example.productcatalogservice.services;

import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    Flux<Product> getAllProducts1();

    Product getProduct(Long productId);

    Product createProduct(Product product);

    Product replaceProduct(Long id, Product product);
    String deleteProduct(Long productId);
}
