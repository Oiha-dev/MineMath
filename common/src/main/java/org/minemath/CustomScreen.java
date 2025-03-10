package org.minemath;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;



public class CustomScreen extends Screen {

    final static Identifier calculatorTexture = new Identifier("minemath", "textures/calculator/calculator.png");

    public CustomScreen(Text title) {
        super(title);
    }

    @Override
    protected void init() {

    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        context.drawTexture(calculatorTexture, 0, 0, 0, 0, 110, 154, 110, 154);
        context.drawText(this.textRenderer, "Special Button", 40, 40 - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);
    }
}