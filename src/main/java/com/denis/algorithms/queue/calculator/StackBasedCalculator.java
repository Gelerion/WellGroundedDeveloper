package com.denis.algorithms.queue.calculator;

import java.util.Stack;

public class StackBasedCalculator {
    Stack<Character> operators = new Stack<>();
    String postfixExpression = "";

    Postfix infixToPostfix(String infixExpression) {
        infixExpression = infixExpression.replaceAll(" ", "");
        char[] chars = infixExpression.toCharArray();

        for (char aChar : chars) {
            switch (aChar) {
                case '+':
                case '-':
                    push(aChar, 1);
                    break;
                case '*':
                case '/':
                    push(aChar, 2);
                    break;
                case '(':
                    operators.push(aChar);
                case ')':
                    revalidate();
                    break;
                default:
                    postfixExpression += Character.getNumericValue(aChar);
            }
        }

        while (!operators.empty()) {
            postfixExpression += operators.pop();
        }

        return new Postfix();
    }

    private void revalidate() {
        while (!operators.empty()) {
            Character top = operators.pop();
            if(top == '(') break;

            postfixExpression += top;
        }
    }

    private void push(char currentOperator, int currentOperatorPriority) {
        while (!operators.empty()) {
            Character topOperator = operators.pop();

            if(topOperator == '(') {
                operators.push(topOperator);
                break;
            }

            int topOperatorPriority;
            if (topOperator == '*' || topOperator == '/') topOperatorPriority = 2;
            else topOperatorPriority = 1;

            if (topOperatorPriority < currentOperatorPriority) {
                operators.push(topOperator);
                break;
            }
            else {
                postfixExpression += topOperator;
            }
        }

        operators.push(currentOperator);
    }


    class Postfix {
        int evaluate() {
            postfixExpression = "147+3-";
            char[] chars = postfixExpression.toCharArray();

            for (char aChar : chars) {
                System.out.println(Character.getNumericValue(aChar));
            }
            return 0;
        }

        Postfix display() {
            System.out.println(postfixExpression);
            return this;
        }
    }
}
