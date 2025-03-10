package org.minemath.forge;

import org.minemath.Minemath;
import net.minecraftforge.fml.common.Mod;

@Mod(Minemath.MOD_ID)
public final class MinemathForge {
    public MinemathForge() {
        // Run our common setup.
        Minemath.init();
    }
}
