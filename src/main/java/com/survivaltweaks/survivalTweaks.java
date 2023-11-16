package com.survivaltweaks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.util.WorldSavePath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class survivalTweaks implements ModInitializer {

    public static final config CONFIG = new config();
    public static Path CONFIG_PATH = null;

    public void onInitialize() {
        survivalTweaksRegistries.registerTweaks();
        System.out.println("Simple Survival Tweaks loaded!");

        ServerWorldEvents.LOAD.register(((server, world) -> {
            Path configDir = server.getSavePath(WorldSavePath.ROOT).resolve("config");

            if (!Files.isDirectory(configDir)) {
                try {
                    Files.createDirectory(configDir);
                } catch (IOException e) {
                    System.out.println("Failed to create config directory");
                    throw new RuntimeException(e);
                }
            }

            CONFIG.load(configDir.resolve("survivaltweaks.properties"));
            CONFIG_PATH = configDir.resolve("survivaltweaks.properties");
        }));
    }

}
