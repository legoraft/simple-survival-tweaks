package com.survivaltweaks;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;

public class survivalTweaksRegistries {

    public static void registerTweaks() {
        registerDebugStickTrade();
        registerCommands();
    }

    private static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(commands::register);
    }

    private static void registerDebugStickTrade() {
        if (!config.SURVIVAL_DEBUG_STICK) { return; }
        ItemStack debugStick = Items.DEBUG_STICK.getDefaultStack();
        TradeOfferHelper.registerWanderingTraderOffers(2, factory -> factory.add((((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 5), debugStick, 2, 1, 1.0F)))));
    }

}
