package io.github.vicen621.ctmvanilla.commands;

import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.Utils.Utils;
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

/*
 * Copyright (c) 2021 Vicen621.
 * All rights reserved.
 */

public class Start implements CommandExecutor {

    private final Main plugin;
    public static String timer;
    private boolean running = false;

    public Start(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("start").setExecutor(this);
        plugin.getCommand("uhc").setExecutor(this);
        plugin.getCommand("minerals").setExecutor(this);
        plugin.getCommand("timer").setExecutor(this);
        plugin.getCommand("playing").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("start")) {
            if (!sender.hasPermission("ctm.op")) {
                sender.sendMessage(Utils.chat("You don't have permissions to execute this command"));
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage(Utils.chat("&cPlease specify: normal | hardmode"));
                return true;
            }
            if (args.length == 1 && args[0].equalsIgnoreCase("normal")) {
                if (Main.started) {
                    sender.sendMessage(Utils.chat("&cYou only can do this command one time!"));
                    return true;
                }
                Bukkit.broadcastMessage(Utils.chat(Main.Prefix + "&aNormalMode has been enabled"));
                time(0, 0, 0);
                running = true;
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.getGameMode() == GameMode.SURVIVAL) {
                        Main.Playing.add(p.getUniqueId());
                    }
                }
                Main.started = Boolean.TRUE;
                Main.NormalMode = Boolean.TRUE;
                if (Main.UHC) {
                    World world = Bukkit.getWorld(Objects.requireNonNull(Main.config.getConfig().getString("world.over")));
                    World nether = Bukkit.getWorld(Objects.requireNonNull(Main.config.getConfig().getString("world.nether")));
                    World end = Bukkit.getWorld(Objects.requireNonNull(Main.config.getConfig().getString("world.end")));
                    world.setGameRule(GameRule.NATURAL_REGENERATION, false);
                    nether.setGameRule(GameRule.NATURAL_REGENERATION, false);
                    end.setGameRule(GameRule.NATURAL_REGENERATION, false);
                }
                return true;
            } else if (args.length == 1 && args[0].equalsIgnoreCase("hardmode") && sender.isOp()) {
                if (Main.started) {
                    sender.sendMessage(Utils.chat("&cYou only can do this command one time!"));
                    return true;
                }
                Bukkit.broadcastMessage(Utils.chat(Main.Prefix + "&aHardMode has been enabled"));
                time(0, 0, 0);
                running = true;
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.getGameMode() == GameMode.SURVIVAL) {
                        Main.Playing.add(p.getUniqueId());
                    }
                }
                Main.started = Boolean.TRUE;
                Main.HardMode = Boolean.TRUE;
                if (Main.UHC) {
                    World world = Bukkit.getWorld("world");
                    World nether = Bukkit.getWorld("world_nether");
                    World end = Bukkit.getWorld("world_the_end");
                    world.setGameRule(GameRule.NATURAL_REGENERATION, false);
                    nether.setGameRule(GameRule.NATURAL_REGENERATION, false);
                    end.setGameRule(GameRule.NATURAL_REGENERATION, false);
                }
                return true;
            }
        } else if (cmd.getName().equalsIgnoreCase("uhc")) {
            if (!sender.hasPermission("ctm.op")) {
                sender.sendMessage(Utils.chat("You don't have permissions to execute this command"));
                return true;
            }
            if (Main.started) {
                sender.sendMessage(Utils.chat("&cYou can't execute this command during game"));
                return true;
            }
            if (!Main.UHC) {
                Bukkit.broadcastMessage(Utils.chat(Main.Prefix + "&aUHC Mode has been enabled"));
                Main.UHC = Boolean.TRUE;
                return true;
            } else {
                Bukkit.broadcastMessage(Utils.chat(Main.Prefix + "&cUHC Mode has been disabled"));
                Main.UHC = Boolean.FALSE;
                return true;
            }
        } else if (cmd.getName().equalsIgnoreCase("minerals")) {
            if (!sender.hasPermission("ctm.op")) {
                sender.sendMessage(Utils.chat("You don't have permissions to execute this command"));
                return true;
            }
            if (Main.started) {
                sender.sendMessage(Utils.chat("&cYou can't execute this command during game"));
                return true;
            }
            if (!Main.Minerals) {
                Bukkit.broadcastMessage(Utils.chat(Main.Prefix + "&aMinerals Mode has been enabled"));
                Main.Minerals = Boolean.TRUE;
                return true;
            } else {
                Bukkit.broadcastMessage(Utils.chat(Main.Prefix + "&cMinerals Mode has been disabled"));
                Main.Minerals = Boolean.FALSE;
                return true;
            }
        }else if (cmd.getName().equalsIgnoreCase("timer") && sender.isOp()){
            if (args.length == 0){
                sender.sendMessage(Utils.chat("&c/timer <set/stop/resume>"));
                return true;
            }

            if (args[0].equalsIgnoreCase("set")){
                if (args.length != 4){
                    sender.sendMessage(Utils.chat("&c/timer set <hours> <minutes> <seconds>"));
                    return true;
                }

                if (Utils.isInt(args[1]) && Utils.isInt(args[2]) && Utils.isInt(args[3])){
                    running = false;
                    new BukkitRunnable(){

                        @Override
                        public void run() {
                            time(Integer.parseInt(args[3]), Integer.parseInt(args[2]), Integer.parseInt(args[1]));
                            running = true;
                            Bukkit.broadcastMessage(Utils.chat(Main.Prefix + "Timer setted to &3" + args[1] + ":" + args[2] + ":" + args[3]));
                        }
                    }.runTaskLater(plugin, 21L);
                }else{
                    sender.sendMessage(Utils.chat("&c/timer set <secs> <mins> <hours>"));
                    return true;
                }
            }else if (args[0].equalsIgnoreCase("stop")){
                running = false;
                Bukkit.broadcastMessage(Utils.chat(Main.Prefix + "Timer stoped"));
            }else if (args[0].equalsIgnoreCase("resume")){
                String[] parts = timer.split(":");
                int secs = Integer.parseInt(parts[2]);
                int mins = Integer.parseInt(parts[1]);
                int hors = Integer.parseInt(parts[0]);



                time(secs, mins, hors);
                running = true;
                Bukkit.broadcastMessage(Utils.chat(Main.Prefix + "Timer resumed from &3" + hors + ":" + mins + ":" + secs));
                return true;
            }
        }else if (cmd.getName().equalsIgnoreCase("Playing") && sender.isOp()){
            if (args.length == 0){
                sender.sendMessage(Utils.chat("&cUsage: /playing <add/remove> <player>"));
                return true;
            }

            if (args[0].equalsIgnoreCase("add")){
                if (args.length != 2){
                    sender.sendMessage(Utils.chat(Main.Prefix + "&cUsage: /playing add <player>"));
                    return true;
                }

                Player p = Bukkit.getPlayer(args[1]);

                if (p == null){
                    sender.sendMessage(Utils.chat(Main.Prefix + "&cEl jugador debe estar online"));
                    return true;
                }

                if (Main.Playing.contains(p.getUniqueId())){
                    sender.sendMessage(Utils.chat(Main.Prefix + p.getName() + " &cYa esta en la lista de jugadores!"));
                    return true;
                }

                Main.Playing.add(p.getUniqueId());
                sender.sendMessage(Utils.chat(Main.Prefix + "Agregaste a " + p.getName() + " a la Lista de Jugadores"));
                return true;
            } else if (args[0].equalsIgnoreCase("remove")){
                if (args.length != 2){
                    sender.sendMessage(Utils.chat(Main.Prefix + "&cUsage: /playing remove <player>"));
                    return true;
                }

                Player p = Bukkit.getPlayer(args[1]);

                if (p == null){
                    sender.sendMessage(Utils.chat(Main.Prefix + "&cEl jugador debe estar online"));
                    return true;
                }

                if (!Main.Playing.contains(p.getUniqueId())){
                    sender.sendMessage(Utils.chat(Main.Prefix + p.getName() + " &cYa esta fuera de la lista de jugadores!"));
                    return true;
                }

                Main.Playing.remove(p.getUniqueId());
                sender.sendMessage(Utils.chat(Main.Prefix + "Agregaste a " + p.getName() + " a la lista de jugadores!"));
                return true;
            }
        }
        return false;
    }

    public void time(int secs, int mins, int hors) {
        new BukkitRunnable(){
            int sec = secs;
            int min = mins;
            int hor = hors;
            String secc;
            String minn;
            String horr;

            @Override
            public void run() {
                if (running) {
                    if (sec < 10) {
                        secc = "0" + sec;
                    } else if (sec > 9) {
                        secc = "" + sec;
                    }
                    if (min < 10) {
                        minn = "0" + min;
                    } else if (min > 9) {
                        minn = "" + min;
                    }
                    if (hor < 10) {
                        horr = "0" + hor;
                    } else if (sec > 9) {
                        horr = "" + hor;
                    }
                    timer = horr + ":" + minn + ":" + secc;
                    sec = sec + 1;
                    if (sec == 60) {
                        sec = 0;
                        min = min + 1;
                    }
                    if (min == 60) {
                        min = 0;
                        hor = hor + 1;
                    }
                }else{
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }
}
