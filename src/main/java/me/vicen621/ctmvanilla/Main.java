package me.vicen621.ctmvanilla;

import me.vicen621.ctmvanilla.Commands.Guides;
import me.vicen621.ctmvanilla.Commands.ItemsFaltantes;
import me.vicen621.ctmvanilla.Commands.ReloadConfig;
import me.vicen621.ctmvanilla.Commands.Start;
import me.vicen621.ctmvanilla.Listeners.Chat;
import me.vicen621.ctmvanilla.Listeners.Wools;
import me.vicen621.ctmvanilla.Scoreboard.FastBoard;
import me.vicen621.ctmvanilla.Scoreboard.Scoreboard;
import me.vicen621.ctmvanilla.Utils.ConfigFile;
import me.vicen621.ctmvanilla.Utils.Utils;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.ScoreboardManager;

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
    public static Boolean won = Boolean.FALSE;
    public static List<UUID> Playing = new ArrayList<>();

    public static Map<String, FastBoard> boards = new HashMap<>();

    public static String Prefix = "&#313535[&#0c768bCTM Vanilla&#313535] &#4b5061» &7";

    @Override
    public void onEnable() {
        config = new ConfigFile(this);
        new Chat(this);
        new Guides(this);
        new Start(this);
        new Wools(this);
        new ItemsFaltantes(this);
        new ReloadConfig(this);
        scoreboards();
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
                    if (Minerals) {
                        if (Wools.getObtainedWools() == 16 && Wools.getObtainedMinerals() == 7) {
                            started = Boolean.FALSE;
                            won = Boolean.TRUE;
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                p.sendTitle(Utils.chat("&a&lYOU WON!!"), Utils.chat("&bCongratulations, you completed the monument"));
                                Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
                                FireworkMeta fwm = fw.getFireworkMeta();

                                //Our random generator
                                Random r = new Random();

                                //Get the type
                                int rt = r.nextInt(5) + 1;
                                FireworkEffect.Type type = FireworkEffect.Type.BALL;
                                if (rt == 1) type = FireworkEffect.Type.BALL;
                                if (rt == 2) type = FireworkEffect.Type.BALL_LARGE;
                                if (rt == 3) type = FireworkEffect.Type.BURST;
                                if (rt == 4) type = FireworkEffect.Type.CREEPER;
                                if (rt == 5) type = FireworkEffect.Type.STAR;

                                //Get our random colours
                                int r1i = r.nextInt(17) + 1;
                                int r2i = r.nextInt(17) + 1;
                                Color c1 = Utils.getColor(r1i);
                                Color c2 = Utils.getColor(r2i);

                                //Create our effect with this
                                FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();

                                //Then apply the effect to the meta
                                fwm.addEffect(effect);

                                //Generate some random power and set it
                                int rp = r.nextInt(2) + 1;
                                fwm.setPower(rp);

                                //Then apply this to our rocket
                                fw.setFireworkMeta(fwm);
                            }
                        }
                    } else {
                        if (Wools.getObtainedWools() == 16) {
                            started = Boolean.FALSE;
                            won = Boolean.TRUE;
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                p.sendTitle(Utils.chat("&a&lYOU WON!!"), Utils.chat("&bCongratulations, you completed the monument"));
                                Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
                                FireworkMeta fwm = fw.getFireworkMeta();

                                //Our random generator
                                Random r = new Random();

                                //Get the type
                                int rt = r.nextInt(5) + 1;
                                FireworkEffect.Type type = FireworkEffect.Type.BALL;
                                if (rt == 1) type = FireworkEffect.Type.BALL;
                                if (rt == 2) type = FireworkEffect.Type.BALL_LARGE;
                                if (rt == 3) type = FireworkEffect.Type.BURST;
                                if (rt == 4) type = FireworkEffect.Type.CREEPER;
                                if (rt == 5) type = FireworkEffect.Type.STAR;

                                //Get our random colours
                                int r1i = r.nextInt(17) + 1;
                                int r2i = r.nextInt(17) + 1;
                                Color c1 = Utils.getColor(r1i);
                                Color c2 = Utils.getColor(r2i);

                                //Create our effect with this
                                FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();

                                //Then apply the effect to the meta
                                fwm.addEffect(effect);

                                //Generate some random power and set it
                                int rp = r.nextInt(2) + 1;
                                fwm.setPower(rp);

                                //Then apply this to our rocket
                                fw.setFireworkMeta(fwm);
                            }
                        }
                    }
                }
            }
        }, 0L, 5L);
    }

    private void scoreboards(){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        assert manager != null;
        org.bukkit.scoreboard.Scoreboard scoreboard = manager.getMainScoreboard();

        if (scoreboard.getObjective("hp") == null){
            Objective objective = scoreboard.registerNewObjective("hp", "health", Utils.chat("&c❤"));
            objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        }
        if (scoreboard.getObjective("vida") == null) {
            Objective objective1 = scoreboard.registerNewObjective("vida", "health");
            objective1.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        }
    }
}
