package com.example.productcatalogservice.dtos;

import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.FakeStoreRating;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private String image;
    private Double price;
    private String category;
    private FakeStoreRating fakeStoreRating;

    @Override
    public String toString() {
        return "FakeStoreProductDto{" +
                "id=" + id +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
