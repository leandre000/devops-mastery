package com.rca.demo_course.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorModelTest {
    @Test
    @DisplayName("Adding two valid number")
    void testAdd_TwoValidNumbers_returnSum(){
        //Arrange
        CalculatorModel calculatorModel = new CalculatorModel();
        //Act
        double sum = calculatorModel.add(4,5);
        //Assert
        Assertions.assertEquals(9,sum);
    }
    @Test
    @DisplayName("Adding negative numbers")
    void testAdd_TwoNegativeNumbers_returnLessThanZero(){
        //arrange
        CalculatorModel calculatorModel = new CalculatorModel();
        //act
        double sum = calculatorModel.add(-4,-5);
        //assert
        Assertions.assertEquals(-9,sum);
    }
}
