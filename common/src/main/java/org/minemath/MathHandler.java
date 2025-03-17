package org.minemath;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;

import java.util.HashMap;

public class MathHandler {
    private String MathExpression = "";
    private static final HashMap<Integer, String> mathExpressions = new HashMap<>() {{
        put(5, "√(");
        put(6, "π");
        put(7, "^");
        put(10, "e(");
        put(11, "(");
        put(12, ")");
        put(13, "%");
        put(14, "!");
        put(15, "log(");
        put(16, "7");
        put(17, "8");
        put(18, "9");
        put(19, "/");
        put(20, "sin(");
        put(21, "4");
        put(22, "5");
        put(23, "6");
        put(24, "*");
        put(25, "cos(");
        put(26, "1");
        put(27, "2");
        put(28, "3");
        put(29, "-");
        put(30, "tan(");
        put(31, "0");
        put(32, ".");
        put(34, "+");
    }};

    public String getMathExpression() {
        return MathExpression;
    }

    public void setMathExpression(String mathExpression) {
        MathExpression = mathExpression;
    }

    public String calculateMathExpression() {
        try {
            // Create JexlEngine
            JexlEngine jexl = new JexlBuilder().create();
            JexlContext context = new MapContext();

            // Add Math class to context
            context.set("Math", Math.class);

            // Format expression for JEXL
            String jexlExpr = MathExpression
                    .replace("√(", "Math.sqrt(")
                    .replace("π", "Math.PI")
                    .replace("sin(", "Math.sin(")
                    .replace("cos(", "Math.cos(")
                    .replace("tan(", "Math.tan(")
                    .replace("log(", "Math.log10(")
                    .replace("e(", "Math.exp(")
                    .replace("^", "**");

            // Evaluate expression
            JexlExpression expression = jexl.createExpression(jexlExpr);
            Object result = expression.evaluate(context);

            // Format result: show integers without decimal point
            if (result instanceof Double) {
                double d = (Double) result;
                if (d == Math.floor(d) && !Double.isInfinite(d)) {
                    return String.format("%.0f", d);
                }
            }

            return String.valueOf(result);
        } catch (Exception e) {
            return "Error";
        }
    }

    public void buttonHandler(int buttonId) {
        switch (buttonId){
            case 0:
                //TODO: History Screen
                break;
            case 1:
                //TODO: Param Screen
                break;
            case 3:
                //TODO: ANS
                break;
            case 4:
                //TODO: AC
                break;
            case 8:
                //TODO: DEL
                break;
            case 9:
                //TODO: OFF
                break;
            case 33:
                MathExpression = calculateMathExpression();
                break;
            default:
                MathExpression += mathExpressions.get(buttonId);
        }
    }
}
