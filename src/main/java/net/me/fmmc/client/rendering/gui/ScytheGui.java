package net.me.fmmc.client.rendering.gui;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.me.fmmc.Main;
import net.me.fmmc.client.MainClient;
import net.me.fmmc.component.ModDataComponents;
import net.me.fmmc.items.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Perspective;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ScytheGui {
    public static void register() {
        HudRenderCallback.EVENT.register(((drawContext, renderTickCounter) -> {

            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null) return;

            ItemStack main = client.player.getMainHandStack();

            boolean holdingScythe = main.isOf(ModItems.SCYTHE);

            // render gui her
            if (holdingScythe && !client.options.hudHidden) {
                int width  = client.getWindow().getScaledWidth();
                int height = client.getWindow().getScaledHeight();

                drawContext.getMatrices().push();

                // ulti X
                if (main.getOrDefault(ModDataComponents.ULT_KILLS, 0) >= 3) {
                    drawContext.drawTexture(Identifier.of(Main.MOD_ID, "textures/gui/ulti1.png"), 2, height - 39, 0, 0, 37, 37, 37, 37);
                } else {
                    drawContext.drawTexture(Identifier.of(Main.MOD_ID, "textures/gui/ulti1_dark.png"), 2, height - 39, 0, 0, 37, 37, 37, 37);
                }

                // slashes  C

                if (main.getOrDefault(ModDataComponents.COOLDOWN_SLASHING, 0) < 1) {
                    drawContext.drawTexture(Identifier.of(Main.MOD_ID, "textures/gui/slash.png"), 41, height - 39, 0, 0, 37, 37, 37, 37);
                } else {
                    drawContext.drawTexture(Identifier.of(Main.MOD_ID, "textures/gui/slash_dark.png"), 41, height - 39, 0, 0, 37, 37, 37, 37);
                }


                // blocking  V

                if (main.getOrDefault(ModDataComponents.COOLDOWN_BLOCKING, 0) < 1) {
                    drawContext.drawTexture(Identifier.of(Main.MOD_ID, "textures/gui/block.png"), 82, height - 39, 0, 0, 37, 37, 37, 37);
                } else {
                    drawContext.drawTexture(Identifier.of(Main.MOD_ID, "textures/gui/block_dark.png"), 82, height - 39, 0, 0, 37, 37, 37, 37);
                }

                drawContext.getMatrices().pop();
            }
        }));
    }
}
