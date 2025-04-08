package org.minemath.neoforge;

import net.neoforged.fml.common.Mod;

import org.minemath.Minemath;

@Mod(Minemath.MOD_ID)
public final class MinemathNeoForge {
    public MinemathNeoForge() {
        // Run our common setup.
        Minemath.init();
    }
}
