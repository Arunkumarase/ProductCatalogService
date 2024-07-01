package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    IProductService iProductService;
    public ProductController(IProductService iProductService){
        this.iProductService = iProductService;
    }
    @GetMapping
    public List<Product> getAllProducts(){
        return iProductService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long productId){
        MultiValueMap headers = new LinkedMultiValueMap();
        try {
            if (productId < 1){
                headers.add("product remarks","invalid productId");
                throw new IllegalArgumentException("Id is invalid");
            }
            headers.add("product remarks","valid productId");
            Product product =  iProductService.getProduct(productId);
            return new ResponseEntity<>(product,headers,HttpStatus.OK);
        } catch (Exception ex) {
            //return new ResponseEntity<>(ex.getMessage(),headers,HttpStatus.BAD_REQUEST);
            throw ex;
        }

    }

    @PostMapping
    public Product createProduct(@RequestBody ProductDto productDto){
        return iProductService.createProduct(getProductFromDto(productDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long productId, @RequestBody ProductDto productDto){
        return new ResponseEntity<>(iProductService.replaceProduct(productId,getProductFromDto(productDto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long productId) {
        return iProductService.deleteProduct(productId);
    }

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    public ResponseEntity<String> ExceptionHandle(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private Product getProductFromDto(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(product.getCategory());
        return product;
    }
}
