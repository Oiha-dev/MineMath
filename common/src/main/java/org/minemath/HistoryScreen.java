package org.minemath;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class HistoryScreen extends Screen {

    private final static Identifier HISTORY_TEXTURE = new Identifier("minemath", "textures/calculator/history.png");
    private List<String> MathList;

    public HistoryScreen(Text title, List<String> MathList) {
        super(title);
        this.MathList = MathList;
    }

    @Override
    public void init() {
        int weightScale = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int heightScale = MinecraftClient.getInstance().getWindow().getScaledHeight();

        List<ButtonWidget> buttons = new ArrayList<>();

        if (MathList.isEmpty()) {
            TextWidget textWidget = new TextWidget(weightScale/2-40, heightScale/2-62, 80, 20, Text.of("No history"), this.textRenderer);
            addDrawableChild(textWidget);
            return;
        }

        for (int i = 0; i < MathList.size(); i++) {
            int y = i % 5;
            int finalI = i;
            buttons.add(ButtonWidget.builder(Text.of(MathList.get(MathList.size()-i-1)), (button) -> {
                        CalculatorScreen calculatorScreen = new CalculatorScreen(Text.empty());
                        calculatorScreen.setResultMath(MathList.get(finalI));
                        MinecraftClient.getInstance().setScreen(calculatorScreen);
                    })
                    .position(weightScale/2-40 , heightScale/2-62 + y*25)
                    .size(80, 20)
                    .build());
            addDrawableChild(buttons.get(buttons.size() - 1));
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int weightScale = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int heightScale = MinecraftClient.getInstance().getWindow().getScaledHeight();

        context.drawTexture(HISTORY_TEXTURE, weightScale/2-55, heightScale/2-77, 0, 0, 110, 154, 110, 154);

        super.render(context, mouseX, mouseY, delta);
    }
}
