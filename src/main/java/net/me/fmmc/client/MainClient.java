package net.me.fmmc.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.me.fmmc.Util;
import net.me.fmmc.client.keybinds.KeyBinds;
import net.me.fmmc.client.keybinds.KeyHandler;
import net.me.fmmc.client.rendering.gui.ScytheGui;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EmptyEntityRenderer;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

public class MainClient implements ClientModInitializer {

    private static int tick = 0;

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