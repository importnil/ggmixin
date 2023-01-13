package net.fabricmc.example.compat;

import net.fabricmc.example.ExampleMod;
import java.lang.reflect.Method;
import net.fabricmc.loader.api.FabricLoader;
import net.irisshaders.iris.api.v0.IrisApi;

public class Iris {
    private static Boolean cachedState = null;

    public static boolean canUseCustomShaders() {
        if (cachedState == null && (cachedState = isShaderPackInUse())) {
            System.out.println("IRISDETECTED");
//            ExampleMod.LOGGER.warn("Iris detected, high-quality shaders will be unavailable for Droplight while shader packs are in use!");
        }
        return cachedState;
    }

    public static void refreshCache() {
        cachedState = null;
    }

    private static boolean isShaderPackInUse() {
        if (FabricLoader.getInstance().isModLoaded("iris")) {
            try {
                return IrisApi.getInstance().isShaderPackInUse();
            }
            catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
