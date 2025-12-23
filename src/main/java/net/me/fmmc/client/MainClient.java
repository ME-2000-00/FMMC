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

        WorldRenderEvents.AFTER_TRANSLUCENT.register(context -> {
            MinecraftClient client = MinecraftClient.getInstance();
            ClientPlayerEntity player = client.player;

//            Vec3d direction = player.getRotationVec(1.0F).normalize();
            Vec3d direction = new Vec3d(0,0,1);
            direction = direction.rotateX(tick * 0.001f);
            Vec3d right = new Vec3d(0, 1, 0).crossProduct(direction).normalize();
            right = Util.rotateAroundAxis(right, direction, tick * 0.01d);



            Camera camera = context.camera();
            Vec3d camPos = camera.getPos();

            context.matrixStack().push();
            context.matrixStack().translate(
                    -camPos.x + 0.5,
                    -camPos.y + 57,
                    -camPos.z + 0
            );

            // Draw geometry using a BufferBuilder
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);

            Matrix4f mat = context.matrixStack().peek().getPositionMatrix();

            // A simple cube face
            buffer.vertex(mat, (float) 0, (float) 0, (float) 0).color(255, 0, 0, 255);
            buffer.vertex(mat,
                    (float) ((float) 0 + (direction.x * 2f)),
                    (float) ((float) 0 + (direction.y * 2f)),
                    (float) ((float) 0 + (direction.z * 2f))
            ).color(0, 255, 0, 255);

            buffer.vertex(mat,
                    (float) ((float) 0 + (direction.x * 2f)),
                    (float) ((float) 0 + (direction.y * 2f)),
                    (float) ((float) 0 + (direction.z * 2f))
            ).color(0, 255, 0, 255);
            buffer.vertex(mat,
                    (float) ((float) 0 + (right.x * 2f) + (direction.x * 2f)),
                    (float) ((float) 0 + (right.y * 2f) + (direction.y * 2f)),
                    (float) ((float) 0 + (right.z * 2f) + (direction.z * 2f))
            ).color(0, 0, 255, 255);

            // We'll get to this bit in the next section.
            RenderSystem.setShader(GameRenderer::getPositionColorProgram);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

            // Render using shader
            BufferRenderer.drawWithGlobalProgram(buffer.end());

            context.matrixStack().pop();

            tick += 1;
        });
    }
}