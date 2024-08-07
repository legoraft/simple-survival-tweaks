package com.survivaltweaks;

import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class config {

    public static boolean SURVIVAL_DEBUG_STICK = false;
    public static boolean NO_EXPENSIVE = false;
    public static boolean CHEAP_RENAME = false;
    public static boolean NO_ENDERMAN_GRIEF = false;
    public static boolean NO_XP_PENALTY = false;
    public static boolean PHANTOM_MOBCAP = false;

    public void write(Properties properties) {
        properties.setProperty("survival_debug_stick", Boolean.toString(SURVIVAL_DEBUG_STICK));
        properties.setProperty("no_too_expensive", Boolean.toString(NO_EXPENSIVE));
        properties.setProperty("cheap_rename", Boolean.toString(CHEAP_RENAME));
        properties.setProperty("no_enderman_grief", Boolean.toString(NO_ENDERMAN_GRIEF));
        properties.setProperty("no_xp_penalty", Boolean.toString(NO_XP_PENALTY));
        properties.setProperty("phantom_mobcap", Boolean.toString(PHANTOM_MOBCAP));
    }

    public void read(Properties properties) {
        SURVIVAL_DEBUG_STICK = Boolean.parseBoolean(properties.getProperty("survival_debug_stick"));
        NO_EXPENSIVE = Boolean.parseBoolean(properties.getProperty("no_too_expensive"));
        CHEAP_RENAME = Boolean.parseBoolean(properties.getProperty("cheap_rename"));
        NO_ENDERMAN_GRIEF = Boolean.parseBoolean(properties.getProperty("no_enderman_grief"));
        NO_XP_PENALTY = Boolean.parseBoolean(properties.getProperty("no_xp_penalty"));
        PHANTOM_MOBCAP = Boolean.parseBoolean(properties.getProperty("phantom_mobcap"));
    }

    public void save(Path configPath) {
        Properties properties = new Properties();
        write(properties);
        if (!Files.exists(configPath)) {
            try {
                Files.createFile(configPath);
            } catch (IOException e) {
                LogManager.getLogger("Simple Survival Tweaks").error("Failed to create config file");
                e.printStackTrace();
            }
        }
        try {
            properties.store(Files.newOutputStream(configPath), "Simple Survival Tweaks config file");
        } catch (IOException e) {
            LogManager.getLogger("Simple Survival Tweaks").error("Failed to write config");
            e.printStackTrace();
        }
    }

    public void load(Path configPath) {
        Properties properties = new Properties();
        if (!Files.exists(configPath)) {
            try {
                Files.createFile(configPath);
                save(configPath);
            } catch (IOException e) {
                LogManager.getLogger("Simple Survival Tweaks").error("Failed to create config file");
                e.printStackTrace();
            }
        }
        try {
            properties.load(Files.newInputStream(configPath));
        } catch (IOException e) {
            LogManager.getLogger("Simple Survival Tweaks").error("Failed to read config");
            e.printStackTrace();
        }
        read(properties);
    }

}
