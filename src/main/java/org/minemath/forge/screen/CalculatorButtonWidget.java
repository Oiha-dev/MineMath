package org.minemath.forge.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class CalculatorButtonWidget extends Button {

    private final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath("minemath", "textures/buttons/buttons-spritesheet.png");
    private final ResourceLocation textureOnHover = ResourceLocation.fromNamespaceAndPath("minemath", "textures/buttons/buttons-spritesheet-hover.png");
    private final ResourceLocation textureOnClick = ResourceLocation.fromNamespaceAndPath("minemath", "textures/buttons/buttons-spritesheet-clicked.png");

    private final int spriteNbr;
    private boolean isClicked = false;

    public CalculatorButtonWidget(int x, int y, int id, OnPress onPress) {
        super(x, y, 16, 13, Component.empty(), onPress, Button.DEFAULT_NARRATION);
        this.spriteNbr = id;
    }

    /**
     * Render the button.
     * This method is called every frame to render the button.
     * It will change its texture depending on the state of the button
     */
    @Override
    protected void renderWidget(GuiGraphics context, int mouseX, int mouseY, float delta) {
        int midX = Minecraft.getInstance().getWindow().getGuiScaledWidth() / 2;
        int midY = Minecraft.getInstance().getWindow().getGuiScaledHeight() / 2;

        if (isClicked && !this.isHovered()) {
            isClicked = false;
        }

        if (isClicked && this.isHovered()) {
            context.blit(RenderType::guiTexturedOverlay, textureOnClick, this.getX(), this.getY(), spriteNbr * 16, 0, 16, 13, 560, 13);
        }else if (this.isHovered() && mouseX != midX && mouseY != midY) {
            context.blit(RenderType::guiTexturedOverlay, textureOnHover, this.getX(), this.getY(), spriteNbr * 16, 0, 16, 13, 560, 13);
        } else {
            context.blit(RenderType::guiTexturedOverlay, texture, this.getX(), this.getY(), spriteNbr * 16, 0, 16, 13, 560, 13);
        }
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        super.onClick(mouseX, mouseY);
        isClicked = true;
    }

    @Override
    public void onRelease(double mouseX, double mouseY) {
        super.onRelease(mouseX, mouseY);
        isClicked = false;
    }
}