package net.me.fmmc;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

import java.util.List;

public class Util {
    public static void fireBeam(ServerWorld world, ServerPlayerEntity player) {
        Vec3d start = player.getEyePos();
        Vec3d direction = player.getRotationVec(1.0F).normalize();
//        Vec3d right = direction.crossProduct(new Vec3d(0,1,0)).normalize();
//        right.rotateX(world.random.nextFloat() * 360);

        double maxDistance = 50.0;
        double step = 0.25; // smaller = smoother beam

        for (double d = 0; d <= maxDistance; d += step) {
            Vec3d pos = start.add(direction.multiply(d));
            BlockPos blockPos = BlockPos.ofFloored(pos);

            BlockState state = world.getBlockState(blockPos);

            // skip air & fluids
            if (state.isAir() || state.getFluidState().isStill()) continue;

            // optional: unbreakable blocks
            if (state.getHardness(world, blockPos) < 0) continue;

            // break block (false = don't drop loot)
            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2; y++) {
                    for (int z = -1; z < 2; z++) {
                        world.breakBlock(blockPos.add(new Vec3i(x,y,z)), false, player, 0);
                        Util.damage_AOE_plr(5, blockPos, player, 20.0f);
                    }
                }
            }

        }
    }

    public static void damage_AOE_plr(int box_scale, BlockPos pos, ServerPlayerEntity player, float damage) {
        Box damageBox = new Box(pos).expand(box_scale);

        List<LivingEntity> entities = player.getWorld().getEntitiesByClass(
                LivingEntity.class,
                damageBox,
                e -> e.isAlive() && e != player // don’t hit yourself
        );

        for (LivingEntity target : entities) {
            target.damage(player.getDamageSources().playerAttack(player), damage);
        }
    }

    public static Vec3d rotateAroundAxis(Vec3d v, Vec3d axis, double angleRad) {
        Vec3d k = axis.normalize();

        double cos = Math.cos(angleRad);
        double sin = Math.sin(angleRad);

        return v.multiply(cos)
                .add(k.crossProduct(v).multiply(sin))
                .add(k.multiply(k.dotProduct(v) * (1 - cos)));
    }

}
