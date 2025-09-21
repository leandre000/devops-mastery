package com.rca.demo_course.domain;

public class CalculatorModel {

    public double add(double a, double b) {
        return a + b;
    }


    private double subtract(double a, double b) {
        return a - b;
    }


    private double multiply(double a, double b) {
        return a * b;
    }


    private double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed");
        }
        return a / b;
    }


    private double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }


    private double squareRoot(double number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of negative number");
        }
        return Math.sqrt(number);
    }


    private double absolute(double number) {
        return Math.abs(number);
    }


    private double percentage(double number, double percentage) {
        return (number * percentage) / 100;
    }

    public static void main(String[] args) {
        CalculatorModel model = new CalculatorModel();
        System.out.println(model.add(1, 2));
        System.out.println(model.subtract(1, 2));
        System.out.println(model.multiply(1, 2));
        System.out.println(model.divide(1, 2));
        System.out.println(model.power(1, 2));
        System.out.println(model.squareRoot(1));
    }
}
