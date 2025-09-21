package com.rca.demo_course.service;

import com.rca.demo_course.service.impl.CalculatorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive unit tests for CalculatorServiceImpl.
 * Tests all calculator operations including edge cases and error conditions.
 */
@ExtendWith(MockitoExtension.class)
public class CalculatorServiceImplTest {

    @InjectMocks
    private CalculatorServiceImpl calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorServiceImpl();
    }

    // Addition Tests
    @Test
    @DisplayName("Should add two positive numbers correctly")
    void testAddPositiveNumbers() {
        // Given
        double a = 5.0;
        double b = 3.0;
        double expected = 8.0;

        // When
        double result = calculatorService.add(a, b);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should add two negative numbers correctly")
    void testAddNegativeNumbers() {
        // Given
        double a = -5.0;
        double b = -3.0;
        double expected = -8.0;

        // When
        double result = calculatorService.add(a, b);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should add positive and negative numbers correctly")
    void testAddMixedNumbers() {
        // Given
        double a = 5.0;
        double b = -3.0;
        double expected = 2.0;

        // When
        double result = calculatorService.add(a, b);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should add zero correctly")
    void testAddWithZero() {
        // Given
        double a = 5.0;
        double b = 0.0;
        double expected = 5.0;

        // When
        double result = calculatorService.add(a, b);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should add decimal numbers correctly")
    void testAddDecimalNumbers() {
        // Given
        double a = 2.5;
        double b = 3.7;
        double expected = 6.2;

        // When
        double result = calculatorService.add(a, b);

        // Then
        assertEquals(expected, result, 0.001);
    }

    // Subtraction Tests
    @Test
    @DisplayName("Should subtract two positive numbers correctly")
    void testSubtractPositiveNumbers() {
        // Given
        double a = 5.0;
        double b = 3.0;
        double expected = 2.0;

        // When
        double result = calculatorService.subtract(a, b);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should subtract negative numbers correctly")
    void testSubtractNegativeNumbers() {
        // Given
        double a = -5.0;
        double b = -3.0;
        double expected = -2.0;

        // When
        double result = calculatorService.subtract(a, b);

        // Then
        assertEquals(expected, result);
    }

    // Multiplication Tests
    @Test
    @DisplayName("Should multiply two positive numbers correctly")
    void testMultiplyPositiveNumbers() {
        // Given
        double a = 4.0;
        double b = 3.0;
        double expected = 12.0;

        // When
        double result = calculatorService.multiply(a, b);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should multiply by zero correctly")
    void testMultiplyByZero() {
        // Given
        double a = 5.0;
        double b = 0.0;
        double expected = 0.0;

        // When
        double result = calculatorService.multiply(a, b);

        // Then
        assertEquals(expected, result);
    }

    // Division Tests
    @Test
    @DisplayName("Should divide two positive numbers correctly")
    void testDividePositiveNumbers() {
        // Given
        double a = 10.0;
        double b = 2.0;
        double expected = 5.0;

        // When
        double result = calculatorService.divide(a, b);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should divide by decimal correctly")
    void testDivideByDecimal() {
        // Given
        double a = 7.0;
        double b = 2.0;
        double expected = 3.5;

        // When
        double result = calculatorService.divide(a, b);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should throw exception when dividing by zero")
    void testDivideByZero() {
        // Given
        double a = 5.0;
        double b = 0.0;

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.divide(a, b);
        });

        assertEquals("Division by zero is not allowed", exception.getMessage());
    }

    @Test
    @DisplayName("Should handle division with negative numbers")
    void testDivideNegativeNumbers() {
        // Given
        double a = -10.0;
        double b = 2.0;
        double expected = -5.0;

        // When
        double result = calculatorService.divide(a, b);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should handle division resulting in decimal")
    void testDivideResultingInDecimal() {
        // Given
        double a = 1.0;
        double b = 3.0;
        double expected = 0.3333333333333333;

        // When
        double result = calculatorService.divide(a, b);

        // Then
        assertEquals(expected, result, 0.000000000000001);
    }

    // Power Tests
    @Test
    @DisplayName("Should calculate power correctly")
    void testPower() {
        // Given
        double base = 2.0;
        double exponent = 3.0;
        double expected = 8.0;

        // When
        double result = calculatorService.power(base, exponent);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should handle power with decimal base and exponent")
    void testPowerWithDecimals() {
        // Given
        double base = 2.5;
        double exponent = 2.0;
        double expected = 6.25;

        // When
        double result = calculatorService.power(base, exponent);

        // Then
        assertEquals(expected, result);
    }

    // Square Root Tests
    @Test
    @DisplayName("Should calculate square root of positive number")
    void testSquareRootPositive() {
        // Given
        double number = 9.0;
        double expected = 3.0;

        // When
        double result = calculatorService.squareRoot(number);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should throw exception for square root of negative number")
    void testSquareRootNegative() {
        // Given
        double number = -4.0;

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.squareRoot(number);
        });

        assertEquals("Cannot calculate square root of negative number", exception.getMessage());
    }

    @Test
    @DisplayName("Should calculate square root of zero")
    void testSquareRootZero() {
        // Given
        double number = 0.0;
        double expected = 0.0;

        // When
        double result = calculatorService.squareRoot(number);

        // Then
        assertEquals(expected, result);
    }

    // Absolute Value Tests
    @Test
    @DisplayName("Should return absolute value of positive number")
    void testAbsolutePositive() {
        // Given
        double number = 5.0;
        double expected = 5.0;

        // When
        double result = calculatorService.absolute(number);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should return absolute value of negative number")
    void testAbsoluteNegative() {
        // Given
        double number = -5.0;
        double expected = 5.0;

        // When
        double result = calculatorService.absolute(number);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should return absolute value of zero")
    void testAbsoluteZero() {
        // Given
        double number = 0.0;
        double expected = 0.0;

        // When
        double result = calculatorService.absolute(number);

        // Then
        assertEquals(expected, result);
    }

    // Percentage Tests
    @Test
    @DisplayName("Should calculate percentage correctly")
    void testPercentage() {
        // Given
        double number = 100.0;
        double percentage = 20.0;
        double expected = 20.0;

        // When
        double result = calculatorService.percentage(number, percentage);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should calculate percentage with decimal values")
    void testPercentageWithDecimals() {
        // Given
        double number = 250.5;
        double percentage = 15.5;
        double expected = 38.8275;

        // When
        double result = calculatorService.percentage(number, percentage);

        // Then
        assertEquals(expected, result, 0.0001);
    }

    @Test
    @DisplayName("Should handle zero percentage")
    void testPercentageZero() {
        // Given
        double number = 100.0;
        double percentage = 0.0;
        double expected = 0.0;

        // When
        double result = calculatorService.percentage(number, percentage);

        // Then
        assertEquals(expected, result);
    }
}
