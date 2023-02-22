package io.github.vicen621.ctmvanilla.game;

import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.Utils.StringUtils;
import io.github.vicen621.ctmvanilla.game.timer.TimerManager;
import io.github.vicen621.ctmvanilla.game.timer.TimerThread;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class GameManager {

    private final Main plugin;

    private final List<UUID> playing;

    private GameState gameState;
    private GameMode gameMode;
    private TimerManager timer;
    private boolean uhc;
    private boolean minerals;

    public GameManager(Main plugin) {
        this.plugin = plugin;

        this.playing = new ArrayList<>();
        this.gameState = GameState.WAITING;
        this.gameMode = GameMode.NORMAL;
        this.uhc = false;
        this.minerals = false;
    }

    public void startGame() {
        Bukkit.broadcastMessage(StringUtils.chat(StringUtils.PREFIX + "&aNormalMode has been enabled"));
        //this.timer = new TimerThread(plugin, );
        //running = true;
        plugin.getGameManager().getPlaying()
                .addAll(Bukkit.getOnlinePlayers().stream()
                        .filter(p -> p.getGameMode() == org.bukkit.GameMode.SURVIVAL)
                        .map(Player::getUniqueId)
                        .toList()
                );

        // Main.started = Boolean.TRUE;
        // Main.NormalMode = Boolean.TRUE;
        if (plugin.getGameManager().isUhc()) {
            // World world = Bukkit.getWorld(Objects.requireNonNull(Main.getConfig().getConfig().getString("world.over")));
            // World nether = Bukkit.getWorld(Objects.requireNonNull(Main.getConfig().getConfig().getString("world.nether")));
            // World end = Bukkit.getWorld(Objects.requireNonNull(Main.getConfig().getConfig().getString("world.end")));
            // world.setGameRule(GameRule.NATURAL_REGENERATION, false);
            // nether.setGameRule(GameRule.NATURAL_REGENERATION, false);
            // end.setGameRule(GameRule.NATURAL_REGENERATION, false);
        }
    }

    public void setGameMode(GameMode mode) {
        this.gameMode = mode;
        plugin.getWoolManager().registerWools(mode);
    }

    public boolean isPlaying(OfflinePlayer p) {
        return playing.contains(p.getUniqueId());
    }


    public enum GameState {
        WAITING,
        PLAYING,
        WON,
        LOSE
    }

    public enum GameMode {
        NORMAL(Main.getInstance().getConfiguration().getGame().maxGameTimeNormalMode()),
        HARD(Main.getInstance().getConfiguration().getGame().maxGameTimeHardMode()),
        NIGHTMARE(0);

        private final int time;

        GameMode(int time) {
            this.time = time;
        }
    }
}
