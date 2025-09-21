package com.rca.demo_course.controller;

import com.rca.demo_course.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for calculator operations.
 * Provides HTTP endpoints for basic arithmetic and mathematical operations.
 */
@RestController
@RequestMapping("/api/calculator")
@CrossOrigin(origins = "*")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    /**
     * Adds two numbers.
     *
     * @param a the first number
     * @param b the second number
     * @return the sum of a and b
     */
    @GetMapping("/add")
    public ResponseEntity<Map<String, Object>> add(
            @RequestParam double a,
            @RequestParam double b) {
        double result = calculatorService.add(a, b);
        return ResponseEntity.ok(createResponse(a, b, result, "addition"));
    }

    /**
     * Subtracts the second number from the first.
     *
     * @param a the first number
     * @param b the number to subtract
     * @return the difference of a and b
     */
    @GetMapping("/subtract")
    public ResponseEntity<Map<String, Object>> subtract(
            @RequestParam double a,
            @RequestParam double b) {
        double result = calculatorService.subtract(a, b);
        return ResponseEntity.ok(createResponse(a, b, result, "subtraction"));
    }

    /**
     * Multiplies two numbers.
     *
     * @param a the first number
     * @param b the second number
     * @return the product of a and b
     */
    @GetMapping("/multiply")
    public ResponseEntity<Map<String, Object>> multiply(
            @RequestParam double a,
            @RequestParam double b) {
        double result = calculatorService.multiply(a, b);
        return ResponseEntity.ok(createResponse(a, b, result, "multiplication"));
    }

    /**
     * Divides the first number by the second.
     *
     * @param a the dividend
     * @param b the divisor
     * @return the quotient of a and b
     */
    @GetMapping("/divide")
    public ResponseEntity<Map<String, Object>> divide(
            @RequestParam double a,
            @RequestParam double b) {
        double result = calculatorService.divide(a, b);
        return ResponseEntity.ok(createResponse(a, b, result, "division"));
    }

    /**
     * Raises the first number to the power of the second.
     *
     * @param base the base number
     * @param exponent the exponent
     * @return base raised to the power of exponent
     */
    @GetMapping("/power")
    public ResponseEntity<Map<String, Object>> power(
            @RequestParam double base,
            @RequestParam double exponent) {
        double result = calculatorService.power(base, exponent);
        Map<String, Object> response = new HashMap<>();
        response.put("base", base);
        response.put("exponent", exponent);
        response.put("result", result);
        response.put("operation", "power");
        return ResponseEntity.ok(response);
    }

    /**
     * Calculates the square root of a number.
     *
     * @param number the number to find the square root of
     * @return the square root of the number
     */
    @GetMapping("/sqrt")
    public ResponseEntity<Map<String, Object>> squareRoot(@RequestParam double number) {
        double result = calculatorService.squareRoot(number);
        Map<String, Object> response = new HashMap<>();
        response.put("number", number);
        response.put("result", result);
        response.put("operation", "square root");
        return ResponseEntity.ok(response);
    }

    /**
     * Calculates the absolute value of a number.
     *
     * @param number the number
     * @return the absolute value of the number
     */
    @GetMapping("/abs")
    public ResponseEntity<Map<String, Object>> absolute(@RequestParam double number) {
        double result = calculatorService.absolute(number);
        Map<String, Object> response = new HashMap<>();
        response.put("number", number);
        response.put("result", result);
        response.put("operation", "absolute value");
        return ResponseEntity.ok(response);
    }

    /**
     * Calculates the percentage of a number.
     *
     * @param number the base number
     * @param percentage the percentage to calculate
     * @return the percentage of the number
     */
    @GetMapping("/percentage")
    public ResponseEntity<Map<String, Object>> percentage(
            @RequestParam double number,
            @RequestParam double percentage) {
        double result = calculatorService.percentage(number, percentage);
        Map<String, Object> response = new HashMap<>();
        response.put("number", number);
        response.put("percentage", percentage);
        response.put("result", result);
        response.put("operation", "percentage");
        return ResponseEntity.ok(response);
    }

    /**
     * Creates a standardized response for binary operations.
     *
     * @param a first operand
     * @param b second operand
     * @param result calculation result
     * @param operation operation name
     * @return response map
     */
    private Map<String, Object> createResponse(double a, double b, double result, String operation) {
        Map<String, Object> response = new HashMap<>();
        response.put("a", a);
        response.put("b", b);
        response.put("result", result);
        response.put("operation", operation);
        return response;
    }
}
