package org.minemath.client;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.ValidationResult;
import net.objecthunter.exp4j.operator.Operator;

public class StrToMathExpression {
    public static String evaluateMathExpression(String expression) {

        String StrMath = expression.replace("√", "sqrt").replace("%", "/100");
        try {
            ValidationResult result = validateExpression(StrMath);
            if (result.isValid()) {
                Operator factorial = new Operator("!", 1, true, Operator.PRECEDENCE_POWER + 1) {

                    @Override
                    public double apply(double... args) {
                        final int arg = (int) args[0];
                        if ((double) arg != args[0]) {
                            throw new IllegalArgumentException("Operand for factorial has to be an integer");
                        }
                        if (arg < 0) {
                            throw new IllegalArgumentException("The operand of the factorial can not be less than zero");
                        }
                        double result = 1;
                        for (int i = 1; i <= arg; i++) {
                            result *= i;
                        }
                        return result;
                    }
                };
                Expression exp = new ExpressionBuilder(StrMath).operator(factorial).build();
                double calculatedResult = exp.evaluate();
                return String.valueOf(calculatedResult);
            } else {
                return "Error: Invalid Expression";
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private static ValidationResult validateExpression(String expression) {
        Operator factorial = new Operator("!", 1, true, Operator.PRECEDENCE_POWER + 1) {

            @Override
            public double apply(double... args) {
                final int arg = (int) args[0];
                if ((double) arg != args[0]) {
                    throw new IllegalArgumentException("Operand for factorial has to be an integer");
                }
                if (arg < 0) {
                    throw new IllegalArgumentException("The operand of the factorial can not be less than zero");
                }
                double result = 1;
                for (int i = 1; i <= arg; i++) {
                    result *= i;
                }
                return result;
            }
        };
        return new ExpressionBuilder(expression)
                .operator(factorial)
                .build()
                .validate();
    }
}
