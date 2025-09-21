package com.rca.demo_course.controller;

import com.rca.demo_course.service.CalculatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Comprehensive integration tests for CalculatorController.
 */
@WebMvcTest(CalculatorController.class)
@DisplayName("Calculator Controller Integration Tests")
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculatorService calculatorService;

    @Test
    @DisplayName("Add endpoint should return correct result")
    void testAddEndpoint() throws Exception {
        when(calculatorService.add(2.0, 3.0)).thenReturn(5.0);

        mockMvc.perform(get("/api/calculator/add")
                .param("a", "2.0")
                .param("b", "3.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.a").value(2.0))
                .andExpect(jsonPath("$.b").value(3.0))
                .andExpect(jsonPath("$.result").value(5.0))
                .andExpect(jsonPath("$.operation").value("addition"));

        verify(calculatorService, times(1)).add(2.0, 3.0);
    }

    @Test
    void testSubtractEndpoint() throws Exception {
        when(calculatorService.subtract(5.0, 2.0)).thenReturn(3.0);

        mockMvc.perform(get("/api/calculator/subtract")
                .param("a", "5.0")
                .param("b", "2.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(3.0))
                .andExpect(jsonPath("$.operation").value("subtraction"));
    }

    @Test
    void testMultiplyEndpoint() throws Exception {
        when(calculatorService.multiply(4.0, 3.0)).thenReturn(12.0);

        mockMvc.perform(get("/api/calculator/multiply")
                .param("a", "4.0")
                .param("b", "3.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(12.0))
                .andExpect(jsonPath("$.operation").value("multiplication"));
    }

    @Test
    void testDivideEndpoint() throws Exception {
        when(calculatorService.divide(10.0, 2.0)).thenReturn(5.0);

        mockMvc.perform(get("/api/calculator/divide")
                .param("a", "10.0")
                .param("b", "2.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(5.0))
                .andExpect(jsonPath("$.operation").value("division"));
    }

    @Test
    void testPowerEndpoint() throws Exception {
        when(calculatorService.power(2.0, 3.0)).thenReturn(8.0);

        mockMvc.perform(get("/api/calculator/power")
                .param("base", "2.0")
                .param("exponent", "3.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.base").value(2.0))
                .andExpect(jsonPath("$.exponent").value(3.0))
                .andExpect(jsonPath("$.result").value(8.0))
                .andExpect(jsonPath("$.operation").value("power"));
    }

    @Test
    void testSquareRootEndpoint() throws Exception {
        when(calculatorService.squareRoot(9.0)).thenReturn(3.0);

        mockMvc.perform(get("/api/calculator/sqrt")
                .param("number", "9.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value(9.0))
                .andExpect(jsonPath("$.result").value(3.0))
                .andExpect(jsonPath("$.operation").value("square root"));
    }

    @Test
    void testAbsoluteEndpoint() throws Exception {
        when(calculatorService.absolute(-5.0)).thenReturn(5.0);

        mockMvc.perform(get("/api/calculator/abs")
                .param("number", "-5.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value(-5.0))
                .andExpect(jsonPath("$.result").value(5.0))
                .andExpect(jsonPath("$.operation").value("absolute value"));
    }

    @Test
    @DisplayName("Percentage endpoint should return correct result")
    void testPercentageEndpoint() throws Exception {
        when(calculatorService.percentage(100.0, 20.0)).thenReturn(20.0);

        mockMvc.perform(get("/api/calculator/percentage")
                .param("number", "100.0")
                .param("percentage", "20.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value(100.0))
                .andExpect(jsonPath("$.percentage").value(20.0))
                .andExpect(jsonPath("$.result").value(20.0))
                .andExpect(jsonPath("$.operation").value("percentage"));
    }

    // Error Handling Tests

    @Test
    @DisplayName("Divide endpoint should handle division by zero")
    void testDivideByZeroError() throws Exception {
        when(calculatorService.divide(5.0, 0.0))
                .thenThrow(new IllegalArgumentException("Division by zero is not allowed"));

        mockMvc.perform(get("/api/calculator/divide")
                .param("a", "5.0")
                .param("b", "0.0"))
                .andExpect(status().isBadRequest());

        verify(calculatorService, times(1)).divide(5.0, 0.0);
    }

    @Test
    @DisplayName("Square root endpoint should handle negative numbers")
    void testSquareRootNegativeError() throws Exception {
        when(calculatorService.squareRoot(-1.0))
                .thenThrow(new IllegalArgumentException("Cannot calculate square root of negative number"));

        mockMvc.perform(get("/api/calculator/sqrt")
                .param("number", "-1.0"))
                .andExpect(status().isBadRequest());

        verify(calculatorService, times(1)).squareRoot(-1.0);
    }

    // Edge Case Tests

    @Test
    @DisplayName("Add endpoint should handle large numbers")
    void testAddLargeNumbers() throws Exception {
        when(calculatorService.add(Double.MAX_VALUE, 1.0)).thenReturn(Double.POSITIVE_INFINITY);

        mockMvc.perform(get("/api/calculator/add")
                .param("a", String.valueOf(Double.MAX_VALUE))
                .param("b", "1.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(Double.POSITIVE_INFINITY));
    }

    @Test
    @DisplayName("Multiply endpoint should handle zero")
    void testMultiplyWithZero() throws Exception {
        when(calculatorService.multiply(5.0, 0.0)).thenReturn(0.0);

        mockMvc.perform(get("/api/calculator/multiply")
                .param("a", "5.0")
                .param("b", "0.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(0.0));
    }

    @Test
    @DisplayName("Power endpoint should handle fractional exponents")
    void testPowerFractionalExponent() throws Exception {
        when(calculatorService.power(4.0, 0.5)).thenReturn(2.0);

        mockMvc.perform(get("/api/calculator/power")
                .param("base", "4.0")
                .param("exponent", "0.5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(2.0));
    }

    @Test
    @DisplayName("Absolute endpoint should handle negative numbers")
    void testAbsoluteNegativeNumber() throws Exception {
        when(calculatorService.absolute(-5.5)).thenReturn(5.5);

        mockMvc.perform(get("/api/calculator/abs")
                .param("number", "-5.5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(5.5));
    }

    // Parameter Validation Tests

    @Test
    @DisplayName("Endpoints should handle missing parameters")
    void testMissingParameters() throws Exception {
        mockMvc.perform(get("/api/calculator/add")
                .param("a", "5.0"))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/api/calculator/multiply"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Endpoints should handle invalid parameter formats")
    void testInvalidParameterFormats() throws Exception {
        mockMvc.perform(get("/api/calculator/add")
                .param("a", "invalid")
                .param("b", "3.0"))
                .andExpect(status().isBadRequest());
    }

    // Content Type Tests

    @Test
    @DisplayName("Endpoints should return JSON content type")
    void testContentType() throws Exception {
        when(calculatorService.add(2.0, 3.0)).thenReturn(5.0);

        mockMvc.perform(get("/api/calculator/add")
                .param("a", "2.0")
                .param("b", "3.0"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    // CORS Tests

    @Test
    @DisplayName("Endpoints should support CORS")
    void testCorsSupport() throws Exception {
        when(calculatorService.add(2.0, 3.0)).thenReturn(5.0);

        mockMvc.perform(get("/api/calculator/add")
                .header("Origin", "http://localhost:3000")
                .param("a", "2.0")
                .param("b", "3.0"))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"));
    }
}
