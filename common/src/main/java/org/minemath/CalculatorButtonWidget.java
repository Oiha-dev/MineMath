package org.minemath;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CalculatorButtonWidget extends ButtonWidget {

    private final Identifier texture = Identifier.of("minemath", "textures/buttons/buttons-spritesheet.png");
    private final Identifier textureOnHover = Identifier.of("minemath", "textures/buttons/buttons-spritesheet-hover.png");
    private final Identifier textureOnClick = Identifier.of("minemath", "textures/buttons/buttons-spritesheet-clicked.png");

    private final int spriteNbr;
    private boolean isClicked = false;

    public CalculatorButtonWidget(int x, int y, int id, PressAction onPress) {
        super(x, y, 16, 13, Text.empty(), onPress, ButtonWidget.DEFAULT_NARRATION_SUPPLIER);
        this.spriteNbr = id;
    }

    /**
     * Render the button.
     * This method is called every frame to render the button.
     * It will change its texture depending on the state of the button
     */
    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        int midX = MinecraftClient.getInstance().getWindow().getScaledWidth() / 2;
        int midY = MinecraftClient.getInstance().getWindow().getScaledHeight() / 2;

        if (isClicked && !this.isHovered()) {
            isClicked = false;
        }

        if (isClicked && this.isHovered()) {
            context.drawTexture(textureOnClick, this.getX(), this.getY(), spriteNbr * 16, 0, 16, 13, 560, 13);
        }else if (this.isHovered() && mouseX != midX && mouseY != midY) {
            context.drawTexture(textureOnHover, this.getX(), this.getY(), spriteNbr * 16, 0, 16, 13, 560, 13);
        } else {
            context.drawTexture(texture, this.getX(), this.getY(), spriteNbr * 16, 0, 16, 13, 560, 13);
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