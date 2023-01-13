package net.fabricmc.example.compat;


import net.fabricmc.example.ExampleMod;
import java.lang.reflect.Method;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TextColor;

public class ItemBorders {
    public static TextColor getColorForItem(ItemStack item) {
        TextColor result = null;
        if (FabricLoader.getInstance().isModLoaded("itemborders")) {
            try {
                Method getBorderColorForItem = Class.forName("net.fabricmc.example.compat.itemborders.ApiAccess").getMethod("getBorderColorForItem", ItemStack.class);
                result = (TextColor)getBorderColorForItem.invoke(null, item);
            }
            catch (Exception e) {
//                ExampleMod.LOGGER.error(e);
            }
        }
        return result;
    }
}

