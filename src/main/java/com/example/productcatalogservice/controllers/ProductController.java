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
@RequestMapping("/product-service/api/v1/products")
public class ProductController {

    private IProductService productService;

    public ProductController(IProductService iProductService){
        this.productService = iProductService;
    }
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
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
            Product product =  productService.getProduct(productId);
            return new ResponseEntity<>(product,headers,HttpStatus.OK);
        } catch (Exception ex) {
            //return new ResponseEntity<>(ex.getMessage(),headers,HttpStatus.BAD_REQUEST);
            throw ex;
        }

    }

    @PostMapping("/save")
    public Product createProduct(@RequestBody ProductDto productDto){
        return productService.createProduct(ProductVoAdapterUtil.bindProductFromVo(productDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(
            @PathVariable("id") Long productId,
            @RequestBody ProductDto productDto
    ){
        return new ResponseEntity<>(
                productService.replaceProduct(
                        productId,ProductVoAdapterUtil.bindProductFromVo(productDto)
                ), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long productId) {
        return productService.deleteProduct(productId);
    }

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    public ResponseEntity<String> exceptionHandle(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
