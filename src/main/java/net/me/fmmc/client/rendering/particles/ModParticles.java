package net.me.fmmc.client.rendering.particles;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.me.fmmc.Main;
import net.me.fmmc.client.rendering.particles.factories.BlockParticleFactory;
import net.me.fmmc.client.rendering.particles.factories.RuneParticleFactory;
import net.me.fmmc.client.rendering.particles.factories.SlashParticleFactory;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles implements ModInitializer, ClientModInitializer {

    public static final SimpleParticleType BLOCK_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType SLASH_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType RUNE_PARTICLE  = FabricParticleTypes.simple();

    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(BLOCK_PARTICLE, BlockParticleFactory::new);
        ParticleFactoryRegistry.getInstance().register(SLASH_PARTICLE, SlashParticleFactory::new);
        ParticleFactoryRegistry.getInstance().register(RUNE_PARTICLE,  RuneParticleFactory::new);
    }



    @Override
    public void onInitialize() {
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Main.MOD_ID, "block_particle"), BLOCK_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Main.MOD_ID, "slash_particle"), SLASH_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Main.MOD_ID, "rune_particle"),  RUNE_PARTICLE);
    }
}
