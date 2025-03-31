package org.minemath;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;


public class CalculatorScreen extends Screen {

    private final MathHandler resultMath = new MathHandler();
    private TextFieldWidget resultWidget;
    private final static Identifier CALCULATOR_TEXTURE = new Identifier("minemath", "textures/calculator/calculator.png");

    public CalculatorScreen(Text title) {
        super(title);
    }

    public void setResultMath(String resultMath) {
        this.resultMath.setMathExpression(resultMath);
    }

    /**
     * Initialize the calculator screen.
     * This method is called when the screen is first opened.
     * It initializes the calculator buttons and the result text field.
     */
    @Override
    protected void init() {
        int weightScale = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int heightScale = MinecraftClient.getInstance().getWindow().getScaledHeight();

        this.resultWidget = new TextFieldWidget(this.textRenderer, weightScale/2-40, heightScale/2-59, 75, 20, Text.empty());
        this.resultWidget.setDrawsBackground(false);
        this.resultWidget.setText(resultMath.getMathExpression());
        addDrawableChild(this.resultWidget);

        List<CalculatorButtonWidget> buttons = new ArrayList<>();

        for (int i = 0; i < 35; i++) {
            int x = i % 5;
            int y = i / 5;
            int finalI = i;
            buttons.add(new CalculatorButtonWidget(weightScale/2-44 + x*18 , heightScale/2-40 + y*15, i,
                    (button) -> {
                        resultMath.setMathExpression(resultWidget.getText());
                        resultMath.buttonHandler(finalI);
                        resultWidget.setText(resultMath.getMathExpression());
                    }));
            addDrawableChild(buttons.get(buttons.size() - 1));
        }
    }

    /**
     * Render the calculator screen.
     * This method is called every frame to render the calculator screen.
     * It will render the calculator background and all the buttons.
     */
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int weightScale = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int heightScale = MinecraftClient.getInstance().getWindow().getScaledHeight();

        context.drawTexture(CALCULATOR_TEXTURE, weightScale/2-55, heightScale/2-77, 0, 0, 110, 154, 110, 154);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        switch (keyCode) {
            case 257: // ENTER
                resultMath.setMathExpression(resultWidget.getText());
                resultMath.buttonHandler(33); // ID du bouton "="
                resultWidget.setText(resultMath.getMathExpression());
                return true;
            case 259: // BACKSPACE
                resultMath.setMathExpression(resultWidget.getText());
                resultMath.buttonHandler(8); // ID du bouton "backspace"
                resultWidget.setText(resultMath.getMathExpression());
                return true;
            default:
                if (this.resultWidget.isFocused() && this.resultWidget.isVisible()) {
                    return this.resultWidget.keyPressed(keyCode, scanCode, modifiers);
                }
                return super.keyPressed(keyCode, scanCode, modifiers);
        }
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        resultMath.setMathExpression(resultWidget.getText());

        switch (chr) {
            case '=':
                resultMath.buttonHandler(33);
                break;
            case '+':
                resultMath.buttonHandler(34);
                break;
            case '-':
                resultMath.buttonHandler(29);
                break;
            case '*':
                resultMath.buttonHandler(24);
                break;
            case '/':
                resultMath.buttonHandler(19);
                break;
            case '(':
                resultMath.buttonHandler(11);
                break;
            case ')':
                resultMath.buttonHandler(12);
                break;
            case '%':
                resultMath.buttonHandler(13);
                break;
            case '^':
                resultMath.buttonHandler(7);
                break;
            case '.':
                resultMath.buttonHandler(32);
                break;
            case '!':
                resultMath.buttonHandler(14);
                break;
            default:
                // for the other characters, we check if they are alphanumeric
                if ((chr >= '0' && chr <= '9') || (chr >= 'a' && chr <= 'z') || (chr >= 'A' && chr <= 'Z')) {
                    this.resultWidget.setFocused(true);
                    return this.resultWidget.charTyped(chr, modifiers);
                }
        }

        resultWidget.setText(resultMath.getMathExpression());
        return true;
    }
}