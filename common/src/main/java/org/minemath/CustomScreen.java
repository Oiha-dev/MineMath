package org.minemath;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.client.util.Icons;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.awt.*;


public class CustomScreen extends Screen {

    protected TextFieldWidget resultWidget;
    final static Identifier calculatorTexture = new Identifier("minemath", "textures/calculator/calculator.png");
    public CustomScreen(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        int weightScale = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int heightScale = MinecraftClient.getInstance().getWindow().getScaledHeight();

        this.resultWidget = new TextFieldWidget(this.textRenderer, weightScale/2-42, heightScale/2-59, 77, 20, Text.of("grdg"));
        this.resultWidget.setDrawsBackground(false);
        addDrawableChild(this.resultWidget);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int weightScale = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int heightScale = MinecraftClient.getInstance().getWindow().getScaledHeight();

        context.drawTexture(calculatorTexture, weightScale/2-55, heightScale/2-77, 0, 0, 110, 154, 110, 154);

        super.render(context, mouseX, mouseY, delta);
    }
}