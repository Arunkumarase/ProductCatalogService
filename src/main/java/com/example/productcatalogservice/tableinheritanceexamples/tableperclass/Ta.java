package com.example.productcatalogservice.tableinheritanceexamples.tableperclass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_ta")
public class Ta extends User {
    private Integer numOfRatings;
}
