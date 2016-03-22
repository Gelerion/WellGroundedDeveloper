package com.denis.algorithms.queue.calculator;

import java.util.Stack;

import static com.denis.algorithms.queue.calculator.StackBasedCalculator.Operator.*;

public class StackBasedCalculator {
    Stack<Operator> operatorsStack;
    String postfixExpression;

    Postfix infixToPostfix(String infixExpression) {
        infixExpression = infixExpression.replaceAll(" ", "");
        char[] chars = infixExpression.toCharArray();

        for (char aChar : chars) {
            if (isOperator(aChar)) {
                if (operatorsStack.empty()) operatorsStack.push(getOperator(aChar));

                Operator previous = operatorsStack.pop();


            }
        }
        return new Postfix();
    }

    private boolean isOperator(char aChar) {
        return PLUS.operator == aChar ||
                MINUS.operator == aChar ||
                MULTIPLY.operator == aChar ||
                DIVIDE.operator == aChar ||
                OPEN_BRACKET.operator == aChar ||
                CLOSE_BRACKET.operator == aChar;
    }

    class Postfix {
        int evaluate() {
            return 0;
        }

        void display() {

        }
    }

    enum Operator {
        PLUS('+', 1),
        MINUS('-', 1),
        MULTIPLY('*', 2),
        DIVIDE('/', 2),
        OPEN_BRACKET('(', 3),
        CLOSE_BRACKET(')', 3);

        char operator;
        int priority;

        Operator(char operator, int priority) {
            this.operator = operator;
            this.priority = priority;
        }

        static Operator getOperator(char aChar) {
            switch (aChar) {
                case '+':
                    return PLUS;
                case '-':
                    return MINUS;
                case '*':
                    return MULTIPLY;
                case '/':
                    return DIVIDE;
                case '(':
                    return OPEN_BRACKET;
                case ')':
                    return CLOSE_BRACKET;
                default:
                    throw new UnsupportedOperationException("Not an operator");
            }
        }
    }
}
