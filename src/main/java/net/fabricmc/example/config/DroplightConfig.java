package net.fabricmc.example.config;

import com.anthonyhilyard.prism.item.ItemColors;
import com.anthonyhilyard.iceberg.util.Selectors;
import com.anthonyhilyard.prism.util.ConfigHelper;
import com.anthonyhilyard.prism.util.IColor;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.fabricmc.example.compat.Iris;
import net.fabricmc.example.compat.ItemBorders;
import net.irisshaders.iris.api.v0.IrisApi;
import net.irisshaders.iris.api.v0.IrisApiConfig;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TextColor;
import net.fabricmc.example.config.ColorDefinition;
import com.electronwill.nightconfig.core.Config;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.api.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class DroplightConfig {
    public static ForgeConfigSpec SPEC;
    public static DroplightConfig INSTANCE;
    public final BooleanValue automaticColor;
    public final BooleanValue itemFlipping;
    public final ConfigValue<List<Config>> colorDefinitionsConfigs;
    public boolean irisShadersInUse;
    private static List<ColorDefinition> defaultColorDefinitions = List.of(new ColorDefinition(List.of(), ""));
    private final Map<ItemStack, Pair<TextColor, TextColor>> colorCache;
    public final ForgeConfigSpec.BooleanValue syncWithItemBorders;

    public DroplightConfig(Builder builder) {
        super();
        this.colorCache = Maps.newHashMap();
        this.irisShadersInUse = Iris.canUseCustomShaders();
        this.syncWithItemBorders = builder.comment(" If the Item Borders mod is installed, should beam/sparkle colors match the item's border color.\n (Manual colors specified in this file will take precedence, so remove them if you want Item Borders to control all colors.)").define("syncWithItemBorders", true);
        this.automaticColor = builder.comment(" If beam/sparkle color should match the item's tooltip name color. (White named items won't have a beam unless specified manually)").define("automaticColor", true);
        this.colorDefinitionsConfigs = builder.define("manualColor", defaultColorDefinitions.stream().map(ColorDefinition::toConfig).toList(), ColorDefinition::validateList);
        this.itemFlipping = builder.comment(" If items should rotate wildly when thrown.").define("itemFlipping", true);
        ModConfigEvent.RELOADING.register(DroplightConfig::onReload);
    }

    public static void clearCaches() {
        INSTANCE.colorCache.clear();
        Iris.refreshCache();
    }

    public static void onReload(ModConfig config) {
        if (config.getModId().equals("example") || config.getModId().equals("itemborders") || config.getModId().equals("iris")) {
            clearCaches();
            DroplightConfig.INSTANCE.irisShadersInUse = Iris.canUseCustomShaders();
            System.out.println("MOD RELOADED >>>>>>");
        }
    }

    public static boolean shouldRenderBeam(ItemStack stack) {
        return ItemBorders.getColorForItem(stack) != null;
    }

    private static /* synthetic */ boolean lambda$shouldRenderBeam$1(final ItemStack stack, final String selector) {
        return Selectors.itemMatches(stack, selector);
    }

    private static /* synthetic */ Stream lambda$shouldRenderBeam$0(final ColorDefinition config) {
        return config.items().stream();
    }

    static {
        defaultColorDefinitions = List.of(new ColorDefinition(List.of(), ""));
        Config.setInsertionOrderPreserved(true);
    }

    public static Pair<TextColor, TextColor> getItemColors(ItemStack item, TextColor defaultColor) {
        TextColor itemBordersColor;
        if (DroplightConfig.INSTANCE.colorCache.containsKey(item)) {
            return DroplightConfig.INSTANCE.colorCache.get(item);
        }
        TextColor color = null;
        TextColor color2 = null;
        color = TextColor.fromRgb(item.getItemBarColor());
        if (color == null && ((Boolean)DroplightConfig.INSTANCE.syncWithItemBorders.get()).booleanValue() && (itemBordersColor = ItemBorders.getColorForItem(item)) != null) {
            color = itemBordersColor;
        }
        color = ItemColors.getColorForItem((ItemStack)item, (TextColor)defaultColor);
        if (color == null) {
            color = defaultColor;
        }
        if (color2 == null) {
            color2 = ConfigHelper.applyModifiers(List.of("-v50", "-h35", "+s35"), (TextColor)color);
        }
        DroplightConfig.INSTANCE.colorCache.put(item, Pair.of(color, color2));
        return DroplightConfig.INSTANCE.colorCache.get(item);
    }

}