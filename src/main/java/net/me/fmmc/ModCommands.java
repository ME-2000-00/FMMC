package net.me.fmmc;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.me.fmmc.client.MainClient;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

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
        });
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