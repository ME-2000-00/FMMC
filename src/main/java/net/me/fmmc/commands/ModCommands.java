package net.me.fmmc.commands;

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
                    CommandManager.argument("set", IntegerArgumentType.integer())
                            .executes(ModCommands::blocking)
            ));

            dispatcher.register(CommandManager.literal("slashing_cooldown").then(
                    CommandManager.argument("set", IntegerArgumentType.integer())
                            .executes(ModCommands::slashing)
            ));

            dispatcher.register(CommandManager.literal("ultimate_cooldown").then(
                    CommandManager.argument("set", IntegerArgumentType.integer())
                            .executes(ModCommands::ulting)
            ));
        });
    }

    //TODO: make sure they dont modify client data, make the data server sided

    private static int blocking(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {

        int value = IntegerArgumentType.getInteger(serverCommandSourceCommandContext, "set");
        MainClient.MAX_BLOOCKING = value;
        serverCommandSourceCommandContext.getSource().sendFeedback(() -> Text.literal("set blocking cooldown to: " + value), true);


        return 1;
    }

    private static int slashing(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        int value = IntegerArgumentType.getInteger(serverCommandSourceCommandContext, "set");
        MainClient.MAX_SLASHING = value;
        serverCommandSourceCommandContext.getSource().sendFeedback(() -> Text.literal("set slashing cooldown to: " + value), true);

        return 1;
    }

    private static int ulting(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        int value = IntegerArgumentType.getInteger(serverCommandSourceCommandContext, "set");
        MainClient.MAX_ULTI = value;
        serverCommandSourceCommandContext.getSource().sendFeedback(() -> Text.literal("set ultimate cooldown to: " + value), true);

        return 1;
    }
}
