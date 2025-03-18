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

    private MathHandler resultString = new MathHandler();
    private TextFieldWidget resultWidget;
    private final static Identifier CALCULATOR_TEXTURE = new Identifier("minemath", "textures/calculator/calculator.png");
    public CalculatorScreen(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        int weightScale = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int heightScale = MinecraftClient.getInstance().getWindow().getScaledHeight();

        this.resultWidget = new TextFieldWidget(this.textRenderer, weightScale/2-42, heightScale/2-59, 77, 20, Text.of("grdg"));
        this.resultWidget.setDrawsBackground(false);
        this.resultWidget.setText(resultString.getMathExpression());
        addDrawableChild(this.resultWidget);

        List<CalculatorButtonWidget> buttons = new ArrayList<>();

        for (int i = 0; i < 35; i++) {
            int x = i % 5;
            int y = i / 5;
            int finalI = i;
            buttons.add(new CalculatorButtonWidget(weightScale/2-44 + x*18 , heightScale/2-40 + y*15, i,
                    (button) -> {
                        resultString.setMathExpression(resultWidget.getText());
                        resultString.buttonHandler(finalI);
                        resultWidget.setText(resultString.getMathExpression());
                    }));
            addDrawableChild(buttons.get(buttons.size() - 1));
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int weightScale = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int heightScale = MinecraftClient.getInstance().getWindow().getScaledHeight();

        context.drawTexture(CALCULATOR_TEXTURE, weightScale/2-55, heightScale/2-77, 0, 0, 110, 154, 110, 154);

        super.render(context, mouseX, mouseY, delta);
    }
}