package com.survivaltweaks;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static com.mojang.brigadier.arguments.BoolArgumentType.bool;
import static com.mojang.brigadier.arguments.BoolArgumentType.getBool;

public class commands {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {

        dispatcher.register(CommandManager.literal("survivaltweaks")
                .then(CommandManager.literal("noExpensive").executes(c -> { c.getSource().sendMessage(Text.literal("Rule noExpensive is currently set to: " + config.NO_EXPENSIVE)); return 1;})
                        .then(CommandManager.argument("boolean", bool())
                                .executes(c -> {
                                    config.NO_EXPENSIVE = getBool(c, "boolean");
                                    applyChanges();
                                    c.getSource().sendMessage(Text.literal("Rule noExpensive is now set to: " + config.NO_EXPENSIVE));
                                    return 1;
                                })
                        )
                )
                .then(CommandManager.literal("noEndermanGriefing").executes(c -> { c.getSource().sendMessage(Text.literal("Rule noEndermanGriefing is currently set to: " + config.NO_ENDERMAN_GRIEF)); return 1;})
                        .then(CommandManager.argument("boolean", bool())
                                .executes(c -> {
                                    config.NO_ENDERMAN_GRIEF = getBool(c, "boolean");
                                    applyChanges();
                                    c.getSource().sendMessage(Text.literal("Rule noEndermanGriefing is now set to: " + config.NO_ENDERMAN_GRIEF));
                                    return 1;
                                })
                        )
                )
                .then(CommandManager.literal("survivalDebugStick").executes(c -> { c.getSource().sendMessage(Text.literal("Rule survivalDebugStick is currently set to: " + config.SURVIVAL_DEBUG_STICK)); return 1;})
                        .then(CommandManager.argument("boolean", bool())
                                .executes(c -> {
                                    config.SURVIVAL_DEBUG_STICK = getBool(c, "boolean");
                                    applyChanges();
                                    c.getSource().sendMessage(Text.literal("Rule survivalDebugStick is now set to: " + config.SURVIVAL_DEBUG_STICK));
                                    return 1;
                                })
                        )
                )
                .then(CommandManager.literal("cheapRename").executes(c -> { c.getSource().sendMessage(Text.literal("Rule cheapRename is currently set to: " + config.CHEAP_RENAME)); return 1;})
                        .then(CommandManager.argument("boolean", bool())
                                .executes(c -> {
                                    config.CHEAP_RENAME = getBool(c, "boolean");
                                    applyChanges();
                                    c.getSource().sendMessage(Text.literal("Rule cheapRename is now set to: " + config.CHEAP_RENAME));
                                    return 1;
                                })
                        )
                )
                .then(CommandManager.literal("noXpPenalty").executes(c -> { c.getSource().sendMessage(Text.literal("Rule noXpPenalty is currently set to: " + config.NO_XP_PENALTY)); return 1;})
                        .then(CommandManager.argument("boolean", bool())
                                .executes(c -> {
                                    config.NO_XP_PENALTY = getBool(c, "boolean");
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
