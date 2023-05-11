package com.survivaltweaks.mixin.client;

import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.client.gui.screen.ingame.ForgingScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreen.class)
public abstract class anvilScreenMixin extends ForgingScreen<AnvilScreenHandler> {

    public anvilScreenMixin(AnvilScreenHandler handler, PlayerInventory playerInventory, Text title, Identifier texture) {
        super(handler, playerInventory, title, texture);
    }

    @Inject(method = "drawForeground", at = @At("TAIL"))
    public void drawZero(MatrixStack matrices, int mouseX, int mouseY, CallbackInfo ci) {
        int i = this.handler.getLevelCost();
        int j = 8453920;
        int h = 9013641;

        if (i == 0) {
            h = 1325400064;
        }

        Text text;
        text = Text.translatable("container.repair.cost", i);

        if (this.handler.getSlot(2).hasStack())  {
            int k = this.backgroundWidth - 8 - this.textRenderer.getWidth(text) - 2;
            fill(matrices, k - 2, 67, this.backgroundWidth - 8, 79, h);
            this.textRenderer.drawWithShadow(matrices, text, (float)k, 69.0F, j);
        }
    }

}
