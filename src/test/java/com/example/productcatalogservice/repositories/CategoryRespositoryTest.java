package com.example.productcatalogservice.repositories;

import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRespositoryTest {

    @Autowired
    CategoryRespository categoryRespository;

    @Test
    @Transactional
    void testLoading() {
        Category category = categoryRespository.findById(1L).get();
        System.out.println(category.getName());
        System.out.println("debug");
        List<Product> productList = category.getProducts();
        for (Product product: productList) {
            System.out.println(product.getName());
        }
    }
    @Test
    @Transactional
    void testFetchAllCategoryAndProducts() {
        List<Category> categories = categoryRespository.findAll();
        if (!categories.isEmpty()){
            for (Category category: categories) {
                System.out.println(category.getName());
                List<Product> products = category.getProducts();
                if (!products.isEmpty()){
                    System.out.println(products.get(0).getName());
                }
            }
        }

    }


}