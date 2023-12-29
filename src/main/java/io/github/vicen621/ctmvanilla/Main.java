package io.github.vicen621.ctmvanilla;

import co.aikar.commands.MessageType;
import co.aikar.commands.PaperCommandManager;
import fr.mrmicky.fastboard.adventure.FastBoard;
import io.github.rysefoxx.inventory.plugin.pagination.InventoryManager;
import io.github.vicen621.ctmvanilla.commands.CTMVanillaCommand;
import io.github.vicen621.ctmvanilla.config.Config;
import io.github.vicen621.ctmvanilla.config.ConfigManager;
import io.github.vicen621.ctmvanilla.game.GameManager;
import io.github.vicen621.ctmvanilla.game.wool.WoolManager;
import io.github.vicen621.ctmvanilla.hooks.PlaceHolderAPIHook;
import io.github.vicen621.ctmvanilla.listeners.ChatListener;
import io.github.vicen621.ctmvanilla.listeners.PlayerListeners;
import io.github.vicen621.ctmvanilla.listeners.WoolsListeners;
import io.github.vicen621.ctmvanilla.scoreboard.Scoreboard;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.Map;

/*
 * Copyright (c) 2021 Vicen621.
 * All rights reserved.
 */

@Getter
public final class Main extends JavaPlugin {

    public static final Map<String, FastBoard> boards = new HashMap<>();

    @Getter
    private static Main instance;
    private ConfigManager<Config> configManager;
    private GameManager gameManager;
    private WoolManager woolManager;
    private PaperCommandManager cmdManager;
    private InventoryManager invManager;

    @Override
    public void onEnable() {
        instance = this;
        configManager = new ConfigManager<>(this, "config.yml", Config.class);

        loadManagers();
        loadHooks();
        commands();
        loadListeners();
        scoreboards();

        getServer().getScheduler().runTaskTimerAsynchronously(this, () -> {
            for (FastBoard board : boards.values())
                Scoreboard.update(board);
        }, 0L, 5L);
    }

    private void loadManagers() {
        gameManager = new GameManager(this);
        woolManager = new WoolManager(this);
        invManager = new InventoryManager(this);
        invManager.invoke();
    }

    private void loadHooks() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
            new PlaceHolderAPIHook(this).register();
    }

    private void loadListeners() {
        new ChatListener(this);
        new PlayerListeners(this);
        new WoolsListeners(this);
    }


    @SuppressWarnings("deprecation")
    private void commands() {
        cmdManager = new PaperCommandManager(getInstance());
        cmdManager.enableUnstableAPI("help");

        cmdManager.registerDependency(GameManager.class, this.getGameManager());
        cmdManager.registerDependency(WoolManager.class, this.getWoolManager());

        cmdManager.setFormat(MessageType.HELP, ChatColor.DARK_AQUA, ChatColor.AQUA, ChatColor.GRAY, ChatColor.DARK_GRAY);
        cmdManager.setFormat(MessageType.INFO, ChatColor.DARK_AQUA, ChatColor.AQUA, ChatColor.GRAY, ChatColor.DARK_GRAY);
        cmdManager.setFormat(MessageType.SYNTAX, ChatColor.DARK_AQUA, ChatColor.AQUA, ChatColor.GRAY, ChatColor.DARK_GRAY);

        cmdManager.registerCommand(new CTMVanillaCommand());
    }

    private void scoreboards() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard scoreboard = manager.getMainScoreboard();

        if (scoreboard.getObjective("hp") == null) {
            Objective objective = scoreboard.registerNewObjective("hp", Criteria.HEALTH, MiniMessage.miniMessage().deserialize("<red>❤"));
            objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        }

        if (scoreboard.getObjective("hpTab") == null) {
            Objective objective = scoreboard.registerNewObjective("hpTab", Criteria.HEALTH, Component.empty());
            objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);
            objective.setRenderType(RenderType.HEARTS);
        }
    }

    public Config getConfiguration() {
        return configManager.getConfig();
    }
}
