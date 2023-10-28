package com.survivaltweaks;

import net.fabricmc.api.ModInitializer;

public class survivalTweaks implements ModInitializer {

    public static final config CONFIG = new config();

    public void onInitialize()  {
        CONFIG.load();
        survivalTweaksRegistries.registerTweaks();
        System.out.println("Simple Survival Tweaks loaded!");
    }

}
