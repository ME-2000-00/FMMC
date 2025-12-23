package net.me.fmmc;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.me.fmmc.component.ModDataComponents;
import net.me.fmmc.effect.ModEffects;
import net.me.fmmc.items.ModItems;
import net.me.fmmc.payload.ModPayloads;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
    public static final String MOD_ID = "fmmc";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static int BLOCKING_COOLD = 60 * 20;
    public static int SLASHING_COOLD = 45 * 20;

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
            if (kills >= 3) return;

            main.set(ModDataComponents.ULT_KILLS, kills + 1);

            // FORCE inventory + component sync
            player.getInventory().markDirty();
            player.currentScreenHandler.sendContentUpdates();
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
