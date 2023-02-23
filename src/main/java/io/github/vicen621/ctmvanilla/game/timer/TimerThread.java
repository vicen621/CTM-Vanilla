package io.github.vicen621.ctmvanilla.game.timer;

import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.game.GameManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;

@Getter
@Setter
public class TimerThread implements Runnable {

    private final Main plugin;
    private final GameManager gameManager;

    private final int totalTime;
    private int currentTime;
    private boolean stopped;

    /**
     * @param totalTime total time in seconds
     */
    public TimerThread(Main plugin, int totalTime) {
        this.plugin = plugin;
        this.gameManager = plugin.getGameManager();
        this.totalTime = totalTime * 60;
        this.currentTime = 0;
        this.stopped = false;
    }

    @Override
    public void run() {
        if (gameManager.getGameState() != GameManager.GameState.PLAYING || stopped)
            return;

        currentTime++;

        if (currentTime >= totalTime)
            gameManager.lose();

        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, this, 20);
    }
}
