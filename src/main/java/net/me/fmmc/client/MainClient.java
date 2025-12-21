package net.me.fmmc.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.me.fmmc.Main;
import net.me.fmmc.client.keybinds.KeyBinds;
import net.me.fmmc.client.keybinds.KeyHandler;
import net.me.fmmc.client.rendering.gui.ScytheGui;
import net.me.fmmc.component.ModDataComponents;
import net.me.fmmc.items.ModItems;
import net.minecraft.client.option.Perspective;
import net.minecraft.item.ItemStack;

public class MainClient implements ClientModInitializer {

    public static int MAX_BLOOCKING = 10;
    public static int MAX_SLASHING = 120;
    public static int MAX_ULTI = 360;

    @Override
    public void onInitializeClient() {
        // register keybindings
        KeyBinds.register();
        // register key handler
        KeyHandler.register();
        // gui register
        ScytheGui.register();
    }
}
