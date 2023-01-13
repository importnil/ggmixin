// Decompiled with: FernFlower
// Class Version: 17
package net.fabricmc.example;

import com.google.common.collect.Maps;
//import dev.banzetta.droplight.DroplightClient;
import net.fabricmc.example.compat.Iris;
import net.fabricmc.example.config.DroplightConfig;
import net.fabricmc.example.registry.ParticleRegistry;
import net.fabricmc.example.shader.BeamShaders;
import java.util.Map;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraftforge.common.ForgeConfigSpec;

public class ExampleMod implements ClientModInitializer, SimpleSynchronousResourceReloadListener {
	public static final Map<ItemEntity, MatrixStack> VISIBLE_ITEMS = Maps.newHashMap();

	public void onInitializeClient() {
		ClientTickEvents.START_CLIENT_TICK.register(ExampleMod::onTick);
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(this);

		final org.apache.commons.lang3.tuple.Pair<DroplightConfig, ForgeConfigSpec> specPair = (new ForgeConfigSpec.Builder()).configure(DroplightConfig::new);
		DroplightConfig.SPEC = (ForgeConfigSpec)specPair.getRight();
		DroplightConfig.INSTANCE = (DroplightConfig)specPair.getLeft();

		System.out.println("loaded modz");
//		ParticleFactoryRegistry.getInstance().register(ParticleRegistry.SPARKLE_PARTICLE, SparkleParticle.Provider::new);
	}

	public static void onTick(MinecraftClient server) {
		VISIBLE_ITEMS.clear();

		DroplightConfig.INSTANCE.irisShadersInUse = Iris.canUseCustomShaders();
	}

	public Identifier getFabricId() {
		return new Identifier("droplight", "shaders");
	}

	public void reload(ResourceManager resourceManager) {
		System.out.println("GGGWP");
		BeamShaders.init(resourceManager);
		DroplightConfig.clearCaches();
	}
}