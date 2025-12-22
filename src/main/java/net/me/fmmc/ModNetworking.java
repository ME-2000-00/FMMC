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
                for (int i = 0; i < 25; i++) {
                    world.spawnParticles(
                            ModParticles.SLASH_PARTICLE,
                            player.getX() + (Math.random() * 10) - 5,
                            player.getY() + (Math.random() * 10) - 5,
                            player.getZ() + (Math.random() * 10) - 5,
                            1,
                            0.5,
                            0.5,
                            0.5,
                            0.3
                    );
                }

                int maxDistanceFromPlayer = 10;
                Box damage_box = new Box(player.getBlockPos()).expand(maxDistanceFromPlayer);
                List entityList = player.getWorld().getEntitiesByClass(MobEntity.class, damage_box, e -> e.isAlive());
                for (Object entity : entityList) {
                    if (entity instanceof MobEntity){
                        ((MobEntity) entity).damage(player.getDamageSources().generic(), ((MobEntity) entity).getMaxHealth() * 0.6f);
                    }
                }

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
