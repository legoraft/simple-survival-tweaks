package com.survivaltweaks.mixin;

import com.survivaltweaks.config;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DebugStickItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DebugStickItem.class)
public abstract class debugStickMixin extends Item {

    public debugStickMixin(Settings settings) {
        super(settings);
    }

    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isCreativeLevelTwoOp()Z"))
    private boolean survivalDebug(PlayerEntity player) {
        if (config.SURVIVAL_DEBUG_STICK) {
            return true;
        } else {
            return player.isCreativeLevelTwoOp();
        }
    }

}
