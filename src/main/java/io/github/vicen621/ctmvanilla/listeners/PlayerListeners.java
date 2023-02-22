package io.github.vicen621.ctmvanilla.listeners;

import fr.mrmicky.fastboard.FastBoard;
import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.Utils.StringUtils;
import io.github.vicen621.ctmvanilla.config.Config;
import io.github.vicen621.ctmvanilla.game.GameManager;
import org.bukkit.GameMode;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListeners implements Listener {
    private final GameManager gameManager;
    private final Config config;

    public PlayerListeners(Main plugin) {
        this.gameManager = plugin.getGameManager();
        this.config = plugin.getConfiguration();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        FastBoard board = new FastBoard(p);
        board.updateTitle(StringUtils.chat(config.getScoreboard().title()));
        Main.boards.put(p.getName(), board);

        if (gameManager.getGameState() == GameManager.GameState.WAITING)
            p.setStatistic(Statistic.DEATHS, 0);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();

        if (gameManager.isUhc())
            p.setGameMode(GameMode.SPECTATOR);
    }
}
