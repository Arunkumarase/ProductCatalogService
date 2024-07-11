package com.example.productcatalogservice.tableinheritanceexamples.joinedclass;

import jakarta.persistence.Entity;

@Entity(name = "jc_instructor")
public class Instructor extends User {
    private int noOfHours;
}
