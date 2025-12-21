package net.me.fmmc;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.me.fmmc.client.rendering.particles.ModParticles;
import net.me.fmmc.commands.ModCommands;
import net.me.fmmc.component.ModDataComponents;
import net.me.fmmc.items.ModItems;
import net.me.fmmc.payload.ModPayloads;
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
        ModPayloads.register();
        ModNetworking.register();


        // handeling payload events
        ServerTickEvents.START_WORLD_TICK.register(world -> {
            world.getPlayers().forEach(player -> {
                boolean blocking = player.getMainHandStack().getOrDefault(ModDataComponents.IS_BLOCKING, false);
                if (blocking) {
                    world.spawnParticles(ModParticles.BLOCK_PARTICLE, player.getX(), player.getY() + 1.0, player.getZ(), 5, 0.5, 0.5, 0.5, 0.0);
                }
            });
        });
    }
}
