package com.example.productcatalogservice.repositories;

import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.function.DoubleToLongFunction;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductById(Long id);
    Product save(Product product);
    List<Product> findProductByPriceBetween(Double low, Double High);
    List<Product> findAllByOrderByNameDesc();

    /*@Query(value = "select p.name from Product p where p.id=:number")
    String getProductNameById(@Param(value = "number") Long id);

    @Query(value = "select p.name from Product p where p.id=?1")
    String getProductNameById(Long id);*/

    @Query(value = "select c.name from Category c join Product p on p.category.id = c.id where p.id=?1")
    String getCategoryNameFromProductId(Long id);
}
