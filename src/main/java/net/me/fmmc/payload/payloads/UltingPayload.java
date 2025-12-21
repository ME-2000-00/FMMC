package net.me.fmmc.payload.payloads;

import io.netty.buffer.ByteBuf;
import net.me.fmmc.Main;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record UltingPayload(boolean ulting) implements CustomPayload {

    public static final Id<UltingPayload> ID =
            new Id<>(Identifier.of(Main.MOD_ID, "ulting"));

    public static final PacketCodec<ByteBuf, UltingPayload> CODEC =
            PacketCodecs.BOOL.xmap(
                    UltingPayload::new,
                    UltingPayload::ulting
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}