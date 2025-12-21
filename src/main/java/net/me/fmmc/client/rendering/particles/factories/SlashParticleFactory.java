package net.me.fmmc.client.rendering.particles.factories;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.me.fmmc.client.rendering.particles.types.BlockParticle;
import net.me.fmmc.client.rendering.particles.types.SlashParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class SlashParticleFactory implements ParticleFactory<SimpleParticleType> {
    private final SpriteProvider sprites;

    public SlashParticleFactory(SpriteProvider sprites) {
        this.sprites = sprites;
    }

    @Override
    public @Nullable Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {

        return new SlashParticle(world, x, y, z, 0, 0, 0, sprites);
    }
}
