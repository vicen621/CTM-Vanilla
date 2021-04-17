/*
 * Copyright (c) 2021. Vicen621
 * All rights reserved.
 */

package me.vicen621.ctmvanilla.Commands;

import me.vicen621.ctmvanilla.Main;
import me.vicen621.ctmvanilla.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadConfig implements CommandExecutor {

    private final Main plugin;

    public ReloadConfig(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("ctmvanilla").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ctmvanilla")) {
            if (args.length == 0) {
                sender.sendMessage(Utils.chat("&c/ctmvanilla reload"));
                return true;
            }
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                Main.config.reloadConfig();
                sender.sendMessage(Utils.chat(Main.Prefix + "&aReload Complete"));
            }
        }
        return false;
    }
}
