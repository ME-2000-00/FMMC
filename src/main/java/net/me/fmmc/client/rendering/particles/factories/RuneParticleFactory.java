package net.me.fmmc.client.rendering.particles.factories;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.me.fmmc.client.rendering.particles.types.RuneParticle;
import net.me.fmmc.client.rendering.particles.types.SlashParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class RuneParticleFactory implements ParticleFactory<SimpleParticleType> {
    private final SpriteProvider sprites;

    public RuneParticleFactory(SpriteProvider sprites) {
        this.sprites = sprites;
    }

    @Override
    public @Nullable Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {

        return new RuneParticle(world, x, y, z, 0, 0, 0, sprites);
    }
}
