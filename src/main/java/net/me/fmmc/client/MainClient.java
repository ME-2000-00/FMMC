package net.me.fmmc.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.me.fmmc.Main;
import net.me.fmmc.client.keybinds.KeyBinds;
import net.me.fmmc.client.keybinds.KeyHandler;
import net.me.fmmc.client.rendering.gui.ScytheGui;
import net.me.fmmc.items.ModItems;
import net.minecraft.client.option.Perspective;
import net.minecraft.item.ItemStack;

public class MainClient implements ClientModInitializer {
//    public static Counter counter = new Counter();
//    public static SlashCounter Scounter = new SlashCounter();
//    public static UltiCounter Ucounter = new UltiCounter();

    @Override
    public void onInitializeClient() {
        // register keybindings
        KeyBinds.register();
        // register key handler
        KeyHandler.register();
        // gui register
        ScytheGui.register();




        ClientTickEvents.START_CLIENT_TICK.register(client -> {

//            Main.LOGGER.info("SLASH COOLDOWN: " + String.valueOf(Scounter.getBlockTimer()));

            if(client.player != null) {
                ItemStack main = client.player.getMainHandStack();


                if (main.getItem() == ModItems.SCYTHE) {
//                    client.options.setPerspective(Perspective.THIRD_PERSON_BACK);|
                }
            }
        });
    }
}
