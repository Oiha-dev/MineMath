package org.minemath.forge.client;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
import org.minemath.Minemath;

@Mod.EventBusSubscriber(modid = Minemath.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModClientEvents {

    public static final KeyBinding CALCULATOR_KEY = new KeyBinding(
            "Open Calculator",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_N,
            "Minemath"
    );

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(CALCULATOR_KEY);
    }
}