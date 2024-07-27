package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class ProductControllerTest {

    @Autowired
    ProductController productController;

    @MockBean
    IProductService iProductService;

    @Autowired
    private ArgumentCaptor<Long> idCaptor;

    @Test
    @DisplayName(value = "product fetched successfully")
    void Test_GetProduct_WithValidId_GetProductSuccessfully() {

        Product product = new Product();
        product.setName("iphone");
        product.setPrice(50000.00);
        product.setDescription("its a mobile phone from apple");

        //Arrange
        when(iProductService.getProduct(any(Long.class))).thenReturn(product);

        //Act
        ResponseEntity<Product> responseEntity = productController.getProduct(1L);

        //Assert
        //assertNotNull(responseEntity.getBody());
        assertEquals(50000D,responseEntity.getBody().getPrice());
        assertEquals("iphone",responseEntity.getBody().getName());
    }

    @Test
    @DisplayName(value = "Executed Runtime Exception scenario")
    void Test_GetProduct_ExternalDependencyThrowsException() {
        //Arrange
        when(iProductService.getProduct(any(Long.class)))
                .thenThrow(new RuntimeException("something went wrong"));

        //Act and Assert
        assertThrows(RuntimeException.class,() -> productController.getProduct(1L));
    }

    @Test
    @DisplayName(value = "Thrown illegal arugument exception for product id < 1")
    void Test_GetProduct_WithInvalidId_ThrowsIllegalArgumentException() {
        //Act and assert
        assertThrows(IllegalArgumentException.class, () -> productController.getProduct(0L));

    }

    @Test
    public void Test_ProductServiceCalledWithExpectedArguments_Successfully(){
        //Arrange
        Long id = 2L;

        //Act
        productController.getProduct(id);

        //Assert
        verify(iProductService).getProduct(idCaptor.capture());
        assertEquals(id,idCaptor.getValue());
    }
}