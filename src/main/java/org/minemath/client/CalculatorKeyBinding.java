package org.minemath.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class CalculatorKeyBinding {
    private static final String CATEGORY = "MineMath";
    private static KeyBinding calculatorKeyBinding;

    public static void register() {
        calculatorKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.calculator",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_N, // Default key (N)
                CATEGORY
        ));
    }
    public static KeyBinding getCalculatorKeyBinding() {
        return calculatorKeyBinding;
    }
}
