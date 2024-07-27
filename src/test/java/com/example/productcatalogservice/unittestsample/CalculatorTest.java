package com.example.productcatalogservice.unittestsample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void Testadd_RunSuccessfully() {
        //Arrange
        Calculator calculator = new Calculator();

        //Act
        int result = calculator.add(1,2);

        //Assert
        assertEquals(3,result);
    }

    @Test
    void Testdivide_ThrowsArithmeticException() {
        //Arrange
        Calculator calculator = new Calculator();

        //Act and Assert
        assertThrows(ArithmeticException.class,() -> calculator.divide(1,0));
    }
}