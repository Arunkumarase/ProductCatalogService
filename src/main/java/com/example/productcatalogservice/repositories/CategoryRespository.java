package com.example.productcatalogservice.repositories;

import com.example.productcatalogservice.models.Category;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRespository extends JpaRepository<Category,Long> {
}
