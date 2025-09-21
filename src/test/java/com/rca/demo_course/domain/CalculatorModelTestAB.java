package com.rca.demo_course.domain;

import com.rca.demo_course.service.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for CalculatorModel using Mockito @InjectMocks
 * This demonstrates unit testing with dependency injection mocking
 */
public class CalculatorModelTestAB {

    @InjectMocks
    private CalculatorModel calculatorModel;

    @Mock
    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test addition operation")
    void testAdd() {
        // Given
        double a = 5.0;
        double b = 3.0;
        double expected = 8.0;

        // When
        when(calculatorService.add(a, b)).thenReturn(expected);
        double result = calculatorModel.add(a, b);

        // Then
        assertEquals(expected, result);
        verify(calculatorService, times(1)).add(a, b);
    }

    @Test
    @DisplayName("Test addition with negative numbers")
    void testAddWithNegativeNumbers() {
        // Given
        double a = -5.0;
        double b = 3.0;
        double expected = -2.0;

        // When
        when(calculatorService.add(a, b)).thenReturn(expected);
        double result = calculatorModel.add(a, b);

        // Then
        assertEquals(expected, result);
        verify(calculatorService).add(a, b);
    }

    @Test
    @DisplayName("Test addition with zero")
    void testAddWithZero() {
        // Given
        double a = 10.0;
        double b = 0.0;
        double expected = 10.0;

        // When
        when(calculatorService.add(a, b)).thenReturn(expected);
        double result = calculatorModel.add(a, b);

        // Then
        assertEquals(expected, result);
        verify(calculatorService).add(a, b);
    }

    @Test
    @DisplayName("Test addition with decimal numbers")
    void testAddWithDecimals() {
        // Given
        double a = 2.5;
        double b = 3.7;
        double expected = 6.2;

        // When
        when(calculatorService.add(a, b)).thenReturn(expected);
        double result = calculatorModel.add(a, b);

        // Then
        assertEquals(expected, result, 0.001); // Using delta for decimal comparison
        verify(calculatorService).add(a, b);
    }

    @Test
    @DisplayName("Test service interaction verification")
    void testServiceInteractionVerification() {
        // Given
        double a = 100.0;
        double b = 50.0;
        double expected = 150.0;

        // When
        when(calculatorService.add(a, b)).thenReturn(expected);
        calculatorModel.add(a, b);
        calculatorModel.add(a, b);

        // Then
        verify(calculatorService, times(2)).add(a, b);
    }

    @Test
    @DisplayName("Test that service is never called with wrong parameters")
    void testServiceNeverCalledWithWrongParams() {
        // Given
        double a = 5.0;
        double b = 3.0;
        double wrongA = 10.0;
        double wrongB = 20.0;

        // When
        when(calculatorService.add(a, b)).thenReturn(8.0);
        calculatorModel.add(a, b);

        // Then
        verify(calculatorService, never()).add(wrongA, wrongB);
        verify(calculatorService, only()).add(a, b);
    }

    @Test
    @DisplayName("Test exception handling in service")
    void testExceptionHandling() {
        // Given
        double a = 5.0;
        double b = 3.0;

        // When
        when(calculatorService.add(a, b)).thenThrow(new RuntimeException("Service error"));

        // Then
        assertThrows(RuntimeException.class, () -> calculatorModel.add(a, b));
        verify(calculatorService).add(a, b);
    }

    @Test
    @DisplayName("Test multiple operations sequence")
    void testMultipleOperationsSequence() {
        // Given
        double a = 10.0;
        double b = 5.0;
        double c = 2.0;

        // When
        when(calculatorService.add(a, b)).thenReturn(15.0);
        when(calculatorService.add(15.0, c)).thenReturn(17.0);

        double result1 = calculatorModel.add(a, b);
        double result2 = calculatorModel.add(result1, c);

        // Then
        assertEquals(15.0, result1);
        assertEquals(17.0, result2);
        verify(calculatorService).add(a, b);
        verify(calculatorService).add(15.0, c);
    }

    @Test
    @DisplayName("Test calculator model initialization")
    void testCalculatorModelInitialization() {
        // Then
        assertNotNull(calculatorModel);
        assertNotNull(calculatorService);
    }
}
