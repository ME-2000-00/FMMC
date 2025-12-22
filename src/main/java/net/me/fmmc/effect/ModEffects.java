package net.me.fmmc.effect;

import net.me.fmmc.Main;
import net.me.fmmc.effect.effects.DamageBlockEffect;
import net.me.fmmc.effect.effects.SlashEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {

    public static final StatusEffect DAMAGE_BLOCK = new DamageBlockEffect();
    public static final StatusEffect SLASH_EFFECT = new SlashEffect();

    public static void register() {
        Registry.register(Registries.STATUS_EFFECT, Identifier.of(Main.MOD_ID, "damage_block"), DAMAGE_BLOCK);
        Registry.register(Registries.STATUS_EFFECT, Identifier.of(Main.MOD_ID, "slash_effect"), SLASH_EFFECT);
    }
}
