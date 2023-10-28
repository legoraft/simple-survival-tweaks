package com.survivaltweaks;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static com.mojang.brigadier.arguments.BoolArgumentType.bool;

public class commands {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {

        dispatcher.register(CommandManager.literal("survivaltweaks")
                .then(CommandManager.literal("noExpensive")
                        .then(CommandManager.argument("boolean", bool())
                                .executes(c -> {
                                    config.NO_EXPENSIVE = !config.NO_EXPENSIVE;
                                    applyChanges();
                                    c.getSource().sendMessage(Text.literal("Rule noExpensive is now set to: " + config.NO_EXPENSIVE));
                                    return 1;
                                })
                        )
                )
                .then(CommandManager.literal("endermanGriefing")
                        .then(CommandManager.argument("boolean", bool())
                                .executes(c -> {
                                    config.NO_ENDERMAN_GRIEF = !config.NO_ENDERMAN_GRIEF;
                                    applyChanges();
                                    c.getSource().sendMessage(Text.literal("Rule endermanGriefing is now set to: " + !config.NO_ENDERMAN_GRIEF));
                                    return 1;
                                })
                        )
                )
                .then(CommandManager.literal("survivalDebugStick")
                        .then(CommandManager.argument("boolean", bool())
                                .executes(c -> {
                                    config.SURVIVAL_DEBUG_STICK = !config.SURVIVAL_DEBUG_STICK;
                                    applyChanges();
                                    c.getSource().sendMessage(Text.literal("Rule survivalDebugStick is now set to: " + config.SURVIVAL_DEBUG_STICK));
                                    return 1;
                                })
                        )
                )
                .then(CommandManager.literal("cheapRename")
                        .then(CommandManager.argument("boolean", bool())
                                .executes(c -> {
                                    config.CHEAP_RENAME = !config.CHEAP_RENAME;
                                    applyChanges();
                                    c.getSource().sendMessage(Text.literal("Rule cheapRename is now set to: " + config.CHEAP_RENAME));
                                    return 1;
                                })
                        )
                )
                .then(CommandManager.literal("noXpPenalty")
                        .then(CommandManager.argument("boolean", bool())
                                .executes(c -> {
                                    config.NO_XP_PENALTY = !config.NO_XP_PENALTY;
                                    applyChanges();
                                    c.getSource().sendMessage(Text.literal("Rule noXpPenalty is now set to: " + config.NO_XP_PENALTY));
                                    return 1;
                                })
                        )
                )
        );
    }

    public static void applyChanges() {
        config config = survivalTweaks.CONFIG;
        java.util.Properties properties = new java.util.Properties();
        config.write(properties);
        config.save();
    }

}
