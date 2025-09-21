package com.rca.demo_course.service;

/**
 * Service interface for calculator operations.
 * Provides basic arithmetic and mathematical operations.
 */
public interface CalculatorService {

    /**
     * Adds two numbers.
     *
     * @param a the first number
     * @param b the second number
     * @return the sum of a and b
     */
    double add(double a, double b);

    /**
     * Subtracts the second number from the first.
     *
     * @param a the first number
     * @param b the number to subtract
     * @return the difference of a and b
     */
    double subtract(double a, double b);

    /**
     * Multiplies two numbers.
     *
     * @param a the first number
     * @param b the second number
     * @return the product of a and b
     */
    double multiply(double a, double b);

    /**
     * Divides the first number by the second.
     *
     * @param a the dividend
     * @param b the divisor
     * @return the quotient of a and b
     * @throws IllegalArgumentException if b is zero
     */
    double divide(double a, double b);

    /**
     * Raises the first number to the power of the second.
     *
     * @param base the base number
     * @param exponent the exponent
     * @return base raised to the power of exponent
     */
    double power(double base, double exponent);

    /**
     * Calculates the square root of a number.
     *
     * @param number the number to find the square root of
     * @return the square root of the number
     * @throws IllegalArgumentException if number is negative
     */
    double squareRoot(double number);

    /**
     * Calculates the absolute value of a number.
     *
     * @param number the number
     * @return the absolute value of the number
     */
    double absolute(double number);

    /**
     * Calculates the percentage of a number.
     *
     * @param number the base number
     * @param percentage the percentage to calculate
     * @return the percentage of the number
     */
    double percentage(double number, double percentage);
}
