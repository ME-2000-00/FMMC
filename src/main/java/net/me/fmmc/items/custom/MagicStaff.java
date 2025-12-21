package net.me.fmmc.items.custom;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MagicStaff extends Item {


    public MagicStaff(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (world.isClient) {
            // client-side only stuff (particles, sounds, animations)
            return TypedActionResult.success(user.getStackInHand(hand));
        }

        // ✅ SAFE: server-side only
        ServerWorld serverWorld = (ServerWorld) world;


        assert client.player != null;
        HitResult hit = client.player.raycast(30.0, 0.0f, false);

        if (hit.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHit = (BlockHitResult) hit;
            BlockPos HitBlockPos = blockHit.getBlockPos();

            // start casting animation



        }

        return TypedActionResult.success(user.getStackInHand(hand));
    }
}