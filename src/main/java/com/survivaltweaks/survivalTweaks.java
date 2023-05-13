package com.survivaltweaks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;

public class survivalTweaks implements ModInitializer {

    public static final config CONFIG = new config();

    public void onInitialize()  {
        CONFIG.load();
        if (config.SURVIVAL_DEBUG_STICK) {
            addDebugStickTrade();
        }
        System.out.println("Simple Survival Tweaks loaded!");
    }

    private static void addDebugStickTrade() {
        ItemStack debugStick = Items.DEBUG_STICK.getDefaultStack();
        TradeOfferHelper.registerWanderingTraderOffers(2, factory -> factory.add((((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 5), debugStick, 2, 1, 1.0F)))));
    }

}
