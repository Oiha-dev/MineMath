package org.minemath.forge.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class HistoryScreen extends Screen {

    private final static ResourceLocation HISTORY_TEXTURE = ResourceLocation.fromNamespaceAndPath("minemath", "textures/calculator/history.png");
    public static List<String> MathList = new ArrayList<>();

    public HistoryScreen(Component title, List<String> MathList) {
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
        int weightScale = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int heightScale = Minecraft.getInstance().getWindow().getGuiScaledHeight();

        List<Button> buttons = new ArrayList<>();

        if (MathList.isEmpty()) {
            StringWidget textWidget = new StringWidget(weightScale/2-40, heightScale/2-62, 80, 20, Component.literal("No history"), this.font);
            addRenderableWidget(textWidget);
            return;
        }

        for (int i = 0; i < MathList.size(); i++) {
            int y = i % 5;
            int finalI = MathList.size() - 1 - i;
            buttons.add(Button.builder(Component.literal(MathList.get(finalI)), (button) -> {
                        CalculatorScreen calculatorScreen = new CalculatorScreen(Component.empty());
                        calculatorScreen.setResultMath(MathList.get(finalI));
                        Minecraft.getInstance().setScreen(calculatorScreen);
                    })
                    .pos(weightScale/2-40 , heightScale/2-62 + y*25)
                    .size(80, 20)
                    .build());
            addRenderableWidget(buttons.get(buttons.size() - 1));
        }
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        int weightScale = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int heightScale = Minecraft.getInstance().getWindow().getGuiScaledHeight();

        context.blit(RenderType::guiTexturedOverlay, HISTORY_TEXTURE, weightScale/2-55, heightScale/2-77, 0, 0, 110, 154, 110, 154);

        super.render(context, mouseX, mouseY, delta);
    }
}
