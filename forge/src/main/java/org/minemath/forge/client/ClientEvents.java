package org.minemath.forge.client;

import com.mojang.brigadier.Command;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.minemath.CalculatorScreen;
import org.minemath.Minemath;

import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static net.minecraft.command.argument.EntityArgumentType.getPlayer;
import static net.minecraft.command.argument.EntityArgumentType.player;

@Mod.EventBusSubscriber(modid = Minemath.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onRegisterClientCommands(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(
                net.minecraft.server.command.CommandManager.literal("calculator")
                        .executes(context -> {
                            // This code runs on the game thread when the command is executed
                            MinecraftClient.getInstance().execute(() -> {
                                MinecraftClient.getInstance().setScreen(new CalculatorScreen(Text.of("Calculator")));
                            });
                            return Command.SINGLE_SUCCESS;
                        })
        );
    }
}