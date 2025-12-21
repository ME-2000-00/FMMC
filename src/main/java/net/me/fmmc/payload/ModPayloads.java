package net.me.fmmc.payload;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.me.fmmc.payload.payloads.BlockingPayload;
import net.me.fmmc.payload.payloads.SlashingPayload;
import net.me.fmmc.payload.payloads.UltingPayload;

public class ModPayloads {

    public static void register() {
        // registering payloads
        PayloadTypeRegistry.playC2S().register(BlockingPayload.ID, BlockingPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(SlashingPayload.ID, SlashingPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(UltingPayload.ID  ,   UltingPayload.CODEC);
    }
}
