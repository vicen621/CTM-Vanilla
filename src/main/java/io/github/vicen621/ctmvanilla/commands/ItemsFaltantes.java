package io.github.vicen621.ctmvanilla.commands;

import io.github.vicen621.ctmvanilla.listeners.Wools;
import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/*
 * Copyright (c) 2021 Vicen621.
 * All rights reserved.
 */

public class ItemsFaltantes implements CommandExecutor {
    private final Main plugin;

    public ItemsFaltantes(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("wools").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("wools")) {
            if (Main.started) {
                sender.sendMessage(Utils.chat("&5------------ &dMissing Items &5------------"));
                if (Main.NormalMode) {
                    if (!Wools.whiteWool) {
                        sender.sendMessage(Utils.chat("&5- &dCake"));
                    }
                    if (!Wools.orangeWool) {
                        sender.sendMessage(Utils.chat("&5- &dDamaged Anvil"));
                    }
                    if (!Wools.magentaWool) {
                        sender.sendMessage(Utils.chat("&5- &dCobweb"));
                    }
                    if (!Wools.lightBlueWool) {
                        sender.sendMessage(Utils.chat("&5- &dBeetroot Soup"));
                    }
                    if (!Wools.yellowWool) {
                        sender.sendMessage(Utils.chat("&5- &dObserver"));
                    }
                    if (!Wools.limeWool) {
                        sender.sendMessage(Utils.chat("&5- &dExperience Bottle"));
                    }
                    if (!Wools.pinkWool) {
                        sender.sendMessage(Utils.chat("&5- &dEnder Chest"));
                    }
                    if (!Wools.grayWool) {
                        sender.sendMessage(Utils.chat("&5- &dNautilus Shell"));
                    }
                    if (!Wools.lightGrayWool) {
                        sender.sendMessage(Utils.chat("&5- &dDiamond Block"));
                    }
                    if (!Wools.cyanWool) {
                        sender.sendMessage(Utils.chat("&5- &dTotem Of Undying"));
                    }
                    if (!Wools.purpleWool) {
                        sender.sendMessage(Utils.chat("&5- &dMusic Disc 11"));
                    }
                    if (!Wools.blueWool) {
                        sender.sendMessage(Utils.chat("&5- &dSponge"));
                    }
                    if (!Wools.brownWool) {
                        sender.sendMessage(Utils.chat("&5- &dBee Nest"));
                    }
                    if (!Wools.greenWool) {
                        sender.sendMessage(Utils.chat("&5- &dEnd Crystal"));
                    }
                    if (!Wools.redWool) {
                        sender.sendMessage(Utils.chat("&5- &dDragon Egg"));
                    }
                    if (!Wools.blackWool) {
                        sender.sendMessage(Utils.chat("&5- &dTipped Arrow"));
                    }
                    if (!Main.Minerals) {
                        sender.sendMessage(Utils.chat("&5---------------------------------------"));
                        return true;
                    }
                }
                if (Main.HardMode) {
                    if (!Wools.whiteWool) {
                        sender.sendMessage(Utils.chat("&5- &dCake"));
                    }
                    if (!Wools.orangeWool) {
                        sender.sendMessage(Utils.chat("&5- &dDamaged Anvil"));
                    }
                    if (!Wools.magentaWool) {
                        sender.sendMessage(Utils.chat("&5- &dRabbit Stew"));
                    }
                    if (!Wools.lightBlueWool) {
                        sender.sendMessage(Utils.chat("&5- &dObserver"));
                    }
                    if (!Wools.yellowWool) {
                        sender.sendMessage(Utils.chat("&5- &dHeart Of The Sea"));
                    }
                    if (!Wools.limeWool) {
                        sender.sendMessage(Utils.chat("&5- &dSkull Banner Pattern"));
                    }
                    if (!Wools.pinkWool) {
                        sender.sendMessage(Utils.chat("&5- &dEnder Chest"));
                    }
                    if (!Wools.grayWool) {
                        sender.sendMessage(Utils.chat("&5- &dTrident"));
                    }
                    if (!Wools.lightGrayWool) {
                        sender.sendMessage(Utils.chat("&5- &dTotem Of Undying"));
                    }
                    if (!Wools.cyanWool) {
                        sender.sendMessage(Utils.chat("&5- &dMusic Disc 11"));
                    }
                    if (!Wools.purpleWool) {
                        sender.sendMessage(Utils.chat("&5- &dSponge"));
                    }
                    if (!Wools.blueWool) {
                        sender.sendMessage(Utils.chat("&5- &dBee Nest"));
                    }
                    if (!Wools.brownWool) {
                        sender.sendMessage(Utils.chat("&5- &dEnd Crystal"));
                    }
                    if (!Wools.greenWool) {
                        sender.sendMessage(Utils.chat("&5- &dDragon Egg"));
                    }
                    if (!Wools.redWool) {
                        sender.sendMessage(Utils.chat("&5- &dTipped Arrow"));
                    }
                    if (!Wools.blackWool) {
                        sender.sendMessage(Utils.chat("&5- &dCyan Shulker Box"));
                    }
                    if (!Main.Minerals) {
                        sender.sendMessage(Utils.chat("&5---------------------------------------"));
                        return true;
                    }
                }
                if (Main.Minerals) {
                    if (!Wools.Coal) {
                        sender.sendMessage(Utils.chat("&5- &0Coal Block"));
                    }
                    if (!Wools.Iron) {
                        sender.sendMessage(Utils.chat("&5- &7Iron Block"));
                    }
                    if (!Wools.Gold) {
                        sender.sendMessage(Utils.chat("&5- &6Gold Block"));
                    }
                    if (!Wools.Lapis) {
                        sender.sendMessage(Utils.chat("&5- &9Lapis Lazuli Block"));
                    }
                    if (!Wools.Redstone) {
                        sender.sendMessage(Utils.chat("&5- &cRedstone Block"));
                    }
                    if (!Wools.Emerald) {
                        sender.sendMessage(Utils.chat("&5- &aEmerald Block"));
                    }
                    if (!Wools.Diamond) {
                        sender.sendMessage(Utils.chat("&5- &bDiamond Block"));
                    }
                    sender.sendMessage(Utils.chat("&5---------------------------------------"));
                    return true;
                }
            }
            sender.sendMessage(Utils.chat("&cNo game running, please start the game to see the missing items"));
            return true;
        }
        return false;
    }
}
