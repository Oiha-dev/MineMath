package org.minemath.forge.client;

import com.mojang.brigadier.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.commands.Commands;
import org.minemath.forge.Minemath;
import org.minemath.forge.screen.CalculatorScreen;

@Mod.EventBusSubscriber(modid = Minemath.MODID, value = Dist.CLIENT)
public class ForgeClientEvents {

    @SubscribeEvent
    public static void onRegisterClientCommands(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("calculator")
                        .executes(context -> {
                            Minecraft.getInstance().execute(() -> {
                                Minecraft.getInstance().setScreen(new CalculatorScreen(Component.literal("Calculator")));
                            });
                            return Command.SINGLE_SUCCESS;
                        })
        );
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END &&
                ModClientEvents.CALCULATOR_KEY.consumeClick()) {
            Minecraft.getInstance().execute(() -> {
                Minecraft.getInstance().setScreen(new CalculatorScreen(Component.literal("Calculator")));
            });
        }
    }
}