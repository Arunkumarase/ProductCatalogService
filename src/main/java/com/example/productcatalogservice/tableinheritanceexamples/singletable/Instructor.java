package com.example.productcatalogservice.tableinheritanceexamples.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "st_instructor")
@DiscriminatorValue(value = "2")
public class Instructor extends User{
    private int noOfHours;
}
