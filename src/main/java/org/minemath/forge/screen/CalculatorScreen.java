package org.minemath.forge.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.minemath.forge.math.MathHandler;

import java.util.ArrayList;
import java.util.List;


public class CalculatorScreen extends Screen {

    private final MathHandler resultMath = new MathHandler();
    private EditBox resultWidget;
    private final static ResourceLocation CALCULATOR_TEXTURE = ResourceLocation.fromNamespaceAndPath("minemath", "textures/calculator/calculator.png");

    public CalculatorScreen(Component title) {
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
        int weightScale = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int heightScale = Minecraft.getInstance().getWindow().getGuiScaledHeight();

        this.resultWidget = new EditBox(this.font, weightScale/2-40, heightScale/2-59, 75, 20, Component.empty());
        this.resultWidget.setBordered(false);
        this.resultWidget.setValue(resultMath.getMathExpression());
        addRenderableWidget(this.resultWidget);

        List<CalculatorButtonWidget> buttons = new ArrayList<>();

        for (int i = 0; i < 35; i++) {
            int x = i % 5;
            int y = i / 5;
            int finalI = i;
            buttons.add(new CalculatorButtonWidget(weightScale/2-44 + x*18 , heightScale/2-40 + y*15, i,
                    (button) -> {
                        resultMath.setMathExpression(resultWidget.getValue());
                        resultMath.buttonHandler(finalI);
                        resultWidget.setValue(resultMath.getMathExpression());
                    }));
            addRenderableWidget(buttons.get(buttons.size() - 1));
        }
    }

    /**
     * Render the calculator screen.
     * This method is called every frame to render the calculator screen.
     * It will render the calculator background and all the buttons.
     */
    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        int weightScale = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int heightScale = Minecraft.getInstance().getWindow().getGuiScaledHeight();

        context.blit(RenderType::guiTexturedOverlay, CALCULATOR_TEXTURE, weightScale/2-55, heightScale/2-77, 0, 0, 110, 154, 110, 154);

        super.render(context, mouseX, mouseY, delta);
    }

    /**
     * Handle key presses.
     * This method is called when a key is pressed.
     * It will handle the ENTER and BACKSPACE keys to perform calculations or delete characters.
     */
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        switch (keyCode) {
            case 256: // ESCAPE
                this.onClose();
                return true;
            case 257: // ENTER
                resultMath.setMathExpression(resultWidget.getValue());
                resultMath.buttonHandler(33); // ID du bouton "="
                resultWidget.setValue(resultMath.getMathExpression());
                return true;
            case 259: // BACKSPACE
                resultMath.setMathExpression(resultWidget.getValue());
                resultMath.buttonHandler(8); // ID du bouton "backspace"
                resultWidget.setValue(resultMath.getMathExpression());
                return true;
            default:
                if (this.resultWidget.isFocused() && this.resultWidget.isVisible()) {
                    return this.resultWidget.keyPressed(keyCode, scanCode, modifiers);
                }
                return super.keyPressed(keyCode, scanCode, modifiers);
        }
    }

    /**
     * Handle character typing.
     * This method is called when a character is typed.
     * It will handle the characters that are not handled by the buttons.
     */
    @Override
    public boolean charTyped(char chr, int modifiers) {
        resultMath.setMathExpression(resultWidget.getValue());

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

        resultWidget.setValue(resultMath.getMathExpression());
        return true;
    }
}