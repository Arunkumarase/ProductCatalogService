package com.example.productcatalogservice.tableinheritanceexamples.joinedclass;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.PrimaryKeyJoinColumn;
import org.springframework.context.annotation.Primary;

@Entity(name = "jc_ta")
@PrimaryKeyJoinColumn(name = "user_id")
public class Ta extends User {
    private Integer numOfRatings;
}
