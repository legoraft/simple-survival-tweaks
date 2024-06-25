package com.survivaltweaks;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.text.Text;

import java.util.Properties;

public class configScreen extends GameOptionsScreen {

    public Screen parent;

    public configScreen(Screen parent) {
        super(parent, null, Text.translatable("config.title"));

        this.parent = parent;
    }

    public CyclingButtonWidget survivalDebugStickToggle;
    public CyclingButtonWidget endermanGriefToggle;
    public CyclingButtonWidget noExpensiveToggle;
    public CyclingButtonWidget cheapRenameToggle;
    public CyclingButtonWidget noXpPenaltyToggle;
    public CyclingButtonWidget phantomMobCap;

    public ButtonWidget doneButton;

    @Override
    protected void init() {
        survivalDebugStickToggle = CyclingButtonWidget.onOffBuilder(config.SURVIVAL_DEBUG_STICK)
                .build(Text.translatable("config.survivaldebugstick"), ((button, value) -> config.SURVIVAL_DEBUG_STICK = !config.SURVIVAL_DEBUG_STICK));

        endermanGriefToggle = CyclingButtonWidget.onOffBuilder(config.NO_ENDERMAN_GRIEF)
                .build(Text.translatable("config.noendermangrief"), (button, value) -> config.NO_ENDERMAN_GRIEF = !config.NO_ENDERMAN_GRIEF);

        noExpensiveToggle = CyclingButtonWidget.onOffBuilder(config.NO_EXPENSIVE)
                .build(Text.translatable("config.notooexpensive"), (button, value) -> config.NO_EXPENSIVE = !config.NO_EXPENSIVE);

        cheapRenameToggle = CyclingButtonWidget.onOffBuilder(config.CHEAP_RENAME)
                .build(Text.translatable("config.cheaprename"), (button, value) -> config.CHEAP_RENAME = !config.CHEAP_RENAME);

        noXpPenaltyToggle = CyclingButtonWidget.onOffBuilder(config.NO_XP_PENALTY)
                .build(Text.translatable("config.noxppenalty"), ((button, value) -> config.NO_XP_PENALTY = !config.NO_XP_PENALTY));

        phantomMobCap = CyclingButtonWidget.onOffBuilder(config.PHANTOM_MOBCAP)
                .build(Text.translatable("config.noxppenalty"), ((button, value) -> config.PHANTOM_MOBCAP = !config.PHANTOM_MOBCAP));

        OptionListWidget optionListWidget = this.addDrawableChild(new OptionListWidget(this.client, this.width, this));

        optionListWidget.addWidgetEntry(survivalDebugStickToggle, endermanGriefToggle);
        optionListWidget.addWidgetEntry(noExpensiveToggle, cheapRenameToggle);
        optionListWidget.addWidgetEntry(noXpPenaltyToggle, phantomMobCap);

        doneButton = ButtonWidget
                .builder(Text.translatable("config.done"), button -> close())
                .dimensions(width / 2 - 100, height - 25, 200, 20)
                .build();

        addDrawableChild(doneButton);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(textRenderer, super.title, width / 2, 12, 0xffffff);
    }

    @Override
    protected void addOptions() {
        super.init();
    }

    @Override
    public void close() {
        assert this.client != null;

        applyChanges();
        this.client.setScreen(this.parent);
    }

    public void applyChanges() {
        config config = survivalTweaks.CONFIG;
        Properties properties = new Properties();
        config.write(properties);
        config.save(survivalTweaks.CONFIG_PATH);
    }

}