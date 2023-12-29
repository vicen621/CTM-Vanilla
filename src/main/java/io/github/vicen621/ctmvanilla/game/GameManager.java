package io.github.vicen621.ctmvanilla.game;

import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.Utils.StringUtils;
import io.github.vicen621.ctmvanilla.Utils.Utils;
import io.github.vicen621.ctmvanilla.game.timer.TimerManager;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@SuppressWarnings("deprecation")
public class GameManager {

    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    private final Main plugin;

    private final List<UUID> playing;

    private GameState gameState;
    private GameMode gameMode;
    private TimerManager timer;
    private boolean uhc;
    private boolean minerals;
    private boolean rewards;
    private int deaths;

    public GameManager(Main plugin) {
        this.plugin = plugin;

        this.playing = new ArrayList<>();
        this.gameState = GameState.WAITING;
        this.gameMode = GameMode.NORMAL;
        this.uhc = false;
        this.minerals = false;
        this.rewards = false;
    }

    public void startGame(GameMode gameMode) {
        setGameMode(gameMode);
        StringUtils.broadcast("The game has started. Game mode: <aqua>" + WordUtils.capitalizeFully(gameMode.name()));
        this.timer = new TimerManager(plugin, gameMode.time);
        this.timer.startTimer();
        getPlaying().addAll(
                Bukkit.getOnlinePlayers().stream()
                        .filter(p -> p.getGameMode() == org.bukkit.GameMode.SURVIVAL)
                        .map(Player::getUniqueId)
                        .toList()
        );

        getPlaying().stream()
                .map(Bukkit::getPlayer)
                .filter(Objects::nonNull).forEach(p -> {
                    p.setStatistic(Statistic.DEATHS, 0);
                    p.setFoodLevel(20);
                    p.setSaturation(100);
                    p.setHealth(p.getMaxHealth());
                });

        setGameState(GameState.PLAYING);
    }

    public void resetGame() {
        this.timer.resetTimer();
        plugin.getWoolManager().resetWools();
        getPlaying().clear();
        setGameState(GameState.WAITING);
        setGameMode(GameMode.NORMAL);
        setUhc(false);
        setMinerals(false);
        setRewards(false);
    }

    public void setGameMode(GameMode mode) {
        this.gameMode = mode;
        Bukkit.getLogger().info(mode.name());
        plugin.getWoolManager().registerWools(gameMode, isMinerals());
    }

    public void incrementDeaths() {
        this.deaths++;
    }

    public void toggleMinerals() {
        setMinerals(!isMinerals());
        StringUtils.broadcast("Minerals Mode has been " + (plugin.getGameManager().isMinerals() ? "<green>enabled" : "<red>disabled"));
    }

    public void toggleUHC() {
        setUhc(!isUhc());
        StringUtils.broadcast("UHC Mode has been " + (plugin.getGameManager().isUhc() ? "<green>enabled" : "<red>disabled"));
    }

    public void toggleRewards() {
        setRewards(!isRewards());
        StringUtils.broadcast("Rewards Mode has been " + (plugin.getGameManager().isRewards() ? "<green>enabled" : "<red>disabled"));
    }

    public boolean isPlaying(OfflinePlayer p) {
        return playing.contains(p.getUniqueId());
    }

    public void lose() {
        setGameState(GameState.LOSE);

        Title title = Title.title(
                MINI_MESSAGE.deserialize("<red><bold>PERDIERON"),
                MINI_MESSAGE.deserialize("<red>Excedieron el tiempo limite")
        );

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.showTitle(title);
            p.playSound(p.getLocation(), Sound.ENTITY_SKELETON_HORSE_DEATH, 10.0F, 1.0F);
        }
    }

    public void won() {
        setGameState(GameState.WON);

        Title title = Title.title(
                MINI_MESSAGE.deserialize("<gold>VICTORY"),
                MINI_MESSAGE.deserialize("<white>Congratulations everyone")
        );

        for (Player p : Bukkit.getOnlinePlayers())
            p.showTitle(title);

        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                if (i >= 100)
                    cancel();

                Utils.spawnFirework();
                i++;
            }
        }.runTaskTimer(plugin, 0, 10);
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
