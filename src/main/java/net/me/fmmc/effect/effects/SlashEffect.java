package net.me.fmmc.effect.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class SlashEffect extends StatusEffect {
    public SlashEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x1F1F1F); // color is dark gray
    }

    // Called whenever the effect would tick; we can use it for periodic effects
    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        // no periodic effect needed for damage blocking
        return false;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return false; // no automatic ticks needed
    }
}
