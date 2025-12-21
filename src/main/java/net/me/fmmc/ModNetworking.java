package net.me.fmmc;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.me.fmmc.component.ModDataComponents;
import net.me.fmmc.payload.payloads.BlockingPayload;
import net.me.fmmc.payload.payloads.SlashingPayload;
import net.me.fmmc.payload.payloads.UltingPayload;
import net.minecraft.server.network.ServerPlayerEntity;

public class ModNetworking {

    public static void register() {
        // blocking payload receiver
        ServerPlayNetworking.registerGlobalReceiver(BlockingPayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                // Get the player who sent the packet
                ServerPlayerEntity player = context.player();

                // Update the main-hand ItemStack component
                player.getMainHandStack().set(ModDataComponents.IS_BLOCKING, payload.blocking());

                // Optional: debug
                System.out.println(player.getName().getString() + " blocking = " + payload.blocking());
            });
        });

        // slashing payload receiver
        ServerPlayNetworking.registerGlobalReceiver(SlashingPayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                // Get the player who sent the packet
                ServerPlayerEntity player = context.player();

                // Update the main-hand ItemStack component
                player.getMainHandStack().set(ModDataComponents.IS_SLASHING, payload.slashing());

                // Optional: debug
                System.out.println(player.getName().getString() + " slashing = " + payload.slashing());
            });
        });

        // ulting payload receiver
        ServerPlayNetworking.registerGlobalReceiver(UltingPayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                // Get the player who sent the packet
                ServerPlayerEntity player = context.player();

                // Update the main-hand ItemStack component
                player.getMainHandStack().set(ModDataComponents.IS_ULTING, payload.ulting());

                // Optional: debug
                System.out.println(player.getName().getString() + " blocking = " + payload.ulting());
            });
        });
    }
}
