package com.survivaltweaks.mixin;

import com.survivaltweaks.config;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DebugStickStateComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DebugStickItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
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

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void survivalDebug(PlayerEntity player, BlockState state, WorldAccess world, BlockPos pos, boolean update, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (player.isCreativeLevelTwoOp() || !config.SURVIVAL_DEBUG_STICK) { return; }

        Block block = state.getBlock();
        RegistryEntry<Block> registryEntry = state.getRegistryEntry();
        StateManager<Block, BlockState> stateManager = registryEntry.value().getStateManager();
        Collection<Property<?>> collection = stateManager.getProperties();

        DebugStickStateComponent stateComponent = stack.get(DataComponentTypes.DEBUG_STICK_STATE);

        if (stateComponent == null) {
            return;
        }

        Property<?> property = stateComponent.properties().get(registryEntry);

        if (update) {
            if (property == null) {
                property = getNextProperty(collection, null);
            }

            BlockState newState = getNextBlockState(state, property);
            world.setBlockState(pos, newState, 18);

        } else {
            property = getNextProperty(collection, property);
            stack.set(DataComponentTypes.DEBUG_STICK_STATE, stateComponent.with(registryEntry, property));
        }
        cir.setReturnValue(true);
    }

    @Unique
    private <T extends Comparable<T>> BlockState getNextBlockState(BlockState state, Property<T> property) {
        T value =  state.get(property);

        return state.with(property, value);
    }

    @Unique
    private Property<?> getNextProperty(Collection<Property<?>> collection, @Nullable Property<?> property) {
        property = Util.next(collection, property);
        return property;
    }

}
