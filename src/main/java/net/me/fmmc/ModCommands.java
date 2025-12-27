package net.me.fmmc;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.me.fmmc.client.MainClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

public class ModCommands {



    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("blocking_cooldown").then(
                    CommandManager.argument("seconds", IntegerArgumentType.integer())
                            .executes(ModCommands::blocking)
            ));

            dispatcher.register(CommandManager.literal("slashing_cooldown").then(
                    CommandManager.argument("seconds", IntegerArgumentType.integer())
                            .executes(ModCommands::slashing)
            ));

            dispatcher.register(CommandManager.literal("max_ult_kills").then(
                    CommandManager.argument("kills", IntegerArgumentType.integer())
                            .executes(ModCommands::ult_kills)
            ));

            dispatcher.register(CommandManager.literal("FMMC_test")
                    .executes(ModCommands::test)
            );
        });
    }

    private static int ult_kills(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        int value = IntegerArgumentType.getInteger(serverCommandSourceCommandContext, "kills");
        Main.MAX_ULT_KILLS = value;
        serverCommandSourceCommandContext.getSource().sendFeedback(() -> Text.literal("set max ulting kills to " + value + " kills"), true);


        return 1;
    }

    private static int test(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        ServerWorld world = source.getWorld();

        Vec3d pos = source.getPosition();

        for (int i = 0; i < 20; i++) {
            VillagerEntity villager = EntityType.VILLAGER.create(world);
            if (villager == null) continue;

            villager.refreshPositionAndAngles(
                    pos.x,
                    pos.y,
                    pos.z,
                    world.random.nextFloat() * 360.0F,
                    0.0F
            );

            world.spawnEntity(villager);
        }

        source.sendFeedback(
                () -> Text.literal("Spawned 20 villagers"),
                false
        );

        return 1;
    }

    //TODO: make sure they dont modify client data, make the data server sided

    private static int blocking(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {

        int value = IntegerArgumentType.getInteger(serverCommandSourceCommandContext, "seconds");
        Main.BLOCKING_COOLD = value * 20;
        serverCommandSourceCommandContext.getSource().sendFeedback(() -> Text.literal("set blocking cooldown to " + value + " seconds"), true);


        return 1;
    }

    private static int slashing(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        int value = IntegerArgumentType.getInteger(serverCommandSourceCommandContext, "seconds");
        Main.SLASHING_COOLD = value * 20;
        serverCommandSourceCommandContext.getSource().sendFeedback(() -> Text.literal("set slashing cooldown to " + value + " seconds"), true);

        return 1;
    }
}