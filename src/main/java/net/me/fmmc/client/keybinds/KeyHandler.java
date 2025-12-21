package net.me.fmmc.client.keybinds;


import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.me.fmmc.items.ModItems;
import net.me.fmmc.payload.payloads.BlockingPayload;
import net.minecraft.item.ItemStack;

public class KeyHandler {

    public static void register() {


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            ItemStack main = client.player.getMainHandStack();



            boolean holdingScythe = main.getItem() == ModItems.SCYTHE;
//            boolean holdingScythe = main.getItem() == ModItems.SCYTHE || off.getItem() == ModItems.SCYTHE;

            if (KeyBinds.ABILITY1.wasPressed() && holdingScythe) {  // check for input
                // send packet to server
                ClientPlayNetworking.send(new BlockingPayload(true));
                // let server update data component
                // server will handle ability logic
            }

            if (KeyBinds.ABILITY2.wasPressed() && holdingScythe) {
                ClientPlayNetworking.send(new BlockingPayload(true));
            }

            if (KeyBinds.ULTI.wasPressed() && holdingScythe) {
                ClientPlayNetworking.send(new BlockingPayload(true));
            }


        });
    }
}
