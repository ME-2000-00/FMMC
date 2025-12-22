package net.me.fmmc.client;

import net.fabricmc.api.ClientModInitializer;
import net.me.fmmc.client.keybinds.KeyBinds;
import net.me.fmmc.client.keybinds.KeyHandler;
import net.me.fmmc.client.rendering.gui.ScytheGui;

public class MainClient implements ClientModInitializer {

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