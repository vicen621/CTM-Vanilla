package io.github.vicen621.ctmvanilla.game;

import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.Utils.StringUtils;
import io.github.vicen621.ctmvanilla.Utils.Utils;
import io.github.vicen621.ctmvanilla.config.Config;
import io.github.vicen621.ctmvanilla.game.timer.TimerManager;
import io.github.vicen621.ctmvanilla.game.wool.Wool;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

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
    private boolean rewards;

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
        StringUtils.broadcast("The game has started. Game mode: &b" + this.gameMode.toString());
        setGameMode(gameMode);
        this.timer = new TimerManager(plugin, this.getGameMode().time);
        getPlaying().addAll(Bukkit.getOnlinePlayers().stream()
                .filter(p -> p.getGameMode() == org.bukkit.GameMode.SURVIVAL)
                .map(Player::getUniqueId)
                .toList()
        );

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
        plugin.getWoolManager().registerWools(mode);
    }

    public void toggleMinerals() {
        Set<Wool> wools = plugin.getWoolManager().getWools();
        Set<Material> materials = plugin.getWoolManager().getMaterials();

        setMinerals(!isMinerals());

        if (isMinerals()) {
            wools.addAll(Arrays.stream(Wool.Minerals.MINERALS).toList());
            materials.addAll(Arrays.stream(Wool.Minerals.MINERALS).map(Wool::getMaterial).toList());
            return;
        }

        Arrays.stream(Wool.Minerals.MINERALS).toList().forEach(wools::remove);
        Arrays.stream(Wool.Minerals.MINERALS).map(Wool::getMaterial).toList().forEach(materials::remove);
        StringUtils.broadcast("Minerals Mode has been " + (plugin.getGameManager().isMinerals() ? "&aenabled" : "&cdisabled"));
    }

    public void toggleUHC() {
        setUhc(!isUhc());
        StringUtils.broadcast("UHC Mode has been " + (plugin.getGameManager().isMinerals() ? "&aenabled" : "&cdisabled"));
    }

    public void toggleRewards() {
        setRewards(!isRewards());
        StringUtils.broadcast("Rewards Mode has been " + (plugin.getGameManager().isMinerals() ? "&aenabled" : "&cdisabled"));
    }

    public boolean isPlaying(OfflinePlayer p) {
        return playing.contains(p.getUniqueId());
    }

    public void lose() {
        setGameState(GameState.LOSE);

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendTitle(StringUtils.chat("&4&lPERDIERON"), StringUtils.chat("&cExcedieron el tiempo limite"));
            p.playSound(p.getLocation(), Sound.ENTITY_SKELETON_HORSE_DEATH, 10.0F, 1.0F);
        }
    }

    public void won() {
        setGameState(GameState.WON);
        for (Player p : Bukkit.getOnlinePlayers())
            p.sendTitle(StringUtils.chat("&a&lYOU WON!!"), StringUtils.chat("&bCongratulations, you completed the monument"));

        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                if (i >= 100)
                    cancel();

                Utils.spawnFirework();
                i++;
            }
        }.runTaskTimerAsynchronously(plugin, 0, 10);
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

        @Override
        public String toString() {
            return WordUtils.capitalizeFully(name());
        }
    }
}
