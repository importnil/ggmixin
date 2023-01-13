package net.fabricmc.example.registry;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ParticleRegistry {
    public static final DefaultParticleType SPARKLE_PARTICLE = (DefaultParticleType)Registry.register(Registry.PARTICLE_TYPE, new Identifier("droplight", "sparkle"), FabricParticleTypes.simple());
}