package com.rca.demo_course.service.impl;

import com.rca.demo_course.service.CalculatorService;
import org.springframework.stereotype.Service;

/**
 * Implementation of CalculatorService.
 * Provides basic arithmetic and mathematical operations.
 */
@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public double add(double a, double b) {
        return a + b;
    }

    @Override
    public double subtract(double a, double b) {
        return a - b;
    }

    @Override
    public double multiply(double a, double b) {
        return a * b;
    }

    @Override
    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed");
        }
        return a / b;
    }

    @Override
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    @Override
    public double squareRoot(double number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of negative number");
        }
        return Math.sqrt(number);
    }

    @Override
    public double absolute(double number) {
        return Math.abs(number);
    }

    @Override
    public double percentage(double number, double percentage) {
        return (number * percentage) / 100;
    }
}
