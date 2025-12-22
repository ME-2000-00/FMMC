package net.me.fmmc.mixin;

import net.me.fmmc.component.ModDataComponents;
import net.me.fmmc.effect.ModEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerDamageMixin {

    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)

    // function that fires when player takes damage
    private void onPlayerDamaged(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        RegistryEntry<StatusEffect> damageBlockEntry = Registries.STATUS_EFFECT.getEntry(ModEffects.DAMAGE_BLOCK);


        // only block damage if the block key is pressed
        if (player.hasStatusEffect(damageBlockEntry)) {
            cir.setReturnValue(false); // false = damage denied
            cir.cancel();
        }
    }
}