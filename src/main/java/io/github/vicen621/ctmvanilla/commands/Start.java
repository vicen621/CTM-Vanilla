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

                StringUtils.broadcast("&aNormalMode has been enabled");
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
                StringUtils.broadcast("&aHardMode has been enabled");
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

        //PLAYING:
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


                return true;
            }
        }
        return false;
    }
}
