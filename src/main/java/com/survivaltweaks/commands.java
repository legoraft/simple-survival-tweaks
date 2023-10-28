package com.survivaltweaks;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContextBuilder;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import org.apache.logging.log4j.core.jmx.Server;

import static com.mojang.brigadier.arguments.BoolArgumentType.bool;
import static com.mojang.brigadier.arguments.BoolArgumentType.getBool;

public class commands {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {

        dispatcher.register(CommandManager.literal("survivaltweaks")
                .then(CommandManager.literal("noExpensive")
                        .then(CommandManager.argument("Bool", bool())
                                .executes(c -> {
                                    config.NO_EXPENSIVE = !config.NO_EXPENSIVE;
                                    applyChanges();
                                    c.getSource().sendMessage(Text.literal("Rule noExpensive is now: " + config.NO_EXPENSIVE));
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
