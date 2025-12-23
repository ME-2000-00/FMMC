package net.me.fmmc;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.me.fmmc.client.rendering.particles.ModParticles;
import net.me.fmmc.component.ModDataComponents;
import net.me.fmmc.effect.ModEffects;
import net.me.fmmc.items.ModItems;
import net.me.fmmc.payload.ModPayloads;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
    public static final String MOD_ID = "fmmc";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static int BLOCKING_COOLD = 60 * 20;
    public static int SLASHING_COOLD = 45 * 20;
    public static int MAX_ULT_KILLS = 3;

//    public static TimerHandeler timerHandeler = new TimerHandeler();

    @Override
    public void onInitialize() {
        ModItems.register();
        ModDataComponents.register();
        ModPayloads.register();
        ModNetworking.register();
        ModEffects.register();
        ModCommands.register();


        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register((world, killer, entity) -> {

            Entity attacker = killer;


            // unwrap projectile owners
            if (attacker instanceof ProjectileEntity projectile) {
                if (projectile.getOwner() != null) {
                    attacker = projectile.getOwner();
                }
            }

            if (!(attacker instanceof ServerPlayerEntity player)) return;

            ItemStack main = player.getMainHandStack();
            if (!main.isOf(ModItems.SCYTHE)) return;

            int kills = main.getOrDefault(ModDataComponents.ULT_KILLS, 0);
            if (kills >= MAX_ULT_KILLS) return;

            main.set(ModDataComponents.ULT_KILLS, kills + 1);

            // FORCE inventory + component sync
            player.getInventory().markDirty();
            player.currentScreenHandler.sendContentUpdates();
        });



        ServerTickEvents.END_WORLD_TICK.register(serverWorld -> {
            serverWorld.getPlayers().forEach(player -> {
                if (player.hasStatusEffect(Registries.STATUS_EFFECT.getEntry(ModEffects.SLASH_EFFECT))) {
                    // player has slashing effect


                    serverWorld.spawnParticles(
                            ModParticles.SLASH_PARTICLE,
                            player.getX() + (Math.random() * 5) - 2.5,
                            player.getY() + (Math.random() * 2 ) + 1,
                            player.getZ() + (Math.random() * 5) - 2.5,
                            1,
                            0.5,
                            0.5,
                            0.5,
                            0.3
                    );


                    Util.damage_AOE_plr(5, player.getBlockPos(), player, 1.5f);
                }

                if (player.getMainHandStack().getOrDefault(ModDataComponents.ULT_KILLS, 0) == MAX_ULT_KILLS && player.getMainHandStack().getItem() == ModItems.SCYTHE) {
                    serverWorld.spawnParticles(
                            ModParticles.RUNE_PARTICLE,
                            player.getX() + (Math.random()) - 0.5,
                            player.getY() + (Math.random()) - 0.5,
                            player.getZ() + (Math.random()) - 0.5,
                            1,
                            0.5,
                            0.5,
                            0.5,
                            0.3
                    );
                }

                if (player.hasStatusEffect(Registries.STATUS_EFFECT.getEntry(ModEffects.DAMAGE_BLOCK))) {
                    serverWorld.spawnParticles(
                            ModParticles.BLOCK_PARTICLE,
                            player.getX() + 0.0,
                            player.getY() + 1.0,
                            player.getZ() + 0.0,
                            1,
                            0.5,
                            0.5,
                            0.5,
                            0.0);
                }
            });
        });



        ServerTickEvents.END_WORLD_TICK.register(serverWorld -> {
            // update client cooldowns every tick
            serverWorld.getPlayers().forEach(player -> {
                ItemStack main = player.getMainHandStack();

                if(Boolean.TRUE.equals(main.get(ModDataComponents.IS_BLOCKING))) {
                    int currentCooldown = main.getOrDefault(ModDataComponents.COOLDOWN_BLOCKING, 0);
                    if (currentCooldown > 0) {
                        currentCooldown--;
                        main.set(ModDataComponents.COOLDOWN_BLOCKING, currentCooldown - 1);
                    }

                    if (currentCooldown < 1) {
                        main.set(ModDataComponents.IS_BLOCKING, false);
                    }
                }

                if(Boolean.TRUE.equals(main.get(ModDataComponents.IS_SLASHING))) {
                    int currentCooldown = main.getOrDefault(ModDataComponents.COOLDOWN_SLASHING, 0);
                    if (currentCooldown > 0) {
                        currentCooldown--;
                        main.set(ModDataComponents.COOLDOWN_SLASHING, currentCooldown - 1);
                    }

                    if (currentCooldown < 1) {
                        main.set(ModDataComponents.IS_SLASHING, false);
                    }
                }

                if (Boolean.TRUE.equals(main.get(ModDataComponents.IS_ULTING))) {
                    // ulting logic if needed
                    main.set(ModDataComponents.IS_ULTING, false);
                    main.set(ModDataComponents.ULT_KILLS, 0);
                }
            });
        });
    }
}
