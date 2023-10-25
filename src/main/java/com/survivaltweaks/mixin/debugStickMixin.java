package com.survivaltweaks.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DebugStickItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;

@Mixin(DebugStickItem.class)
public abstract class debugStickMixin {

    @Shadow
    private static void sendMessage(PlayerEntity player, Text message) { }

    @Shadow
    private static <T extends Comparable<T>> BlockState cycle(BlockState state, Property<T> property, boolean inverse) { return null; }

    @Shadow
    private static <T extends Comparable<T>> String getValueString(BlockState state, Property<T> property) { return null; }

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void survivalDebug(PlayerEntity player, BlockState state, WorldAccess world, BlockPos pos, boolean update, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (player.isCreativeLevelTwoOp()) { return; }

        Block block = state.getBlock();
        StateManager<Block, BlockState> stateManager = block.getStateManager();
        Collection<Property<?>> collection = stateManager.getProperties();

        NbtCompound nbtCompound = stack.getOrCreateSubNbt("debugProp");

        String blockName = Registries.BLOCK.getId(block).toString();
        String propertyName = nbtCompound.getString(blockName);

        Property<?> property = stateManager.getProperty(propertyName);

        if (player.isSneaking()) {
            property = getNextProperty(collection, property);
            nbtCompound.putString(blockName, property.getName());
            sendMessage(player, Text.of(String.format("Selected \"%s\" (%s)", property.getName(), getValueString(state, property))));
        } else {
            if (collection.isEmpty() || property == null) {
            sendMessage(player, Text.of(String.format("%s has no properties", blockName)));
            return;
            }
            BlockState newState = cycle(state, property, false);
            world.setBlockState(pos, newState, 18);
            sendMessage(player, Text.of(String.format("\"%s\" to %s", property.getName(), getValueString(newState, property))));
        }
        cir.setReturnValue(true);
    }

    @Unique
    private Property<?> getNextProperty(Collection<Property<?>> collection, @Nullable Property<?> property) {
        property = Util.next(collection, property);
        return property;
    }

}
