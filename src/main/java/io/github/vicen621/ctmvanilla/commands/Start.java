package io.github.vicen621.ctmvanilla.commands;

import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.Utils.StringUtils;
import io.github.vicen621.ctmvanilla.Utils.Utils;
import io.github.vicen621.ctmvanilla.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/*
 * Copyright (c) 2021 Vicen621.
 * All rights reserved.
 */

//TODO
public class Start implements CommandExecutor {

    public static String timer;
    private final Main plugin;
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
        // START: start the game
        if (cmd.getName().equalsIgnoreCase("start")) {
            if (!sender.hasPermission("ctm.op")) {
                sender.sendMessage(StringUtils.chat("You don't have permissions to execute this command"));
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage(StringUtils.chat("&cPlease specify: normal | hardmode"));
                return true;
            }
            if (args.length == 1 && args[0].equalsIgnoreCase("normal")) {
                if (plugin.getGameManager().getGameState() == GameManager.GameState.PLAYING) {
                    sender.sendMessage(StringUtils.chat("&cYou only can do this command one time!"));
                    return true;
                }

                Bukkit.broadcastMessage(StringUtils.chat(StringUtils.PREFIX + "&aNormalMode has been enabled"));
                time(0, 0, 0);
                running = true;
                plugin.getGameManager().getPlaying()
                        .addAll(Bukkit.getOnlinePlayers().stream()
                                .filter(p -> p.getGameMode() == GameMode.SURVIVAL)
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
                return true;
            } else if (args.length == 1 && args[0].equalsIgnoreCase("hardmode") && sender.isOp()) {
                if (plugin.getGameManager().getGameState() == GameManager.GameState.PLAYING) {
                    sender.sendMessage(StringUtils.chat("&cYou only can do this command one time!"));
                    return true;
                }
                Bukkit.broadcastMessage(StringUtils.chat(StringUtils.PREFIX + "&aHardMode has been enabled"));
                time(0, 0, 0);
                running = true;
                plugin.getGameManager().getPlaying()
                        .addAll(Bukkit.getOnlinePlayers().stream()
                                .filter(p -> p.getGameMode() == GameMode.SURVIVAL)
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
                return true;
            }
        }

        // UHC: toggles the uhc game mode
        else if (cmd.getName().equalsIgnoreCase("uhc")) {
            if (!sender.hasPermission("ctm.op")) {
                sender.sendMessage(StringUtils.chat("You don't have permissions to execute this command"));
                return true;
            }
            if (plugin.getGameManager().getGameState() == GameManager.GameState.PLAYING) {
                sender.sendMessage(StringUtils.chat("&cYou can't execute this command during game"));
                return true;
            }

            plugin.getGameManager().setUhc(!plugin.getGameManager().isUhc());
            Bukkit.broadcastMessage(StringUtils.chat(StringUtils.PREFIX + "UHC Mode has been " + (plugin.getGameManager().isUhc() ? "&aenabled" : "&cdisabled")));
            return true;
        }

        // MINERALS: toggles the minerals game mode
        else if (cmd.getName().equalsIgnoreCase("minerals")) {
            if (!sender.hasPermission("ctm.op")) {
                sender.sendMessage(StringUtils.chat("You don't have permissions to execute this command"));
                return true;
            }
            if (plugin.getGameManager().getGameState() == GameManager.GameState.PLAYING) {
                sender.sendMessage(StringUtils.chat("&cYou can't execute this command during game"));
                return true;
            }

            plugin.getGameManager().setMinerals(!plugin.getGameManager().isMinerals());
            Bukkit.broadcastMessage(StringUtils.chat(StringUtils.PREFIX + "Minerals Mode has been " + (plugin.getGameManager().isMinerals() ? "&aenabled" : "&cdisabled")));
            return true;
        }

        // TIMER: resumes, stops and set the timer
        else if (cmd.getName().equalsIgnoreCase("timer") && sender.isOp()) {
            if (args.length == 0) {
                sender.sendMessage(StringUtils.chat("&c/timer <set/stop/resume>"));
                return true;
            }

            if (args[0].equalsIgnoreCase("set")) {
                if (args.length != 4) {
                    sender.sendMessage(StringUtils.chat("&c/timer set <hours> <minutes> <seconds>"));
                    return true;
                }

                if (Utils.isInt(args[1]) && Utils.isInt(args[2]) && Utils.isInt(args[3])) {
                    running = false;
                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            time(Integer.parseInt(args[3]), Integer.parseInt(args[2]), Integer.parseInt(args[1]));
                            running = true;
                            Bukkit.broadcastMessage(StringUtils.chat(StringUtils.PREFIX + "Timer setted to &3" + args[1] + ":" + args[2] + ":" + args[3]));
                        }
                    }.runTaskLater(plugin, 21L);
                } else {
                    sender.sendMessage(StringUtils.chat("&c/timer set <secs> <mins> <hours>"));
                    return true;
                }
            } else if (args[0].equalsIgnoreCase("stop")) {
                running = false;
                Bukkit.broadcastMessage(StringUtils.chat(StringUtils.PREFIX + "Timer stoped"));
            } else if (args[0].equalsIgnoreCase("resume")) {
                String[] parts = timer.split(":");
                int secs = Integer.parseInt(parts[2]);
                int mins = Integer.parseInt(parts[1]);
                int hors = Integer.parseInt(parts[0]);


                time(secs, mins, hors);
                running = true;
                Bukkit.broadcastMessage(StringUtils.chat(StringUtils.PREFIX + "Timer resumed from &3" + hors + ":" + mins + ":" + secs));
                return true;
            }
        }

