package net.me.fmmc.component;

import com.mojang.serialization.Codec;
import net.me.fmmc.Main;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponents {

    // booleans
    public static final ComponentType<Boolean> IS_BLOCKING = regsiterComponent("is_blocking", b -> b.codec(Codec.BOOL));
    public static final ComponentType<Boolean> IS_SLASHING = regsiterComponent("is_slashing", b -> b.codec(Codec.BOOL));
    public static final ComponentType<Boolean> IS_ULTING = regsiterComponent("is_ulting", b -> b.codec(Codec.BOOL));

    // integers
    public static final ComponentType<Integer> COOLDOWN_BLOCKING = regsiterComponent("cooldown_blocking", b -> b.codec(Codec.INT));
    public static final ComponentType<Integer> COOLDOWN_SLASHING = regsiterComponent("cooldown_slashing", b -> b.codec(Codec.INT));
    public static final ComponentType<Integer> ULT_KILLS = regsiterComponent("ultimate_kills", b -> b.codec(Codec.INT));

    private static <T>ComponentType<T> regsiterComponent(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Main.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void register() {
        Main.LOGGER.info("Registering Mod Data Components");
    }
}
