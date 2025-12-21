package net.me.fmmc.client.rendering.particles.types;

import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;

public class SlashParticle extends SpriteBillboardParticle {


    private final SpriteProvider sprites;


    // Constructor world, x, y, z, velocityX, velocityY, velocityZ
    public SlashParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, SpriteProvider sprite) {
        super(clientWorld, d, e, f, g, h, i);
        this.sprites = sprite;

        this.scale = 3.0f;
        this.velocityMultiplier = 0.0f;
        this.gravityStrength = 0.0f;

        this.maxAge = 10 + this.random.nextInt(10);
        this.setSpriteForAge(this.sprites);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();

        this.velocityMultiplier = 0.0f;
        this.gravityStrength = 0.0f;

        this.setSpriteForAge(this.sprites);
    }
}
