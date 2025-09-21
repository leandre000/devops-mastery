package com.rca.demo_course;

import com.rca.demo_course.service.impl.CalculatorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive unit tests for the CalculatorServiceImpl class.
 */
@DisplayName("Calculator Service Implementation Tests")
public class CalculatorTest {

    private CalculatorServiceImpl calculator;

    @BeforeEach
    void setUp() {
        calculator = new CalculatorServiceImpl();
    }

    @DisplayName("Addition Tests")
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "2.0, 3.0, 5.0",
            "-2.0, 2.0, 0.0",
            "-2.0, -3.0, -5.0",
            "0.0, 5.0, 5.0",
            "5.0, 0.0, 5.0",
            "0.0, 0.0, 0.0",
            "1.5, 2.5, 4.0",
            "-1.5, -2.5, -4.0"
    })
    void testAdd(double a, double b, double expected) {
        assertEquals(expected, calculator.add(a, b), 0.001,
                () -> String.format("%f + %f should equal %f", a, b, expected));
    }

    @Test
    @DisplayName("Addition with Large Numbers")
    void testAddLargeNumbers() {
        double largeNumber = Double.MAX_VALUE;
        assertEquals(largeNumber, calculator.add(largeNumber, 0.0), 0.001);
    }

    @Test
    @DisplayName("Addition with Small Numbers")
    void testAddSmallNumbers() {
        double smallNumber = Double.MIN_VALUE;
        assertEquals(smallNumber, calculator.add(smallNumber, 0.0), 0.001);
    }

    @DisplayName("Subtraction Tests")
    @ParameterizedTest(name = "{0} - {1} = {2}")
    @CsvSource({
            "3.0, 2.0, 1.0",
            "2.0, 3.0, -1.0",
            "2.0, -3.0, 5.0",
            "5.0, 0.0, 5.0",
            "0.0, 5.0, -5.0",
            "0.0, 0.0, 0.0",
            "1.5, 2.5, -1.0",
            "-1.5, -2.5, 1.0"
    })
    void testSubtract(double a, double b, double expected) {
        assertEquals(expected, calculator.subtract(a, b), 0.001,
                () -> String.format("%f - %f should equal %f", a, b, expected));
    }

    @DisplayName("Multiplication Tests")
    @ParameterizedTest(name = "{0} * {1} = {2}")
    @CsvSource({
            "2.0, 3.0, 6.0",
            "0.0, 5.0, 0.0",
            "-2.0, 3.0, -6.0",
            "5.0, 0.0, 0.0",
            "1.0, 5.0, 5.0",
            "5.0, 1.0, 5.0",
            "1.5, 2.0, 3.0",
            "-1.5, -2.0, 3.0"
    })
    void testMultiply(double a, double b, double expected) {
        assertEquals(expected, calculator.multiply(a, b), 0.001,
                () -> String.format("%f * %f should equal %f", a, b, expected));
    }

    @Test
    @DisplayName("Multiplication with Infinity")
    void testMultiplyInfinity() {
        assertTrue(Double.isInfinite(calculator.multiply(Double.MAX_VALUE, 2.0)));
    }

    @DisplayName("Division Tests")
    @ParameterizedTest(name = "{0} / {1} = {2}")
    @CsvSource({
            "6.0, 3.0, 2.0",
            "-6.0, 3.0, -2.0",
            "1.0, 2.0, 0.5",
            "5.0, 1.0, 5.0",
            "0.0, 5.0, 0.0",
            "1.5, 0.5, 3.0",
            "-1.5, 0.5, -3.0"
    })
    void testDivide(double a, double b, double expected) {
        assertEquals(expected, calculator.divide(a, b), 0.001,
                () -> String.format("%f / %f should equal %f", a, b, expected));
    }

    @DisplayName("Division by Zero Tests")
    @ParameterizedTest(name = "Division by zero should throw exception for dividend {0}")
    @ValueSource(doubles = {5.0, -5.0, 0.0, Double.MAX_VALUE, Double.MIN_VALUE})
    void testDivideByZero(double dividend) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> calculator.divide(dividend, 0.0));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }

    @Test
    @DisplayName("Division with Very Small Numbers")
    void testDivideVerySmallNumbers() {
        double result = calculator.divide(1.0, Double.MAX_VALUE);
        assertEquals(0.0, result, 0.001);
    }

    @DisplayName("Power Tests")
    @ParameterizedTest(name = "{0}^{1} = {2}")
    @CsvSource({
            "2.0, 3.0, 8.0",
            "5.0, 0.0, 1.0",
            "2.0, -2.0, 0.25",
            "4.0, 0.5, 2.0",
            "1.0, 100.0, 1.0",
            "0.0, 5.0, 0.0",
            "10.0, 2.0, 100.0"
    })
    void testPower(double base, double exponent, double expected) {
        assertEquals(expected, calculator.power(base, exponent), 0.001,
                () -> String.format("%f^%f should equal %f", base, exponent, expected));
    }

    @Test
    @DisplayName("Power with Infinity")
    void testPowerInfinity() {
        assertTrue(Double.isInfinite(calculator.power(2.0, Double.MAX_VALUE)));
    }

    @DisplayName("Square Root Tests")
    @ParameterizedTest(name = "sqrt({0}) = {1}")
    @CsvSource({
            "9.0, 3.0",
            "0.0, 0.0",
            "2.0, 1.4142135623730951",
            "4.0, 2.0",
            "16.0, 4.0",
            "1.0, 1.0",
            "25.0, 5.0"
    })
    void testSquareRoot(double number, double expected) {
        assertEquals(expected, calculator.squareRoot(number), 0.001,
                () -> String.format("sqrt(%f) should equal %f", number, expected));
    }

    @DisplayName("Square Root of Negative Numbers")
    @ParameterizedTest(name = "Square root of {0} should throw exception")
    @ValueSource(doubles = {-1.0, -5.0, -100.0, Double.NEGATIVE_INFINITY})
    void testSquareRootNegative(double number) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> calculator.squareRoot(number));
        assertEquals("Cannot calculate square root of negative number", exception.getMessage());
    }

    @DisplayName("Absolute Value Tests")
    @ParameterizedTest(name = "abs({0}) = {1}")
    @CsvSource({
            "5.0, 5.0",
            "-5.0, 5.0",
            "0.0, 0.0",
            "-1.5, 1.5",
            "1.5, 1.5",
            "-100.0, 100.0",
            "100.0, 100.0"
    })
    void testAbsolute(double number, double expected) {
        assertEquals(expected, calculator.absolute(number), 0.001,
                () -> String.format("abs(%f) should equal %f", number, expected));
    }

    @DisplayName("Percentage Tests")
    @ParameterizedTest(name = "{0}% of {1} = {2}")
    @CsvSource({
            "20.0, 100.0, 20.0",
            "25.0, 200.0, 50.0",
            "0.0, 100.0, 0.0",
            "100.0, 100.0, 100.0",
            "50.0, 50.0, 25.0",
            "10.0, 1000.0, 100.0"
    })
    void testPercentage(double percentage, double number, double expected) {
        assertEquals(expected, calculator.percentage(number, percentage), 0.001,
                () -> String.format("%f%% of %f should equal %f", percentage, number, expected));
    }

    @Test
    @DisplayName("Percentage with Zero Base")
    void testPercentageZeroBase() {
        assertEquals(0.0, calculator.percentage(0.0, 50.0), 0.001);
    }
}
