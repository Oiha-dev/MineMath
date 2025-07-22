package org.minemath.neoforge.client;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;
import org.minemath.Minemath;

@EventBusSubscriber(modid = Minemath.MOD_ID, value = Dist.CLIENT)
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