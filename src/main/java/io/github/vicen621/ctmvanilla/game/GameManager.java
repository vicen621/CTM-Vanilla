package io.github.vicen621.ctmvanilla.game;

import io.github.vicen621.ctmvanilla.Main;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.OfflinePlayer;

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
        NORMAL,
        HARD,
        NIGHTMARE
    }
}
