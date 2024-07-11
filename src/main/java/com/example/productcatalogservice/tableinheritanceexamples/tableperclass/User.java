package com.example.productcatalogservice.tableinheritanceexamples.tableperclass;

import jakarta.persistence.*;

@Entity(name = "tpc_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
}
