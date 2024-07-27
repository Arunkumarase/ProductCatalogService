package com.example.productcatalogservice.controllers;


import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductControllerFlowTest {
    @Autowired
    private ProductController productController;

    @Autowired
    private IProductService productService;

    @Test
    public void Test_Create_Replace_WithStub_Successfully(){
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("pencil");
        productDto.setPrice(5.00);
        productDto.setDescription("Stationary Item");

        Product product = productController.createProduct(productDto);

        ResponseEntity<Product> responseEntity = productController.getProduct(product.getId());
        productDto.setName("pen");
        Product replacedProduct = productController.replaceProduct(productDto.getId(),productDto).getBody();
        ResponseEntity<Product> responseEntity2 = productController.getProduct(replacedProduct.getId());

        assertEquals("pen", responseEntity2.getBody().getName());
        assertEquals("pencil", responseEntity.getBody().getName());

    }


}
