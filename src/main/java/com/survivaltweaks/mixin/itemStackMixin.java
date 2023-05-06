package com.survivaltweaks.mixin;

import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class itemStackMixin {

    @Inject(method = "getRepairCost", at = @At("RETURN"), cancellable = true)
    private void getRepairCost(CallbackInfoReturnable<Integer> cir) {
        if(cir.getReturnValueI() >  0)  {
            cir.setReturnValue(0);
        }
    }

}
