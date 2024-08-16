package com.survivaltweaks.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.survivaltweaks.config;
import net.minecraft.item.DebugStickItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DebugStickItem.class)
public abstract class debugStickMixin {

    @ModifyExpressionValue(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isCreativeLevelTwoOp()Z"))
    private boolean survivalDebugStick(boolean original) {
        if (config.SURVIVAL_DEBUG_STICK) {
            return true;
        } else {
            return original;
        }
    }

}
