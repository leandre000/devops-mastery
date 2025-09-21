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
 * Unit tests for CalculatorService interface implementation.
 * Tests the service layer in isolation.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Calculator Service Tests")
public class CalculatorServiceTest {

    @InjectMocks
    private CalculatorServiceImpl calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorServiceImpl();
    }

    @Test
    @DisplayName("Service should be properly initialized")
    void testServiceInitialization() {
        assertNotNull(calculatorService, "Calculator service should not be null");
    }

    @Test
    @DisplayName("Addition should handle edge cases")
    void testAddEdgeCases() {
        // Test with Double.MAX_VALUE
        assertEquals(Double.MAX_VALUE, calculatorService.add(Double.MAX_VALUE, 0.0), 0.001);

        // Test with Double.MIN_VALUE
        assertEquals(Double.MIN_VALUE, calculatorService.add(Double.MIN_VALUE, 0.0), 0.001);

        // Test with negative infinity
        assertTrue(Double.isInfinite(calculatorService.add(Double.NEGATIVE_INFINITY, 1.0)));

        // Test with positive infinity
        assertTrue(Double.isInfinite(calculatorService.add(Double.POSITIVE_INFINITY, 1.0)));
    }

    @Test
    @DisplayName("Division should handle special cases")
    void testDivideSpecialCases() {
        // Test division by very small numbers
        assertTrue(Double.isInfinite(calculatorService.divide(1.0, Double.MIN_VALUE)));

        // Test division of very small numbers
        assertEquals(0.0, calculatorService.divide(Double.MIN_VALUE, 1.0), 0.001);

        // Test division resulting in infinity
        assertTrue(Double.isInfinite(calculatorService.divide(Double.MAX_VALUE, Double.MIN_VALUE)));
    }

    @Test
    @DisplayName("Power should handle special mathematical cases")
    void testPowerSpecialCases() {
        // Test power of 0
        assertEquals(1.0, calculatorService.power(0.0, 0.0), 0.001);

        // Test power with NaN
        assertTrue(Double.isNaN(calculatorService.power(0.0, -1.0)));

        // Test power with infinity
        assertTrue(Double.isInfinite(calculatorService.power(2.0, Double.MAX_VALUE)));
    }

    @Test
    @DisplayName("Square root should handle boundary conditions")
    void testSquareRootBoundaryConditions() {
        // Test square root of very small positive numbers
        assertTrue(calculatorService.squareRoot(Double.MIN_VALUE) >= 0);

        // Test square root of very large numbers
        assertTrue(calculatorService.squareRoot(Double.MAX_VALUE) > 0);

        // Test square root of infinity
        assertTrue(Double.isInfinite(calculatorService.squareRoot(Double.POSITIVE_INFINITY)));
    }

    @Test
    @DisplayName("Absolute value should handle special values")
    void testAbsoluteSpecialValues() {
        // Test absolute of infinity
        assertTrue(Double.isInfinite(calculatorService.absolute(Double.POSITIVE_INFINITY)));
        assertTrue(Double.isInfinite(calculatorService.absolute(Double.NEGATIVE_INFINITY)));

        // Test absolute of NaN
        assertTrue(Double.isNaN(calculatorService.absolute(Double.NaN)));
    }

    @Test
    @DisplayName("Percentage should handle edge cases")
    void testPercentageEdgeCases() {
        // Test percentage with very large numbers
        assertTrue(calculatorService.percentage(Double.MAX_VALUE, 1.0) > 0);

        // Test percentage with very small numbers
        assertEquals(0.0, calculatorService.percentage(Double.MIN_VALUE, 1.0), 0.001);

        // Test percentage over 100%
        assertEquals(200.0, calculatorService.percentage(100.0, 200.0), 0.001);

        // Test negative percentage
        assertEquals(-20.0, calculatorService.percentage(100.0, -20.0), 0.001);
    }

    @Test
    @DisplayName("All operations should maintain precision")
    void testPrecisionMaintenance() {
        // Test that operations maintain reasonable precision
        double result = calculatorService.add(0.1, 0.2);
        assertEquals(0.3, result, 1e-15); // Very high precision test

        result = calculatorService.multiply(3.0, 0.1);
        assertEquals(0.3, result, 1e-15);

        result = calculatorService.divide(0.3, 3.0);
        assertEquals(0.1, result, 1e-15);
    }

    @Test
    @DisplayName("Exception messages should be descriptive")
    void testExceptionMessages() {
        // Test division by zero exception message
        IllegalArgumentException divideException = assertThrows(IllegalArgumentException.class,
                () -> calculatorService.divide(5.0, 0.0));
        assertEquals("Division by zero is not allowed", divideException.getMessage());

        // Test negative square root exception message
        IllegalArgumentException sqrtException = assertThrows(IllegalArgumentException.class,
                () -> calculatorService.squareRoot(-1.0));
        assertEquals("Cannot calculate square root of negative number", sqrtException.getMessage());
    }

    @Test
    @DisplayName("Service should handle NaN inputs gracefully")
    void testNaNInputHandling() {
        // Test that NaN inputs propagate correctly
        assertTrue(Double.isNaN(calculatorService.add(Double.NaN, 5.0)));
        assertTrue(Double.isNaN(calculatorService.multiply(Double.NaN, 5.0)));
        assertTrue(Double.isNaN(calculatorService.divide(Double.NaN, 5.0)));
        assertTrue(Double.isNaN(calculatorService.absolute(Double.NaN)));
    }
}
