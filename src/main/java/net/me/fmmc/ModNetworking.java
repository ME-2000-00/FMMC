package net.me.fmmc;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.me.fmmc.client.rendering.particles.ModParticles;
import net.me.fmmc.component.ModDataComponents;
import net.me.fmmc.effect.ModEffects;
import net.me.fmmc.payload.payloads.BlockingPayload;
import net.me.fmmc.payload.payloads.SlashingPayload;
import net.me.fmmc.payload.payloads.UltingPayload;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;

import java.util.List;


public class ModNetworking {

    public static void register() {
        // blocking payload receiver
        ServerPlayNetworking.registerGlobalReceiver(BlockingPayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                // Get the player who sent the packet
                ServerPlayerEntity player = context.player();
                ServerWorld world = context.server().getWorld(player.getWorld().getRegistryKey());
                // Update the main-hand ItemStack component
                player.getMainHandStack().set(ModDataComponents.IS_BLOCKING, payload.blocking());
                player.getMainHandStack().set(ModDataComponents.COOLDOWN_BLOCKING, payload.cooldown());


                player.addStatusEffect(
                        new StatusEffectInstance(
                                Registries.STATUS_EFFECT.getEntry(ModEffects.DAMAGE_BLOCK),
                                20,     // duration in ticks
                                0,      // amplifier
                                false,  // ambient
                                false,  // particles
                                false   // show icon
                        )
                );


                world.spawnParticles(ModParticles.BLOCK_PARTICLE, player.getX(), player.getY() + 1.0, player.getZ(), 5, 0.5, 0.5, 0.5, 0.0);
            });
        });

        // slashing payload receiver
        ServerPlayNetworking.registerGlobalReceiver(SlashingPayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                // Get the player who sent the packet
                ServerPlayerEntity player = context.player();
                // Update the main-hand ItemStack component
                player.getMainHandStack().set(ModDataComponents.IS_SLASHING, payload.slashing());
                player.getMainHandStack().set(ModDataComponents.COOLDOWN_SLASHING, payload.cooldown());
                // make slashing logic
                ServerWorld world = context.server().getWorld(player.getWorld().getRegistryKey());

                // do slashes over 3 seconds : 60 ticks

                player.addStatusEffect(
                        new StatusEffectInstance(
                                Registries.STATUS_EFFECT.getEntry(ModEffects.SLASH_EFFECT),
                                20 * 3,     // duration in ticks
                                0,      // amplifier
                                false,  // ambient
                                false,  // particles
                                false   // show icon
                        )
                );
            });
        });

        // ulting payload receiver
        ServerPlayNetworking.registerGlobalReceiver(UltingPayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                // Get the player who sent the packet
                ServerPlayerEntity player = context.player();
                // Update the main-hand ItemStack component
                player.getMainHandStack().set(ModDataComponents.IS_ULTING, payload.ulting());
                // ult logic here



                // reset ult boolean
//                player.getMainHandStack().set(ModDataComponents.IS_ULTING, false);
            });
        });
    }
}
