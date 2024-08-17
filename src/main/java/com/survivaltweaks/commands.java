package com.survivaltweaks;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.nio.file.Path;

import static com.mojang.brigadier.arguments.BoolArgumentType.bool;
import static com.mojang.brigadier.arguments.BoolArgumentType.getBool;

public class commands {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {

        dispatcher.register(CommandManager.literal("survivaltweaks")
                .then(CommandManager.literal("noExpensive").executes(c -> { c.getSource().sendFeedback(() -> Text.translatable("commands.tweak.query", "noExpensive", "%s".formatted(config.NO_EXPENSIVE)), false); return 1;})
                        .then(CommandManager.argument("boolean", bool())
                                .executes(c -> {
                                    config.NO_EXPENSIVE = getBool(c, "boolean");
                                    config.save(survivalTweaks.CONFIG_PATH);
                                    c.getSource().sendFeedback(() -> Text.translatable("commands.tweak.set", "noExpensive", "%s".formatted(config.NO_EXPENSIVE)), false);
                                    return 1;
                                })
                        )
                )
                .then(CommandManager.literal("noEndermanGriefing").executes(c -> { c.getSource().sendFeedback(() -> Text.translatable("commands.tweak.query", "noEndermanGriefing", "%s".formatted(config.NO_ENDERMAN_GRIEF)), false); return 1;})
                        .then(CommandManager.argument("boolean", bool())
                                .executes(c -> {
                                    config.NO_ENDERMAN_GRIEF = getBool(c, "boolean");
                                    config.save(survivalTweaks.CONFIG_PATH);
                                    c.getSource().sendFeedback(() -> Text.translatable("commands.tweak.set", "noEndermanGriefing", "%s".formatted(config.NO_ENDERMAN_GRIEF)), false);
                                    return 1;
                                })
                        )
                )
                .then(CommandManager.literal("survivalDebugStick").executes(c -> { c.getSource().sendFeedback(() -> Text.translatable("commands.tweak.query", "survivalDebugStick", "%s".formatted(config.SURVIVAL_DEBUG_STICK)), false); return 1;})
                        .then(CommandManager.argument("boolean", bool())
                                .executes(c -> {
                                    config.SURVIVAL_DEBUG_STICK = getBool(c, "boolean");
                                    config.save(survivalTweaks.CONFIG_PATH);
                                    c.getSource().sendFeedback(() -> Text.translatable("commands.tweak.set", "survivalDebugStick", "%s".formatted(config.SURVIVAL_DEBUG_STICK)), false);
                                    return 1;
                                })
                        )
                )
                .then(CommandManager.literal("cheapRename").executes(c -> { c.getSource().sendFeedback(() -> Text.translatable("commands.tweak.query", "cheapRename", "%s".formatted(config.CHEAP_RENAME)), false); return 1;})
                        .then(CommandManager.argument("boolean", bool())
                                .executes(c -> {
                                    config.CHEAP_RENAME = getBool(c, "boolean");
                                    config.save(survivalTweaks.CONFIG_PATH);
                                    c.getSource().sendFeedback(() -> Text.translatable("commands.tweak.set", "cheapRename", "%s".formatted(config.CHEAP_RENAME)), false);
                                    return 1;
                                })
                        )
                )
                .then(CommandManager.literal("noXpPenalty").executes(c -> { c.getSource().sendFeedback(() -> Text.translatable("commands.tweak.query", "noXpPenalty", "%s".formatted(config.NO_XP_PENALTY)), false); return 1;})
                        .then(CommandManager.argument("boolean", bool())
                                .executes(c -> {
                                    config.NO_XP_PENALTY = getBool(c, "boolean");
                                    config.save(survivalTweaks.CONFIG_PATH);
                                    c.getSource().sendFeedback(() -> Text.translatable("commands.tweak.set", "noXpPenalty", "%s".formatted(config.NO_XP_PENALTY)), false);
                                    return 1;
                                })
                        )
                )
                .then(CommandManager.literal("phantomMobcap").executes(c -> { c.getSource().sendFeedback(() -> Text.translatable("commands.tweak.query", "phantomMobcap", "%s".formatted(config.PHANTOM_MOBCAP)), false); return 1;})
                        .then(CommandManager.argument("boolean", bool())
                                .executes(c -> {
                                    config.PHANTOM_MOBCAP = getBool(c, "boolean");
                                    config.save(survivalTweaks.CONFIG_PATH);
                                    c.getSource().sendFeedback(() -> Text.translatable("commands.tweak.set", "phantomMobcap", "%s".formatted(config.PHANTOM_MOBCAP)), false);
                                    return 1;
                                })
                        )
                )
        );
    }

}
