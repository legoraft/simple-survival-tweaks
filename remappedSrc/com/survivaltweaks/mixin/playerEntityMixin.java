package com.survivaltweaks.mixin;

import com.survivaltweaks.config;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class playerEntityMixin {

    @Shadow public int experienceLevel;
    @Shadow public abstract boolean isSpectator();

    @Inject(method = "getXpToDrop", at = @At("RETURN"), cancellable = true)
    public void droppedXp(CallbackInfoReturnable<Integer> cir) {
        int i = 0;
        double xpLevel = this.experienceLevel * 0.5;

        if (!this.isSpectator() && config.NO_XP_PENALTY) {
            if (xpLevel <= 16) {
                i = (int) (Math.pow(xpLevel, 2) + (6 * xpLevel) + 3);
            } else if (xpLevel <= 32) {
                i = (int) (2.5 * Math.pow(xpLevel, 2) - (40.5 * xpLevel) + 365);
            } else if (xpLevel > 32) {
                i = (int) (4.5 * Math.pow(xpLevel, 2) - (162.5 * xpLevel) + 2245);
            }
        }  else {
            i = this.experienceLevel * 7;
            i = Math.min(i, 100);
        }
        cir.setReturnValue(i);
    }

}
