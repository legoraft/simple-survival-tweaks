package com.survivaltweaks.mixin;

import com.survivaltweaks.config;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.Property;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(AnvilScreenHandler.class)
public class anvilScreenMixin {

    @Shadow @Final private Property levelCost;

    @Inject(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/Property;get()I", ordinal = 1), locals = LocalCapture.CAPTURE_FAILHARD)
    public void cheapRename(CallbackInfo ci, ItemStack itemStack, int i, int j, int k)  {
        if (k > 0 && k == i && config.CHEAP_RENAME) {
            levelCost.set(1);
        }
    }

}
