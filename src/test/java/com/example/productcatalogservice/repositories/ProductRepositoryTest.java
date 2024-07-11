package com.example.productcatalogservice.repositories;

import com.example.productcatalogservice.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @Transactional
    void testQueries() {
        //List<Product> productList = productRepository.findProductByPriceBetween(0.00,1000.00);
        List<Product> productList = productRepository.findAllByOrderByNameDesc();
        productList.forEach(product -> System.out.println(product.getName().toString()));

    }

}