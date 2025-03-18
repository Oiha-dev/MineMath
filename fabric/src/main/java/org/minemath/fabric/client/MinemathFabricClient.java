package org.minemath.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import org.minemath.CalculatorScreen;

public final class MinemathFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("Minemath Fabric Client");
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("test_command").executes(context -> {
                context.getSource().sendFeedback(() -> Text.literal("Called /test_command."), false);

                MinecraftClient.getInstance().execute(() -> {
                    MinecraftClient.getInstance().setScreen(
                            new CalculatorScreen(Text.of("Calculator"))
                    );
                });
                return 1;
            }));
        });

    }
}
