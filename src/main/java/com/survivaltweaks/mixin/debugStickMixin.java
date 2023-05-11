package com.survivaltweaks.mixin;

import com.survivaltweaks.config;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DebugStickItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DebugStickItem.class)
public class debugStickMixin {

    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isCreativeLevelTwoOp()Z"))
    public boolean isCreativeLevelTwoOp(PlayerEntity playerEntity) {
        if (config.SURVIVAL_DEBUG_STICK) {
            return true;
        }
        return playerEntity.isCreativeLevelTwoOp();
    }

}
