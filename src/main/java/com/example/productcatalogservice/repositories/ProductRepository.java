package com.example.productcatalogservice.repositories;

import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductById(Long id);
    Product save(Product product);
}