        //PLAYING: adds or remove players from the Player's list
        else if (cmd.getName().equalsIgnoreCase("Playing") && sender.isOp()) {
            if (args.length == 0) {
                sender.sendMessage(StringUtils.chat("&cUsage: /playing <add/remove> <player>"));
                return true;
            }

            if (args[0].equalsIgnoreCase("add")) {
                if (args.length != 2) {
                    sender.sendMessage(StringUtils.chat(StringUtils.PREFIX + "&cUsage: /playing add <player>"));
                    return true;
                }

                Player p = Bukkit.getPlayer(args[1]);

                if (p == null) {
                    sender.sendMessage(StringUtils.chat(StringUtils.PREFIX + "&cEl jugador debe estar online"));
                    return true;
                }

                if (plugin.getGameManager().isPlaying(p)) {
                    sender.sendMessage(StringUtils.chat(StringUtils.PREFIX + p.getName() + " &cYa esta en la lista de jugadores!"));
                    return true;
                }

                plugin.getGameManager().getPlaying().add(p.getUniqueId());
                sender.sendMessage(StringUtils.chat(StringUtils.PREFIX + "Agregaste a " + p.getName() + " a la Lista de Jugadores"));
                return true;
            } else if (args[0].equalsIgnoreCase("remove")) {
                if (args.length != 2) {
                    sender.sendMessage(StringUtils.chat(StringUtils.PREFIX + "&cUsage: /playing remove <player>"));
                    return true;
                }

                Player p = Bukkit.getPlayer(args[1]);

                if (p == null) {
                    sender.sendMessage(StringUtils.chat(StringUtils.PREFIX + "&cEl jugador debe estar online"));
                    return true;
                }

                if (!plugin.getGameManager().isPlaying(p)) {
                    sender.sendMessage(StringUtils.chat(StringUtils.PREFIX + p.getName() + " &cYa esta fuera de la lista de jugadores!"));
                    return true;
                }

                plugin.getGameManager().getPlaying().remove(p.getUniqueId());
                sender.sendMessage(StringUtils.chat(StringUtils.PREFIX + "Agregaste a " + p.getName() + " a la lista de jugadores!"));
                return true;
            }
        }
        return false;
    }

    public void time(int secs, int mins, int hors) {
        new BukkitRunnable() {
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
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }
}
