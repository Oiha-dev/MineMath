package org.minemath.client.gui;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.InputResult;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.WSprite;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

import org.minemath.client.StrToMathExpression;

import java.util.Objects;

import static net.fabricmc.api.EnvType.CLIENT;


public class CalculatorMathGUI extends LightweightGuiDescription {

    private StrToMathExpression mathExpression;
    public String ANS;

    public String Input = "";
    public WSprite icon = new WSprite(new Identifier("minemath:textures/calculator/calculator.png"));
    public WTextField InOutField = new WTextField();
    public WSprite Equal = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Equal");
            Input = InOutField.getText();
            InOutField.setText(StrToMathExpression.evaluateMathExpression(Input));
            ANS = StrToMathExpression.evaluateMathExpression(Input);
            return null;
        }
    };
    public WSprite Historical = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Historical");
            InOutField.setText("Coming Soon");
            return null;
        }
    };
    public WSprite Setting = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Setting");
            InOutField.setText("Coming Soon");
            return null;
        }
    };
    public WSprite Ans = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Ans");
            Input = InOutField.getText();
            Input += ANS;
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite AC = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("AC");
            Input = "";
            InOutField.setText("");
            return null;
        }
    };
    public WSprite Sqrt = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Sqrt");
            Input = InOutField.getText();
            Input += "√(";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Pi = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Pi");
            Input = InOutField.getText();
            Input += "π";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Power = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Power");
            Input = InOutField.getText();
            Input += "^(";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Del = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Del");
            Input = InOutField.getText();
            if (Input.length() > 0) {
                Input = Input.substring(0, Input.length() - 1);
            }
            InOutField.setText(Input);

            return null;
        }
    };
    public WSprite Off = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Off");
            MinecraftClient.getInstance().player.closeHandledScreen();
            return null;
        }
    };
    public WSprite Exp = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Exp");
            Input = InOutField.getText();
            Input += "exp(";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite OpenParenthesis = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("OpenParenthesis");
            Input = InOutField.getText();
            Input += "(";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite CloseParenthesis = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("CloseParenthesis");
            Input = InOutField.getText();
            Input += ")";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Percentage = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Percentage");
            Input = InOutField.getText();
            Input += "%";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Factorial = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Factorial");
            Input = InOutField.getText();
            Input += "!";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Log = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Log");
            Input = InOutField.getText();
            Input += "log(";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Seven = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Seven");
            Input = InOutField.getText();
            Input += "7";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Eight = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Eight");
            Input = InOutField.getText();
            Input += "8";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Nine = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Nine");
            Input = InOutField.getText();
            Input += "9";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Division = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Division");
            Input = InOutField.getText();
            Input += "/";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Sin = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Sin");
            Input = InOutField.getText();
            Input += "sin(";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Four = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Four");
            Input = InOutField.getText();
            Input += "4";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Five = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Five");
            Input = InOutField.getText();
            Input += "5";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Six = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Six");
            Input = InOutField.getText();
            Input += "6";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Multiplication = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Multiplication");
            Input = InOutField.getText();
            Input += "*";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Cos = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Cos");
            Input = InOutField.getText();
            Input += "cos(";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite One = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("One");
            Input = InOutField.getText();
            Input += "1";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Two = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Two");
            Input = InOutField.getText();
            Input += "2";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Three = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Three");
            Input = InOutField.getText();
            Input += "3";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Substract = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Substract");
            Input = InOutField.getText();
            Input += "-";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Tan = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Tan");
            Input = InOutField.getText();
            Input += "tan(";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Zero = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Zero");
            Input = InOutField.getText();
            Input += "0";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Dot = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Dot");
            Input = InOutField.getText();
            Input += ".";
            InOutField.setText(Input);
            return null;
        }
    };
    public WSprite Addition = new WSprite(new Identifier("minemath:textures/button/transparent.png")){
        @Override @Environment(CLIENT)
        public InputResult onClick(int x, int y, int button) {
            System.out.println("Addition");
            Input = InOutField.getText();
            Input += "+";
            InOutField.setText(Input);
            return null;
        }
    };
    public CalculatorMathGUI() {

        WPlainPanel root = new WPlainPanel();
        setRootPanel(root);
        root.setInsets(Insets.ROOT_PANEL);
        root.add(icon, 0, 1, 110, 154);
        root.add(InOutField, 12 ,13, 86, 20);
        root.add(Equal, 65, 126, 16, 12);
        root.add(Historical, 11, 38, 16, 12);
        root.add(Setting, 29, 38, 16, 12);
        root.add(Ans, 65, 38, 16, 12);
        root.add(AC, 83, 38, 16, 12);
        root.add(Sqrt, 11, 52, 16, 12);
        root.add(Pi, 29, 52, 16, 12);
        root.add(Power, 47, 52, 16, 12);
        root.add(Del, 65, 52, 16, 12);
        root.add(Off, 83, 52, 16, 12);
        root.add(Exp, 11, 66, 16, 12);
        root.add(OpenParenthesis, 29, 66, 16, 12);
        root.add(CloseParenthesis, 47, 66, 16, 12);
        root.add(Percentage, 65, 66, 16, 12);
        root.add(Factorial, 83, 66, 16, 12);
        root.add(Log, 11, 81, 16, 12);
        root.add(Seven, 29, 81, 16, 12);
        root.add(Eight, 47, 81, 16, 12);
        root.add(Nine, 65, 81, 16, 12);
        root.add(Division, 83, 81, 16, 12);
        root.add(Sin, 11, 96, 16, 12);
        root.add(Four, 29, 96, 16, 12);
        root.add(Five, 47, 96, 16, 12);
        root.add(Six, 65, 96, 16, 12);
        root.add(Multiplication, 83, 96, 16, 12);
        root.add(Cos, 11, 111, 16, 12);
        root.add(One, 29, 111, 16, 12);
        root.add(Two, 47, 111, 16, 12);
        root.add(Three, 65, 111, 16, 12);
        root.add(Substract, 83, 111, 16, 12);
        root.add(Tan, 11, 126, 16, 12);
        root.add(Zero, 29, 126, 16, 12);
        root.add(Dot, 47, 126, 16, 12);
        root.add(Addition, 83, 126, 16, 12);
        root.validate(this);
    }

    @Environment(CLIENT)
    @Override
    public void addPainters() {
        rootPanel.setBackgroundPainter(BackgroundPainter.createNinePatch(Objects.requireNonNull(Identifier.tryParse("minemath:textures/calculator/transparent.png"))));
    }
}
