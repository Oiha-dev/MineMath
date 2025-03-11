package org.minemath;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CalculatorButtonWidget extends ButtonWidget {

    private final Identifier texture = new Identifier("minemath", "textures/buttons/buttons-spritesheet.png");
    private final int spriteNbr;

    public CalculatorButtonWidget(int x, int y, int id) {
        super(x, y, 16, 13, Text.empty(), button -> {
            System.out.println(id);
        }, ButtonWidget.DEFAULT_NARRATION_SUPPLIER);
        this.spriteNbr = id;
    }

    @Override
    protected void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawTexture(texture, this.getX(), this.getY(), spriteNbr * 16, 0, 16, 13, 560, 13);
    }
}