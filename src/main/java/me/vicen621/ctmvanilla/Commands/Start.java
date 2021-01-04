package me.vicen621.ctmvanilla.Commands;

import me.vicen621.ctmvanilla.Main;
import me.vicen621.ctmvanilla.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class Start implements CommandExecutor {

    private Main plugin;
    public static String timer;

    public Start(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("start").setExecutor(this);
        plugin.getCommand("uhc").setExecutor(this);
        plugin.getCommand("minerals").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("start")){
            if (!sender.hasPermission("ctm.op")){
                sender.sendMessage(Utils.chat("You don't have permissions to execute this command"));
                return true;
            }
            if (args.length == 0){
                sender.sendMessage(Utils.chat("&cPlease specify: normal | hardmode"));
                return true;
            }if (args.length == 1 && args[0].equalsIgnoreCase("normal")){
                if (Main.started){
                    sender.sendMessage(Utils.chat("&cYou only can do this command one time!"));
                    return true;
                }
                Bukkit.broadcastMessage(Utils.chat("&aNormalMode has been enabled"));
                time();
                for (Player p : Bukkit.getOnlinePlayers()){
                    if (p.getGameMode() == GameMode.SURVIVAL){
                        Main.Playing.add(p);
                    }
                }
                Main.started = Boolean.TRUE;
                Main.NormalMode = Boolean.TRUE;
                if (Main.UHC){
                    World world = Bukkit.getWorld(Objects.requireNonNull(Main.config.getConfig().getString("world.over")));
                    World nether = Bukkit.getWorld(Objects.requireNonNull(Main.config.getConfig().getString("world.nether")));
                    World end = Bukkit.getWorld(Objects.requireNonNull(Main.config.getConfig().getString("world.end")));
                    world.setGameRule(GameRule.NATURAL_REGENERATION, false);
                    nether.setGameRule(GameRule.NATURAL_REGENERATION, false);
                    end.setGameRule(GameRule.NATURAL_REGENERATION, false);
                }
                return true;
            }else if (args.length == 1 && args[0].equalsIgnoreCase("hardmode")){
                if (Main.started){
                    sender.sendMessage(Utils.chat("&cYou only can do this command one time!"));
                    return true;
                }
                Bukkit.broadcastMessage(Utils.chat("&aHardMode has been enabled"));
                time();
                for (Player p : Bukkit.getOnlinePlayers()){
                    if (p.getGameMode() == GameMode.SURVIVAL){
                        Main.Playing.add(p);
                    }
                }
                Main.started = Boolean.TRUE;
                Main.HardMode = Boolean.TRUE;
                if (Main.UHC){
                    World world = Bukkit.getWorld("world");
                    World nether = Bukkit.getWorld("world_nether");
                    World end = Bukkit.getWorld("world_the_end");
                    world.setGameRule(GameRule.NATURAL_REGENERATION, false);
                    nether.setGameRule(GameRule.NATURAL_REGENERATION, false);
                    end.setGameRule(GameRule.NATURAL_REGENERATION, false);
                }
                return true;
            }
        }
        else if (cmd.getName().equalsIgnoreCase("uhc")){
            if (!sender.hasPermission("ctm.op")){
                sender.sendMessage(Utils.chat("You don't have permissions to execute this command"));
                return true;
            }
            if (Main.started){
                sender.sendMessage(Utils.chat("&cYou can't execute this command during game"));
                return true;
            }
            if (!Main.UHC) {
                Bukkit.broadcastMessage(Utils.chat("&aUHC Mode has been enabled"));
                Main.UHC = Boolean.TRUE;
                return true;
            } else{
                Bukkit.broadcastMessage(Utils.chat("&cUHC Mode has been disabled"));
                Main.UHC = Boolean.FALSE;
                return true;
            }
        }
        else if (cmd.getName().equalsIgnoreCase("minerals")){
            if (!sender.hasPermission("ctm.op")){
                sender.sendMessage(Utils.chat("You don't have permissions to execute this command"));
                return true;
            }
            if (Main.started){
                sender.sendMessage(Utils.chat("&cYou can't execute this command during game"));
                return true;
            }
            if (!Main.Minerals){
                Bukkit.broadcastMessage(Utils.chat("&aMinerals Mode has been enabled"));
                Main.Minerals = Boolean.TRUE;
                return true;
            } else{
                Bukkit.broadcastMessage(Utils.chat("&cMinerals Mode has been disabled"));
                Main.Minerals = Boolean.FALSE;
                return true;
            }
        }
        return false;
    }

    public void time(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            int min = 00;
            int sec = 00;
            int hor = 00;
            String secc;
            String minn;
            String horr;
            @Override
            public void run() {
                if (sec < 10){
                    secc = "0" + sec;
                } else if (sec > 9){
                    secc = "" + sec;
                }
                if (min < 10){
                    minn = "0" + min;
                } else if (min > 9){
                    minn = "" + min;
                }
                if (hor < 10){
                    horr = "0" + hor;
                } else if (sec > 9){
                    horr = "" + hor;
                }
                timer = horr + ":" + minn + ":" + secc;
                sec = sec + 1;
                if (sec == 60){
                    sec = 00;
                    min = min + 1;
                }
                if (min == 60){
                    min = 00;
                    hor = hor + 1;
                }
            }
        },0L, 20L);
    }
}
