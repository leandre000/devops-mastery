package com.rca.demo_course.controller;

import com.rca.demo_course.service.CalculatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for CalculatorController.
 * Tests all REST endpoints with various scenarios including error cases.
 */
@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculatorService calculatorService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // Setup common mock behaviors if needed
    }

    // Addition Tests
    @Test
    @DisplayName("Should perform addition via GET endpoint")
    void testAddEndpoint() throws Exception {
        // Given
        double a = 5.0;
        double b = 3.0;
        double expectedResult = 8.0;

        when(calculatorService.add(a, b)).thenReturn(expectedResult);

        // When & Then
        mockMvc.perform(get("/api/calculator/add")
                .param("a", String.valueOf(a))
                .param("b", String.valueOf(b)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.a").value(a))
                .andExpect(jsonPath("$.b").value(b))
                .andExpect(jsonPath("$.result").value(expectedResult))
                .andExpect(jsonPath("$.operation").value("addition"));
    }

    @Test
    @DisplayName("Should perform addition with negative numbers")
    void testAddEndpointWithNegativeNumbers() throws Exception {
        // Given
        double a = -5.0;
        double b = 3.0;
        double expectedResult = -2.0;

        when(calculatorService.add(a, b)).thenReturn(expectedResult);

        // When & Then
        mockMvc.perform(get("/api/calculator/add")
                .param("a", String.valueOf(a))
                .param("b", String.valueOf(b)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(expectedResult));
    }

    // Subtraction Tests
    @Test
    @DisplayName("Should perform subtraction via GET endpoint")
    void testSubtractEndpoint() throws Exception {
        // Given
        double a = 10.0;
        double b = 3.0;
        double expectedResult = 7.0;

        when(calculatorService.subtract(a, b)).thenReturn(expectedResult);

        // When & Then
        mockMvc.perform(get("/api/calculator/subtract")
                .param("a", String.valueOf(a))
                .param("b", String.valueOf(b)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.a").value(a))
                .andExpect(jsonPath("$.b").value(b))
                .andExpect(jsonPath("$.result").value(expectedResult))
                .andExpect(jsonPath("$.operation").value("subtraction"));
    }

    // Multiplication Tests
    @Test
    @DisplayName("Should perform multiplication via GET endpoint")
    void testMultiplyEndpoint() throws Exception {
        // Given
        double a = 4.0;
        double b = 5.0;
        double expectedResult = 20.0;

        when(calculatorService.multiply(a, b)).thenReturn(expectedResult);

        // When & Then
        mockMvc.perform(get("/api/calculator/multiply")
                .param("a", String.valueOf(a))
                .param("b", String.valueOf(b)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.a").value(a))
                .andExpect(jsonPath("$.b").value(b))
                .andExpect(jsonPath("$.result").value(expectedResult))
                .andExpect(jsonPath("$.operation").value("multiplication"));
    }

    // Division Tests
    @Test
    @DisplayName("Should perform division via GET endpoint")
    void testDivideEndpoint() throws Exception {
        // Given
        double a = 15.0;
        double b = 3.0;
        double expectedResult = 5.0;

        when(calculatorService.divide(a, b)).thenReturn(expectedResult);

        // When & Then
        mockMvc.perform(get("/api/calculator/divide")
                .param("a", String.valueOf(a))
                .param("b", String.valueOf(b)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.a").value(a))
                .andExpect(jsonPath("$.b").value(b))
                .andExpect(jsonPath("$.result").value(expectedResult))
                .andExpect(jsonPath("$.operation").value("division"));
    }

    @Test
    @DisplayName("Should handle division by zero error")
    void testDivideByZeroEndpoint() throws Exception {
        // Given
        double a = 10.0;
        double b = 0.0;

        when(calculatorService.divide(a, b))
                .thenThrow(new IllegalArgumentException("Division by zero is not allowed"));

        // When & Then
        mockMvc.perform(get("/api/calculator/divide")
                .param("a", String.valueOf(a))
                .param("b", String.valueOf(b)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Should perform division with decimal result")
    void testDivideEndpointWithDecimalResult() throws Exception {
        // Given
        double a = 7.0;
        double b = 2.0;
        double expectedResult = 3.5;

        when(calculatorService.divide(a, b)).thenReturn(expectedResult);

        // When & Then
        mockMvc.perform(get("/api/calculator/divide")
                .param("a", String.valueOf(a))
                .param("b", String.valueOf(b)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(expectedResult));
    }

    // Power Tests
    @Test
    @DisplayName("Should perform power calculation via GET endpoint")
    void testPowerEndpoint() throws Exception {
        // Given
        double base = 2.0;
        double exponent = 3.0;
        double expectedResult = 8.0;

        when(calculatorService.power(base, exponent)).thenReturn(expectedResult);

        // When & Then
        mockMvc.perform(get("/api/calculator/power")
                .param("base", String.valueOf(base))
                .param("exponent", String.valueOf(exponent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.base").value(base))
                .andExpect(jsonPath("$.exponent").value(exponent))
                .andExpect(jsonPath("$.result").value(expectedResult))
                .andExpect(jsonPath("$.operation").value("power"));
    }

    // Square Root Tests
    @Test
    @DisplayName("Should perform square root calculation via GET endpoint")
    void testSquareRootEndpoint() throws Exception {
        // Given
        double number = 9.0;
        double expectedResult = 3.0;

        when(calculatorService.squareRoot(number)).thenReturn(expectedResult);

        // When & Then
        mockMvc.perform(get("/api/calculator/sqrt")
                .param("number", String.valueOf(number)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value(number))
                .andExpect(jsonPath("$.result").value(expectedResult))
                .andExpect(jsonPath("$.operation").value("square root"));
    }

    @Test
    @DisplayName("Should handle square root of negative number error")
    void testSquareRootNegativeNumberEndpoint() throws Exception {
        // Given
        double number = -4.0;

        when(calculatorService.squareRoot(number))
                .thenThrow(new IllegalArgumentException("Cannot calculate square root of negative number"));

        // When & Then
        mockMvc.perform(get("/api/calculator/sqrt")
                .param("number", String.valueOf(number)))
                .andExpect(status().isInternalServerError());
    }

    // Absolute Value Tests
    @Test
    @DisplayName("Should perform absolute value calculation via GET endpoint")
    void testAbsoluteEndpoint() throws Exception {
        // Given
        double number = -5.0;
        double expectedResult = 5.0;

        when(calculatorService.absolute(number)).thenReturn(expectedResult);

        // When & Then
        mockMvc.perform(get("/api/calculator/abs")
                .param("number", String.valueOf(number)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value(number))
                .andExpect(jsonPath("$.result").value(expectedResult))
                .andExpect(jsonPath("$.operation").value("absolute value"));
    }

    // Percentage Tests
    @Test
    @DisplayName("Should perform percentage calculation via GET endpoint")
    void testPercentageEndpoint() throws Exception {
        // Given
        double number = 100.0;
        double percentage = 20.0;
        double expectedResult = 20.0;

        when(calculatorService.percentage(number, percentage)).thenReturn(expectedResult);

        // When & Then
        mockMvc.perform(get("/api/calculator/percentage")
                .param("number", String.valueOf(number))
                .param("percentage", String.valueOf(percentage)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value(number))
                .andExpect(jsonPath("$.percentage").value(percentage))
                .andExpect(jsonPath("$.result").value(expectedResult))
                .andExpect(jsonPath("$.operation").value("percentage"));
    }

    // Error Handling Tests
    @Test
    @DisplayName("Should return 400 for missing parameters")
    void testMissingParameters() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/calculator/add"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return 400 for invalid parameter format")
    void testInvalidParameterFormat() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/calculator/add")
                .param("a", "invalid")
                .param("b", "3.0"))
                .andExpect(status().isBadRequest());
    }
}