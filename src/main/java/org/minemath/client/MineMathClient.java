package org.minemath.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import org.minemath.client.gui.CalculatorMathGUI;
import org.minemath.client.gui.CalculatorMathScreen;

import static net.fabricmc.api.EnvType.CLIENT;

public class MineMathClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        CalculatorKeyBinding.register();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (CalculatorKeyBinding.getCalculatorKeyBinding().isPressed()) {
                MinecraftClient.getInstance().setScreen(new CalculatorMathScreen(new CalculatorMathGUI()));
            }
        });
    }
}