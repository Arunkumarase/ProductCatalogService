package com.example.productcatalogservice.repositories;

import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.DoubleToLongFunction;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductById(Long id);
    Product save(Product product);
    List<Product> findProductByPriceBetween(Double low, Double High);
    List<Product> findAllByOrderByNameDesc();
}
