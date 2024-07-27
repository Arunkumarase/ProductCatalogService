package com.example.productcatalogservice.unittestsample;

public class Calculator {
    int add(int num1, int num2) {
        return num1+num2;
    }
    int divide(int num1, int num2) {
        try {
            return num1/num2;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }

    }
}
