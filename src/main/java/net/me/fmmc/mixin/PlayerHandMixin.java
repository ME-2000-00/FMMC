package net.me.fmmc.mixin;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PlayerEntityRenderer.class)
public interface PlayerHandMixin {

    @Invoker("renderArm")
    void invokeRenderArm(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, AbstractClientPlayerEntity player, ModelPart arm, ModelPart sleeve);
}
