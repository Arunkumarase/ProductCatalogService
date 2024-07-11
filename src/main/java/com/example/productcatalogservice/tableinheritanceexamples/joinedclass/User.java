package com.example.productcatalogservice.tableinheritanceexamples.joinedclass;

import jakarta.persistence.*;

@Entity(name = "jc_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
}
