package org.minemath.forge.client;

import net.minecraft.client.KeyMapping;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import org.minemath.forge.Minemath;

@Mod.EventBusSubscriber(modid = Minemath.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModClientEvents {

    public static final KeyMapping CALCULATOR_KEY = new KeyMapping(
            "Open Calculator",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_N,
            "Minemath"
    );

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(CALCULATOR_KEY);
    }
}