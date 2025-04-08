package org.minemath.neoforge.client;

import com.mojang.brigadier.Command;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import net.neoforged.neoforge.event.TickEvent;
import org.minemath.CalculatorScreen;
import org.minemath.Minemath;

@Mod.EventBusSubscriber(modid = Minemath.MOD_ID, value = Dist.CLIENT)
public class NeoForgeClientEvents {

    @SubscribeEvent
    public static void onRegisterClientCommands(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(
                net.minecraft.server.command.CommandManager.literal("calculator")
                        .executes(context -> {
                            MinecraftClient.getInstance().execute(() ->
                                    MinecraftClient.getInstance().setScreen(new CalculatorScreen(Text.of("Calculator")))
                            );
                            return Command.SINGLE_SUCCESS;
                        })
        );
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (ModClientEvents.CALCULATOR_KEY.wasPressed()) {
            MinecraftClient.getInstance().execute(() ->
                    MinecraftClient.getInstance().setScreen(new CalculatorScreen(Text.of("Calculator")))
            );
        }
    }
}