package com.denis.algorithms.queue.calculator;

public class CalculatorTest {
    public static void main(String[] args) {
        StackBasedCalculator calculator = new StackBasedCalculator();

        calculator.infixToPostfix("A + B – C");

    }
}
