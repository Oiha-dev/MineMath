package org.minemath;

import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class HistoryScreen extends Screen {

    private final static Identifier HISTORY_TEXTURE = new Identifier("minemath", "textures/calculator/history.png");
    public static List<String> MathList = new ArrayList<>();
    private final List<Drawable> drawables = Lists.newArrayList();


    public HistoryScreen(Text title, List<String> MathList) {
        super(title);
        this.MathList = MathList;
    }

    /**
     * Initialize the history screen.
     * This method is called when the screen is first opened.
     * It initializes the history buttons.
     * The history buttons are used to display the history of previous calculations.
     * The user can click on a history button to open the calculator screen with the corresponding expression.
     * If the history is empty, a message is displayed instead.
     */
    @Override
    public void init() {
        int weightScale = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int heightScale = MinecraftClient.getInstance().getWindow().getScaledHeight();

        List<ButtonWidget> buttons = new ArrayList<>();

        if (MathList.isEmpty()) {
            TextWidget textWidget = new TextWidget(weightScale/2-40, heightScale/2-62, 80, 20, Text.of("No history"), this.textRenderer);
            addSelectableChild(textWidget);
            this.drawables.add(textWidget);
            return;
        }

        for (int i = 0; i < MathList.size(); i++) {
            int y = i % 5;
            int finalI = MathList.size() - 1 - i;
            buttons.add(ButtonWidget.builder(Text.of(MathList.get(finalI)), (button) -> {
                        CalculatorScreen calculatorScreen = new CalculatorScreen(Text.empty());
                        calculatorScreen.setResultMath(MathList.get(finalI));
                        MinecraftClient.getInstance().setScreen(calculatorScreen);
                    })
                    .position(weightScale/2-40 , heightScale/2-62 + y*25)
                    .size(80, 20)
                    .build());
            addSelectableChild(buttons.get(buttons.size() - 1));
            this.drawables.add(buttons.get(buttons.size() - 1));
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int weightScale = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int heightScale = MinecraftClient.getInstance().getWindow().getScaledHeight();

        context.drawTexture(HISTORY_TEXTURE, weightScale/2-55, heightScale/2-77, 0, 0, 110, 154, 110, 154);

        for (Drawable drawable : drawables) {
            drawable.render(context, mouseX, mouseY, delta);
        }
    }
}
