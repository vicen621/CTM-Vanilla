package io.github.vicen621.ctmvanilla.game.timer;

import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.Utils.StringUtils;
import org.bukkit.Bukkit;

public class TimerManager {

    private final Main plugin;
    private final TimerThread timer;

    public TimerManager(Main plugin, int time) {
        this.plugin = plugin;
        this.timer = new TimerThread(plugin, time);
    }

    public void startTimer() {
        if (this.timer.isStopped())
            this.timer.setStopped(false);

        Bukkit.getScheduler().runTaskAsynchronously(plugin, timer);
    }

    public void stopTimer() {
        if (!this.timer.isStopped())
            this.timer.setStopped(true);
    }

    public void resetTimer() {
        stopTimer();
        this.timer.setCurrentTime(0);
    }

    public void setTime(int seconds) {
        this.timer.setCurrentTime(seconds);
    }

    public int getCurrentTime() {
        return timer.getCurrentTime();
    }

    public String getCurrentTimeFormatted() {
        return StringUtils.formatTime(getCurrentTime());
    }

    public int getRemainingTime() {
        return timer.getTotalTime() - timer.getCurrentTime();
    }

    public String getRemainingTimeFormatted() {
        return StringUtils.formatTime(getRemainingTime());
    }
}
