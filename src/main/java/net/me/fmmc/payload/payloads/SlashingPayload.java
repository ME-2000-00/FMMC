package net.me.fmmc.payload.payloads;

import io.netty.buffer.ByteBuf;
import net.me.fmmc.Main;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record SlashingPayload(boolean slashing, int cooldown) implements CustomPayload {

    public static final Id<SlashingPayload> ID =
            new CustomPayload.Id<>(Identifier.of(Main.MOD_ID, "slashing"));

    public static final PacketCodec<ByteBuf, SlashingPayload> CODEC =
            PacketCodec.of(
                    (value, buf) -> {
                        buf.writeBoolean(value.slashing());
                        buf.writeInt(value.cooldown());
                    },
                    buf -> new SlashingPayload(buf.readBoolean(), buf.readInt())
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}