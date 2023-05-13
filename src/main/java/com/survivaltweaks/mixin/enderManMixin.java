package com.survivaltweaks.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net/minecraft/entity/mob/EndermanEntity$PickUpBlockGoal", priority = 1500)
abstract class enderManMixin {

    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
    private boolean endermanAllowed(BlockState blockState, TagKey<?> tagKey, Operation<Boolean> originalOperation) {
        return blockState.isOf(Blocks.PUMPKIN) || blockState.isOf(Blocks.MELON);
    }

}
