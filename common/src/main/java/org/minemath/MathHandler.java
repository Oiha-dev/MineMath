package org.minemath;

import com.ezylang.evalex.bigmath.BigMathExpression;
import com.ezylang.evalex.config.ExpressionConfiguration;
import com.ezylang.evalex.data.EvaluationValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles mathematical expression evaluation for the MineMath calculator.
 * This class manages the current expression, calculation logic, and expression history.
 * It uses EvalEx-big-math library for high-precision mathematical operations.
 */
public class MathHandler {
    private String MathExpression = "";
    private String PreviousExpression = "";
    private List<String> MathList = new ArrayList<>();

    /** Map of button IDs to mathematical expressions. */
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

    /**
     * Evaluates the current mathematical expression and returns the result as a string.
     * If an error occurs during calculation, returns "Error".
     * The result is also added to the history list.
     * @return the result of the mathematical expression
     */
    public String calculateMathExpression() {
        try {
            String exprStr = MathExpression;

            exprStr = handleFactorial(exprStr);

            exprStr = exprStr
                    .replace("√(", "ROOT(")
                    .replace("π", "PI()")
                    .replace("e(", "EXP(")
                    .replace("log(", "LOG10(")
                    .replace("sin(", "SIN(")
                    .replace("cos(", "COS(")
                    .replace("tan(", "TAN(");

            ExpressionConfiguration config = ExpressionConfiguration.builder()
                    .mathContext(new MathContext(15, RoundingMode.HALF_UP))
                    .decimalPlacesRounding(10)
                    .build();

            BigMathExpression expression = new BigMathExpression(exprStr, config);
            EvaluationValue result = expression.evaluate();

            String resultStr;
            BigDecimal value = result.getNumberValue();
            if (value.stripTrailingZeros().scale() <= 0) {
                resultStr = value.toBigInteger().toString();
            } else {
                resultStr = value.stripTrailingZeros().toPlainString();
            }

            addToList();
            return resultStr;
        } catch (Exception e) {
            System.out.println("Calculation error: " + e.getMessage());
            return "Error";
        }
    }

    /**
     * Replaces factorial expressions in the given mathematical expression with the FACT function.
     * @param expr the mathematical expression
     * @return the expression with factorial expressions replaced
     */
    private String handleFactorial(String expr) {
        Pattern pattern = Pattern.compile("(\\d+|\\([^)]+\\))!");
        Matcher matcher = pattern.matcher(expr);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String match = matcher.group();
            String arg = match.substring(0, match.length() - 1);
            matcher.appendReplacement(result, "FACT(" + arg + ")");
        }
        matcher.appendTail(result);

        return result.toString();
    }

    /**
     * Handles button clicks for the calculator screen.
     * @param buttonId the ID of the button clicked
     */
    public void buttonHandler(int buttonId) {
        switch (buttonId){
            case 0:
                MinecraftClient.getInstance().setScreen(new HistoryScreen(Text.empty(), MathList));
                break;
            case 1:
                //TODO: Param Screen
                break;
            case 3:
                if (!PreviousExpression.equals("Error")) {
                    MathExpression = MathExpression + PreviousExpression;
                }
                break;
            case 4:
                MathExpression = "";
                break;
            case 8:
                if (MathExpression.length() > 0) {
                    MathExpression = MathExpression.substring(0, MathExpression.length() - 1);
                }
                break;
            case 9:
                MinecraftClient.getInstance().setScreen(null);
                break;
            case 33:
                MathExpression = calculateMathExpression();
                PreviousExpression = MathExpression;
                break;
            default:
                if (mathExpressions.containsKey(buttonId)) {
                    MathExpression += mathExpressions.get(buttonId);
                }
        }
    }

    /**
     * Adds the current expression to the history list.
     * If the list exceeds 5 expressions, removes the oldest expression.
     */
    public void addToList() {
        MathList.add(MathExpression);
        if (MathList.size() > 5) {
            MathList.remove(0);
        }
    }
}