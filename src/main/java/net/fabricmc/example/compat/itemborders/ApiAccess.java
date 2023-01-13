package net.fabricmc.example.compat.itemborders;

import com.anthonyhilyard.itemborders.ItemBordersConfig;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;

public class ApiAccess {
    public static TextColor getBorderColorForItem(ItemStack item) {
        TextColor result = ItemBordersConfig.INSTANCE.getBorderColorForItem(item);
        if (!((Boolean)ItemBordersConfig.INSTANCE.showForCommon.get()).booleanValue() && result == TextColor.fromFormatting((Formatting)Formatting.WHITE)) {
            result = null;
        }
        return result;
    }
}
