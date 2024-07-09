package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.models.Product;

public class ProductVoAdapterUtil {
    public static Product bindProductFromVo(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());
        return product;
    }
}
