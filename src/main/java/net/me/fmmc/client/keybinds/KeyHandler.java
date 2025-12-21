package net.me.fmmc.client.keybinds;


import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.me.fmmc.client.MainClient;
import net.me.fmmc.client.rendering.particles.ModParticles;
import net.me.fmmc.component.ModDataComponents;
import net.me.fmmc.items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

import java.util.Objects;

public class KeyHandler {

    public static void register() {


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            ItemStack main = client.player.getMainHandStack();



            boolean holdingScythe = main.getItem() == ModItems.SCYTHE;
//            boolean holdingScythe = main.getItem() == ModItems.SCYTHE || off.getItem() == ModItems.SCYTHE;

            if (KeyBinds.ABILITY1.wasPressed() && holdingScythe) {  // check for input
                main.set(ModDataComponents.IS_BLOCKING, true);      // set ability to true
                main.set(ModDataComponents.COOLDOWN_BLOCKING, MainClient.MAX_BLOOCKING);      // set ability to true
                // start cooldown for ability
            }

            if (KeyBinds.ABILITY2.wasPressed() && holdingScythe) {
                main.set(ModDataComponents.IS_SLASHING, true);
                main.set(ModDataComponents.COOLDOWN_SLASHING, MainClient.MAX_SLASHING);      // set ability to true
            }

            if (KeyBinds.ULTI.wasPressed() && holdingScythe) {
                main.set(ModDataComponents.IS_ULTING, true);
                main.set(ModDataComponents.COOLDOWN_ULTING, MainClient.MAX_ULTI);      // set ability to true
            }


        });
    }
}
