package org.minemath.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.minemath.CalculatorScreen;

public final class MinemathFabricClient implements ClientModInitializer {
    private static KeyBinding keyBinding;

    @Override
    public void onInitializeClient() {
        System.out.println("Minemath Fabric Client");
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("calculator").executes(context -> {
                MinecraftClient.getInstance().execute(() -> {
                    MinecraftClient.getInstance().setScreen(
                            new CalculatorScreen(Text.of("Calculator"))
                    );
                });
                return 1;
            }));
        });

        // Register the calculator keybind
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Open Calculator",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_N,
                "MineMath"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                MinecraftClient.getInstance().execute(() -> {
                    MinecraftClient.getInstance().setScreen(
                            new CalculatorScreen(Text.of("Calculator"))
                    );
                });
            }
        });

    }
}
