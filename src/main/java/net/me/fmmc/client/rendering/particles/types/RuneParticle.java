package net.me.fmmc.client.rendering.particles.types;

import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;

public class RuneParticle extends SpriteBillboardParticle {


    private final SpriteProvider sprites;


    // Constructor world, x, y, z, velocityX, velocityY, velocityZ
    public RuneParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, SpriteProvider sprite) {
        super(clientWorld, d, e, f, g, h, i);
        this.sprites = sprite;

        this.scale = 0.2f;
        this.velocityMultiplier = 0.9f;
        this.alpha = 0.8f;

        this.maxAge = 25 + this.random.nextInt(5);
        this.setSprite(this.sprites.getSprite(clientWorld.random));
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
    }
}
