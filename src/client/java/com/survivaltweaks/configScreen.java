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
        super(Text.translatable("title.config"));
        this.parent = parent;

        this.survivalDebugStickToggle = new SpruceBooleanOption("config.survivaldebugstick", () -> config.SURVIVAL_DEBUG_STICK, newValue -> config.SURVIVAL_DEBUG_STICK = newValue, Text.translatable("config.description.survivaldebugstick"));
        this.endermanGriefToggle = new SpruceBooleanOption("config.noendermangrief", () -> config.NO_ENDERMAN_GRIEF, newValue -> config.NO_ENDERMAN_GRIEF = newValue, Text.translatable("config.description.noendermagrief"));
        this.noExpensiveToggle = new SpruceBooleanOption("config.notooexpensive", () -> config.NO_EXPENSIVE, newValue -> config.NO_EXPENSIVE = newValue, Text.translatable("config.description.notooexpensive"));
        this.cheapRenameToggle = new SpruceBooleanOption("config.cheaprename", () -> config.CHEAP_RENAME, newValue -> config.CHEAP_RENAME = newValue, Text.translatable("config.description.cheaprename"));
        this.noXpPenaltyToggle = new SpruceBooleanOption("config.noxppenalty", () -> config.NO_XP_PENALTY, newValue -> config.NO_XP_PENALTY = newValue, Text.translatable("config.description.noxppenalty"));

    }

    @Override
    protected void init() {
        super.init();

        SpruceOptionListWidget list = new SpruceOptionListWidget(Position.of(0, 34), this.width, this.height - 69);
        list.addOptionEntry(this.survivalDebugStickToggle, this.endermanGriefToggle);
        list.addOptionEntry(this.noExpensiveToggle, this.cheapRenameToggle);
        list.addOptionEntry(this.noXpPenaltyToggle, null);

        this.addDrawableChild(list);

        this.addDrawableChild(new SpruceButtonWidget(Position.of(this.width / 2 - 100, this.height - 30), 200, 20, Text.translatable("config.done"), button -> this.applyChanges()));
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