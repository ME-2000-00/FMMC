package net.me.fmmc;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.me.fmmc.commands.ModCommands;
import net.me.fmmc.component.ModDataComponents;
import net.me.fmmc.items.ModItems;
import net.me.fmmc.items.custom.Scythe;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
    public static final String MOD_ID = "fmmc";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    @Override
    public void onInitialize() {
        ModItems.register();
        ModCommands.register();
        ModDataComponents.register();

        // for left-clicking with the scythe
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (player.getStackInHand(hand).isOf(ModItems.SCYTHE)) {
                ((Scythe) ModItems.SCYTHE).LeftClickAction(player, world, hand, entity);
            }
            return ActionResult.PASS;
        });
    }
}
