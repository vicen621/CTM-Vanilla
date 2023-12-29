package io.github.vicen621.ctmvanilla.listeners;

import fr.mrmicky.fastboard.adventure.FastBoard;
import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.config.Config;
import io.github.vicen621.ctmvanilla.game.GameManager;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.GameMode;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListeners implements Listener {
    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
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
        board.updateTitle(MINI_MESSAGE.deserialize(config.getScoreboard().title()));
        Main.boards.put(p.getName(), board);

        e.joinMessage(MINI_MESSAGE.deserialize("<aqua>» <gray>" + e.getPlayer().getName()));

        if (gameManager.getGameState() == GameManager.GameState.WAITING)
            p.setStatistic(Statistic.DEATHS, 0);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        e.quitMessage(MINI_MESSAGE.deserialize("<red>« <gray>" + e.getPlayer().getName()));
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        gameManager.incrementDeaths();

        if (gameManager.isUhc())
            p.setGameMode(GameMode.SPECTATOR);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (gameManager.getGameState() == GameManager.GameState.PLAYING)
            return;

        if (e.getEntity() instanceof Player)
            e.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (gameManager.getGameState() == GameManager.GameState.PLAYING)
            return;

        if (e.getDamager() instanceof Player)
            e.setCancelled(true);
    }

    @EventHandler
    public void onDamage(FoodLevelChangeEvent e) {
        if (gameManager.getGameState() == GameManager.GameState.PLAYING)
            return;

        if (e.getEntity() instanceof Player)
            e.setCancelled(true);
    }

    @EventHandler
    public void onDamage(BlockBreakEvent e) {
        if (gameManager.getGameState() == GameManager.GameState.PLAYING)
            return;

        if (e.getPlayer().getGameMode() == GameMode.SURVIVAL)
            e.setCancelled(true);
    }

}
