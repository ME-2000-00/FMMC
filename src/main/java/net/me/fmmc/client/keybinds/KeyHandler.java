package net.me.fmmc.client.keybinds;


import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.me.fmmc.Main;
import net.me.fmmc.component.ModDataComponents;
import net.me.fmmc.items.ModItems;
import net.me.fmmc.payload.payloads.BlockingPayload;
import net.me.fmmc.payload.payloads.SlashingPayload;
import net.me.fmmc.payload.payloads.UltingPayload;
import net.minecraft.item.ItemStack;

public class KeyHandler {

    public static void register() {


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            ItemStack main = client.player.getMainHandStack();



            boolean holdingScythe = main.getItem() == ModItems.SCYTHE;
//            boolean holdingScythe = main.getItem() == ModItems.SCYTHE || off.getItem() == ModItems.SCYTHE;

            if (KeyBinds.ABILITY1.wasPressed() && holdingScythe && main.get(ModDataComponents.COOLDOWN_BLOCKING) < 1) {  // check for input
                ClientPlayNetworking.send(new BlockingPayload(true, Main.BLOCKING_COOLD)); // 60 seconds cooldown
            }

            if (KeyBinds.ABILITY2.wasPressed() && holdingScythe && main.get(ModDataComponents.COOLDOWN_SLASHING) < 1) {
                ClientPlayNetworking.send(new SlashingPayload(true, Main.SLASHING_COOLD)); // 45 seconds cooldown
            }

            if (KeyBinds.ULTI.wasPressed() && holdingScythe && main.get(ModDataComponents.ULT_KILLS) >= Main.MAX_ULT_KILLS) {
                ClientPlayNetworking.send(new UltingPayload(true));
            }

        });
    }
}
