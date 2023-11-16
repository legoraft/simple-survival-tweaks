package com.survivaltweaks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.WorldSavePath;

import java.nio.file.Path;

public class survivalTweaks implements ModInitializer {

    public static final config CONFIG = new config();

    public void onInitialize()  {
        CONFIG.load();
        survivalTweaksRegistries.registerTweaks();
        System.out.println("Simple Survival Tweaks loaded!");

        ServerWorldEvents.LOAD.register(((server, world) -> {
            Path config = server.getSavePath(WorldSavePath.ROOT);
            System.out.println(config);
        }));
    }

}
