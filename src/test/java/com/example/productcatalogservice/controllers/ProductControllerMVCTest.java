package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMVCTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    IProductService iProductService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName(value = "Get method to fetch all products")
    void Test_GetAllProducts_RunsSuccessfully() throws Exception {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setName("iphone");
        product.setPrice(50000.00);
        product.setDescription("its a mobile phone from apple");
        products.add(product);
        when(iProductService.getAllProducts()).thenReturn(products);
        mockMvc.perform(get("/product-service/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(products)));
    }
    
    @Test
    @DisplayName(value = "New product created successfully")
    void Test_CreateProduct_Successfully() throws Exception {
        Product expectedProduct = new Product();
        expectedProduct.setName("Adidas Striker");
        expectedProduct.setDescription("Mens shoe");
        expectedProduct.setPrice(5000.00);

        ProductDto productDto = new ProductDto();
        productDto.setName("Adidas Striker");
        productDto.setDescription("Mens shoe");
        productDto.setPrice(5000.00);

        when(iProductService.createProduct(any(Product.class))).thenReturn(expectedProduct);

        mockMvc.perform(post("/product-service/api/v1/products/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(expectedProduct)));
    }


}
