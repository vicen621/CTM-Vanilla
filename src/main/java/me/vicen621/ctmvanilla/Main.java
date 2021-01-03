package me.vicen621.ctmvanilla;

import me.vicen621.ctmvanilla.Commands.Guides;
import me.vicen621.ctmvanilla.Commands.ReloadConfig;
import me.vicen621.ctmvanilla.Commands.Start;
import me.vicen621.ctmvanilla.FastBoard.FastBoard;
import me.vicen621.ctmvanilla.Listeners.Wools;
import me.vicen621.ctmvanilla.Scoreboard.Scoreboard;
import me.vicen621.ctmvanilla.Utils.ConfigFile;
import me.vicen621.ctmvanilla.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class Main extends JavaPlugin {
    public static ConfigFile config;
    public static Boolean started = Boolean.FALSE;
    public static Boolean HardMode = Boolean.FALSE;
    public static Boolean NormalMode = Boolean.FALSE;
    public static Boolean NightmareMode = Boolean.FALSE;
    public static Boolean UHC = Boolean.FALSE;
    public static Boolean Minerals = Boolean.FALSE;
    public static Boolean lose = Boolean.FALSE;
    public static List<Player> Playing = new ArrayList<Player>();

    public static Map<String, FastBoard> boards = new HashMap<>();

    @Override
    public void onEnable() {
        config = new ConfigFile(this);
        started = false;
        new Guides(this);
        new Start(this);
        new Wools(this);
        new ReloadConfig(this);
        Playing.clear();

        getServer().getScheduler().runTaskTimer(this, () -> {
            for (FastBoard board : boards.values()) {
                board.updateTitle(Utils.chat(config.getConfig().getString("scoreboard.title")));
                Scoreboard.update(board);
                if (started) {
                    if (NormalMode && Start.timer.equals(config.getConfig().getString("GameConfig.MaxGameTimeNormalMode"))) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.sendTitle(Utils.chat("&4&lPERDIERON"), Utils.chat("&cExcedieron el tiempo limite"));
                            p.playSound(p.getLocation(), Sound.ENTITY_SKELETON_HORSE_DEATH, 10.0F, 1.0F);
                            started = Boolean.FALSE;
                            lose = Boolean.TRUE;
                        }
                    } else if (HardMode && Start.timer.equals(config.getConfig().getString("GameConfig.MaxGameTimeHardMode"))) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.sendTitle(Utils.chat("&4&lPERDIERON"), Utils.chat("&cExcedieron el tiempo limite"));
                            p.playSound(p.getLocation(), Sound.ENTITY_SKELETON_HORSE_DEATH, 10.0F, 1.0F);
                            started = Boolean.FALSE;
                            lose = Boolean.TRUE;
                        }
                    }
                }
            }
        }, 0L, 5L);
    }

    @Override
    public void onDisable() {
    }
}
