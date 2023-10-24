package com.survivaltweaks;

import dev.lambdaurora.spruceui.Position;
import dev.lambdaurora.spruceui.option.SpruceBooleanOption;
import dev.lambdaurora.spruceui.option.SpruceOption;
import dev.lambdaurora.spruceui.screen.SpruceScreen;
import dev.lambdaurora.spruceui.widget.SpruceButtonWidget;
import dev.lambdaurora.spruceui.widget.container.SpruceOptionListWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.Properties;

public class configScreen extends SpruceScreen {

    private final Screen parent;

    private final SpruceOption survivalDebugStickToggle;
    private final SpruceOption endermanGriefToggle;
    private final SpruceOption noExpensiveToggle;
    private final SpruceOption cheapRenameToggle;
    private final SpruceOption noXpPenaltyToggle;

    public configScreen(@Nullable Screen parent) {
        super(Text.literal("Armorhud test GUI"));
        this.parent = parent;

        this.survivalDebugStickToggle = new SpruceBooleanOption("Survival debug stick", () -> config.SURVIVAL_DEBUG_STICK, newValue -> config.SURVIVAL_DEBUG_STICK = newValue, Text.literal("Makes the debug stick usable in survival"));
        this.endermanGriefToggle = new SpruceBooleanOption("No enderman grief", () -> config.NO_ENDERMAN_GRIEF, newValue -> config.NO_ENDERMAN_GRIEF = newValue, Text.literal("Prevents enderman from picking up anything except pumpkins and melons"));
        this.noExpensiveToggle = new SpruceBooleanOption("No 'Too Expensive'", () -> config.NO_EXPENSIVE, newValue -> config.NO_EXPENSIVE = newValue, Text.literal("Prevents enchantments in an anvil from getting 'Too Expensive"));
        this.cheapRenameToggle = new SpruceBooleanOption("Cheap rename", () -> config.CHEAP_RENAME, newValue -> config.CHEAP_RENAME = newValue, Text.literal("Makes renames always cost 1 level in the anvil"));
        this.noXpPenaltyToggle = new SpruceBooleanOption("No XP penalty", () -> config.NO_XP_PENALTY, newValue -> config.NO_XP_PENALTY = newValue, Text.literal("Removes the level cap of 7 XP levels when you die"));

    }

    @Override
    protected void init() {
        super.init();

        SpruceOptionListWidget list = new SpruceOptionListWidget(Position.of(0, 34), this.width, this.height - 69);
        list.addOptionEntry(this.survivalDebugStickToggle, this.endermanGriefToggle);
        list.addOptionEntry(this.noExpensiveToggle, this.cheapRenameToggle);
        list.addOptionEntry(this.noXpPenaltyToggle, null);

        this.addDrawableChild(list);

        this.addDrawableChild(new SpruceButtonWidget(Position.of(this.width / 2 - 100, this.height - 30), 200, 20, Text.literal("Done"), button -> this.applyChanges()));
    }

    @Override
    public void renderTitle(DrawContext context, int MouseX, int MouseY, float delta) {
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 8, 16777215);
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackgroundTexture(context);
    }

    public void close() {
        this.client.setScreen(parent);
    }

    public void applyChanges() {
        config config = survivalTweaks.CONFIG;
        Properties properties = new Properties();
        config.write(properties);
        config.save();
        this.client.setScreen(parent);
    }

}