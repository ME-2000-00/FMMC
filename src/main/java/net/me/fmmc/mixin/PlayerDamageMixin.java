package net.me.fmmc.mixin;

import net.me.fmmc.component.ModDataComponents;
import net.me.fmmc.items.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerDamageMixin {

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)

    // function that fires when player takes damage
    private void onPlayerDamaged(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
        // mainhand itemstack
        ItemStack main = client.player.getMainHandStack();

        // only block damage if the block key is pressed
        if (Boolean.TRUE.equals(main.get(ModDataComponents.IS_BLOCKING))) {
            cir.setReturnValue(false); // false = damage denied
            cir.cancel();
        }
    }
}