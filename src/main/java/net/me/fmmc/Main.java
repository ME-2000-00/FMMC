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
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
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

        ServerTickEvents.START_SERVER_TICK.register(Server -> {
            ServerWorld world = null;

            for (RegistryKey<World> key : Server.getWorldRegistryKeys()) {
                world = Server.getWorld(key);
            }

            if (world != null) {
                // do server world based effects here if needed
            }


        });
    }
}
