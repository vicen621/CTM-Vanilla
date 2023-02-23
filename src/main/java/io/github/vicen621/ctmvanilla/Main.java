package io.github.vicen621.ctmvanilla;

import co.aikar.commands.MessageType;
import co.aikar.commands.PaperCommandManager;
import de.exlll.configlib.ConfigLib;
import de.exlll.configlib.NameFormatters;
import de.exlll.configlib.YamlConfigurationProperties;
import de.exlll.configlib.YamlConfigurationStore;
import fr.mrmicky.fastboard.FastBoard;
import io.github.rysefoxx.inventory.plugin.pagination.InventoryManager;
import io.github.vicen621.ctmvanilla.Utils.StringUtils;
import io.github.vicen621.ctmvanilla.commands.CTMVanillaCommand;
import io.github.vicen621.ctmvanilla.commands.Start;
import io.github.vicen621.ctmvanilla.config.Config;
import io.github.vicen621.ctmvanilla.game.GameManager;
import io.github.vicen621.ctmvanilla.game.wool.WoolManager;
import io.github.vicen621.ctmvanilla.hooks.PlaceHolderAPIHook;
import io.github.vicen621.ctmvanilla.listeners.ChatListener;
import io.github.vicen621.ctmvanilla.listeners.PlayerListeners;
import io.github.vicen621.ctmvanilla.listeners.WoolsListeners;
import io.github.vicen621.ctmvanilla.scoreboard.Scoreboard;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.ScoreboardManager;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/*
 * Copyright (c) 2021 Vicen621.
 * All rights reserved.
 */

@Getter
public final class Main extends JavaPlugin {

    public static Map<String, FastBoard> boards = new HashMap<>();
    @Getter
    private static Main instance;
    @Getter(AccessLevel.NONE)
    private YamlConfigurationStore<Config> configStore;
    private Config configuration;
    private GameManager gameManager;
    private WoolManager woolManager;
    private PaperCommandManager cmdManager;
    private InventoryManager invManager;

    @Override
    public void onEnable() {
        instance = this;

        loadConfigFile();
        loadManagers();
        loadHooks();
        loadListeners();
        scoreboards();

        new Start(this);

        getServer().getScheduler().runTaskTimerAsynchronously(this, () -> {
            for (FastBoard board : boards.values()) {
                // board.updateTitle(StringUtils.chat(configuration.getScoreboard().title()));
                Scoreboard.update(board);
            }

            // TODO Fijarse si hay alguna manera mas eficiente de fijarse si ganaron,
            //  se puede hacer que cuando se agarra una lana, si es la ultima se ejecute
            //  para fijarse si pierden que sea cuando el timer llegue a x tiempo, fijarse en el thread del timer
            /*if (started) {
                if (NormalMode && Start.timer.equals(config.getConfig().getString("GameConfig.MaxGameTimeNormalMode"))) {

                } else if (HardMode && Start.timer.equals(config.getConfig().getString("GameConfig.MaxGameTimeHardMode"))) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.sendTitle(StringUtils.chat("&4&lPERDIERON"), StringUtils.chat("&cExcedieron el tiempo limite"));
                        p.playSound(p.getLocation(), Sound.ENTITY_SKELETON_HORSE_DEATH, 10.0F, 1.0F);
                        started = Boolean.FALSE;
                        lose = Boolean.TRUE;
                    }
                }

                if (WoolsListeners.getObtainedWools() == 16 && WoolsListeners.getObtainedMinerals() == 7) {

            }*/
        }, 0L, 5L);
    }

    private void loadConfigFile() {
        YamlConfigurationProperties properties = ConfigLib.BUKKIT_DEFAULT_PROPERTIES.toBuilder()
                .header("""
                                                Plugin made by:
                         ██╗░░░██╗██╗░█████╗░███████╗███╗░░██╗░█████╗░██████╗░░░███╗░░
                         ██║░░░██║██║██╔══██╗██╔════╝████╗░██║██╔═══╝░╚════██╗░████║░░
                         ╚██╗░██╔╝██║██║░░╚═╝█████╗░░██╔██╗██║██████╗░░░███╔═╝██╔██║░░
                         ░╚████╔╝░██║██║░░██╗██╔══╝░░██║╚████║██╔══██╗██╔══╝░░╚═╝██║░░
                         ░░╚██╔╝░░██║╚█████╔╝███████╗██║░╚███║╚█████╔╝███████╗███████╗
                         ░░░╚═╝░░░╚═╝░╚════╝░╚══════╝╚═╝░░╚══╝░╚════╝░╚══════╝╚══════╝
                                                
                        You can use any Placeholder API placeholder
                        Also this plugin adds this placeholders to the Placeholder API plugin:
                          %ctmv_gamemode%: Shows the current gamemode
                          %ctmv_obtained_wools%: Displays your currents wools
                          %ctmv_time_Played%: Displays the time played
                          %ctmv_total_deaths%: Displays the deaths of all teams
                        """)
                .setNameFormatter(NameFormatters.LOWER_UNDERSCORE)
                .addSerializer(World.class, new Config.WorldToStringSerializer())
                .build();

        Path configFile = new File(getDataFolder(), "config.yml").toPath();
        configStore = new YamlConfigurationStore<>(Config.class, properties);
        configuration = configStore.update(configFile);
    }

    private void loadManagers() {
        gameManager = new GameManager(this);
        woolManager = new WoolManager(this);
        invManager = new InventoryManager(this);
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

    public void updateConfig() {
        Path configFile = new File(getDataFolder(), "config.yml").toPath();
        configuration = configStore.update(configFile);
    }

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
            Objective objective = scoreboard.registerNewObjective("hp", "health", StringUtils.chat("&c❤"));
            objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        }
        if (scoreboard.getObjective("vida") == null) {
            Objective objective1 = scoreboard.registerNewObjective("vida", "health");
            objective1.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        }
    }
}
