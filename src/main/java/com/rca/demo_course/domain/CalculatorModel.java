package com.rca.demo_course.domain;

import com.rca.demo_course.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculatorModel {

    @Autowired
    private CalculatorService calculatorService;

    public double add(double a, double b) {
        return calculatorService.add(a, b);
    }


    public double subtract(double a, double b) {
        return calculatorService.subtract(a, b);
    }

    public double multiply(double a, double b) {
        return calculatorService.multiply(a, b);
    }

    public double divide(double a, double b) {
        return calculatorService.divide(a, b);
    }

    public double power(double base, double exponent) {
        return calculatorService.power(base, exponent);
    }

    public double squareRoot(double number) {
        return calculatorService.squareRoot(number);
    }

    public double absolute(double number) {
        return calculatorService.absolute(number);
    }

    public double percentage(double number, double percentage) {
        return calculatorService.percentage(number, percentage);
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
