package com.survivaltweaks.mixin;

import com.survivaltweaks.config;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.Property;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public class anvilScreenHandlerMixin {

    @Shadow @Final private Property levelCost;

    @Inject(method = "updateResult", at = @At("TAIL"))
    private void setLevelCost(CallbackInfo ci) {
        int cost = levelCost.get();

        if (cost > 5 && config.NO_EXPENSIVE) {
            levelCost.set(5);
        }
    }
}
