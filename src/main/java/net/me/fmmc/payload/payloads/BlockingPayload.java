package net.me.fmmc.payload.payloads;

import io.netty.buffer.ByteBuf;
import net.me.fmmc.Main;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record BlockingPayload(boolean blocking) implements CustomPayload {

    public static final Id<BlockingPayload> ID =
            new CustomPayload.Id<>(Identifier.of(Main.MOD_ID, "blocking"));

    public static final PacketCodec<ByteBuf, BlockingPayload> CODEC =
            PacketCodecs.BOOL.xmap(
                    BlockingPayload::new,
                    BlockingPayload::blocking
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}