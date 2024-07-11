package com.example.productcatalogservice.tableinheritanceexamples.tableperclass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_instructor")
public class Instructor extends User {
    private int noOfHours;
}
