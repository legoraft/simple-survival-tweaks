package com.survivaltweaks.mixin;

import com.survivaltweaks.config;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.spawner.PhantomSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PhantomSpawner.class)
public class phantomSpawnerMixin {

    @Inject(method = "spawn", at = @At("RETURN"), cancellable = true)
    private void phantomSpawn(ServerWorld world, boolean spawnMonsters, boolean spawnAnimals, CallbackInfoReturnable<Integer> cir) {
        if (config.PHANTOM_MOBCAP && SpawnGroup.MONSTER.getCapacity() >= 70) {
            cir.setReturnValue(0);
        }
    }

}
