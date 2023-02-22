package io.github.vicen621.ctmvanilla.commands;

import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.Utils.StringUtils;
import io.github.vicen621.ctmvanilla.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
 * Copyright (c) 2021 Vicen621.
 * All rights reserved.
 */

//TODO
public class tl implements CommandExecutor {

    private final Main plugin;

    public tl(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("tl").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tl") && sender instanceof Player p){
            if (!plugin.getGameManager().isPlaying(p)){
                StringUtils.sendMessage(p, "&cEste comando solo puede ser usado por los jugadores!");
                return true;
            }

            if (args.length == 0){

                String prefix = StringUtils.chat("&#313535[&bTL&#313535] &#4b5061» ");
                String format = "&3&o{name}&8&o: &7X: &a{x} &7Y: &a{y} &7Z: &a{z} &8(&c{dimention}&8)";

                Location loc = p.getLocation();

                format = format.replace("{name}", p.getName());
                format = format.replace("{x}", "" + loc.getBlockX());
                format = format.replace("{y}", "" + loc.getBlockY());
                format = format.replace("{z}", "" + loc.getBlockZ());
                format = format.replace("{dimention}", environment(p.getWorld()));

                Bukkit.broadcastMessage(StringUtils.chat(prefix + format));
                return true;
            }else {
                StringBuilder message = new StringBuilder();
                for (String part : args) {
                    if (!message.toString().equals(""))
                        message.append(" ");
                    message.append(part);
                }

                String prefix = StringUtils.chat("&#313535[&bTL&#313535] &#4b5061» ");
                String format = "&3&o{name}&8&o: &7X: &a{x} &7Y: &a{y} &7Z: &a{z} &8(&c{dimention}&8)";

                Location loc = p.getLocation();

                format = format.replace("{name}", p.getName());
                format = format.replace("{x}", "" + loc.getBlockX());
                format = format.replace("{y}", "" + loc.getBlockY());
                format = format.replace("{z}", "" + loc.getBlockZ());
                format = format.replace("{dimention}", environment(p.getWorld()));

                Bukkit.broadcastMessage(StringUtils.chat(prefix + "&f" + message + " &7| " + format));
                return true;
            }
        }
        return false;
    }

    private String environment(World world) {
        switch (world.getEnvironment()) {
            case NORMAL:
                return "Overworld";
            case NETHER:
                return "Nether";
            case THE_END:
                return "The End";
            default:
                return "Unknown";
        }
    }
}
