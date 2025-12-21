package net.me.fmmc.client.rendering.particles.types;

import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.world.ClientWorld;

public class BlockParticle extends SpriteBillboardParticle {

    // Constructor world, x, y, z, velocityX, velocityY, velocityZ
    public BlockParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
        super(clientWorld, d, e, f, g, h, i);

        this.velocityMultiplier = 0.8f;
        this.gravityStrength = 0.0f;

        this.maxAge = 20 + this.random.nextInt(10);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();


        this.alpha = ( 1.0f - ((float)this.age / (float)this.maxAge) ) + 0.2f;
        this.scale = 0.2f + ((float)this.age / (float)this.maxAge);
    }
}
