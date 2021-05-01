package me.vicen621.ctmvanilla.Listeners;

import me.vicen621.ctmvanilla.Main;
import me.vicen621.ctmvanilla.Scoreboard.FastBoard;
import me.vicen621.ctmvanilla.Utils.Utils;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

/*
 * Copyright (c) 2021 Vicen621.
 * All rights reserved.
 */

public class Wools implements Listener {
    private final Main plugin;

    public static Boolean whiteWool = Boolean.FALSE;
    public static Boolean orangeWool = Boolean.FALSE;
    public static Boolean magentaWool = Boolean.FALSE;
    public static Boolean lightBlueWool = Boolean.FALSE;
    public static Boolean yellowWool = Boolean.FALSE;
    public static Boolean limeWool = Boolean.FALSE;
    public static Boolean pinkWool = Boolean.FALSE;
    public static Boolean grayWool = Boolean.FALSE;
    public static Boolean lightGrayWool = Boolean.FALSE;
    public static Boolean cyanWool = Boolean.FALSE;
    public static Boolean purpleWool = Boolean.FALSE;
    public static Boolean blueWool = Boolean.FALSE;
    public static Boolean brownWool = Boolean.FALSE;
    public static Boolean greenWool = Boolean.FALSE;
    public static Boolean redWool = Boolean.FALSE;
    public static Boolean blackWool = Boolean.FALSE;


    public static Boolean Coal = Boolean.FALSE;
    public static Boolean Iron = Boolean.FALSE;
    public static Boolean Gold = Boolean.FALSE;
    public static Boolean Lapis = Boolean.FALSE;
    public static Boolean Redstone = Boolean.FALSE;
    public static Boolean Emerald = Boolean.FALSE;
    public static Boolean Diamond = Boolean.FALSE;

    public Wools(Main plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        FastBoard board = new FastBoard(p);
        board.updateTitle(Utils.chat(Main.config.getConfig().getString("scoreboard.title")));
        Main.boards.put(p.getName(), board);

        if (!Main.started) {
            p.setStatistic(Statistic.DEATHS, 0);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        Player p = (Player) e.getWhoClicked();
        if (Main.started) {

            if (item == null || item.getType() == Material.AIR) return;

            if (Main.NormalMode) {
                if (item.getType() == Material.CAKE) {
                    if (whiteWool) return;
                    whiteWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lWhite Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.DAMAGED_ANVIL) {
                    if (orangeWool) return;
                    orangeWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lOrange Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.COBWEB) {
                    if (magentaWool) return;
                    magentaWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lMagenta Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.BEETROOT_SOUP) {
                    if (lightBlueWool) return;
                    lightBlueWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lLight Blue Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.OBSERVER) {
                    if (yellowWool) return;
                    yellowWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lYellow Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.EXPERIENCE_BOTTLE) {
                    if (limeWool) return;
                    limeWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lLime Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.ENDER_CHEST) {
                    if (pinkWool) return;
                    pinkWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lPink Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.NAUTILUS_SHELL) {
                    if (grayWool) return;
                    grayWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lGray Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.DIAMOND_BLOCK) {
                    if (lightGrayWool) return;
                    lightGrayWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lLight Gray Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.TOTEM_OF_UNDYING) {
                    if (cyanWool) return;
                    cyanWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lCyan Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.MUSIC_DISC_11) {
                    if (purpleWool) return;
                    purpleWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lPurple Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.SPONGE) {
                    if (blueWool) return;
                    blueWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lBlue Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.BEE_NEST) {
                    if (brownWool) return;
                    brownWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lBrown Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.END_CRYSTAL) {
                    if (greenWool) return;
                    greenWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lGreen Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.DRAGON_EGG) {
                    if (redWool) return;
                    redWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lRed Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.TIPPED_ARROW) {
                    if (blackWool) return;
                    blackWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lBlack Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (Main.Minerals) {
                    if (item.getType() == Material.COAL_BLOCK) {
                        if (Coal) return;
                        Coal = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lCoal Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.IRON_BLOCK) {
                        if (Iron) return;
                        Iron = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lIron Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.GOLD_BLOCK) {
                        if (Gold) return;
                        Gold = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lGold Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.LAPIS_BLOCK) {
                        if (Lapis) return;
                        Lapis = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lLapis Lazuli Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.REDSTONE_BLOCK) {
                        if (Redstone) return;
                        Redstone = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lRedstone Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.EMERALD_BLOCK) {
                        if (Emerald) return;
                        Emerald = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lEmerald Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.DIAMOND_BLOCK) {
                        if (Diamond) return;
                        Diamond = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lDiamond Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                }
            } else if (Main.HardMode) {
                if (item.getType() == Material.CAKE) {
                    if (whiteWool) return;
                    whiteWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lWhite Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.DAMAGED_ANVIL) {
                    if (orangeWool) return;
                    orangeWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lOrange Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.RABBIT_STEW) {
                    if (magentaWool) return;
                    magentaWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lMagenta Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.OBSERVER) {
                    if (lightBlueWool) return;
                    lightBlueWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lLight Blue Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.HEART_OF_THE_SEA) {
                    if (yellowWool) return;
                    yellowWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lYellow Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.SKULL_BANNER_PATTERN) {
                    if (limeWool) return;
                    limeWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lLime Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.ENDER_CHEST) {
                    if (pinkWool) return;
                    pinkWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lPink Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.TRIDENT) {
                    if (grayWool) return;
                    grayWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lGray Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.TOTEM_OF_UNDYING) {
                    if (lightGrayWool) return;
                    lightGrayWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lLight Gray Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.MUSIC_DISC_11) {
                    if (cyanWool) return;
                    cyanWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lCyan Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.SPONGE) {
                    if (purpleWool) return;
                    purpleWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lPurple Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.BEE_NEST) {
                    if (blueWool) return;
                    blueWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lBlue Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.END_CRYSTAL) {
                    if (brownWool) return;
                    brownWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lBrown Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.DRAGON_EGG) {
                    if (greenWool) return;
                    greenWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lGreen Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.TIPPED_ARROW) {
                    if (redWool) return;
                    redWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lRed Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (item.getType() == Material.CYAN_SHULKER_BOX) {
                    if (blackWool) return;
                    blackWool = Boolean.TRUE;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        online.sendTitle(Utils.chat("&f&lBlack Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                        online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                }
                if (Main.Minerals) {
                    if (item.getType() == Material.COAL_BLOCK) {
                        if (Coal) return;
                        Coal = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lCoal Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.IRON_BLOCK) {
                        if (Iron) return;
                        Iron = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lIron Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.GOLD_BLOCK) {
                        if (Gold) return;
                        Gold = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lGold Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.LAPIS_BLOCK) {
                        if (Lapis) return;
                        Lapis = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lLapis Lazuli Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.REDSTONE_BLOCK) {
                        if (Redstone) return;
                        Redstone = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lRedstone Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.EMERALD_BLOCK) {
                        if (Emerald) return;
                        Emerald = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lEmerald Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.DIAMOND_BLOCK) {
                        if (Diamond) return;
                        Diamond = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lDiamond Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPickUp(EntityPickupItemEvent e) {
        if (e.getEntity().getType() == EntityType.PLAYER) {
            Item PickItem = e.getItem();
            ItemStack item = PickItem.getItemStack();
            Player p = (Player) e.getEntity();
            if (Main.started) {

                if (item == null || item.getType() == Material.AIR) return;

                if (Main.NormalMode) {
                    if (item.getType() == Material.CAKE) {
                        if (whiteWool) return;
                        whiteWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lWhite Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.DAMAGED_ANVIL) {
                        if (orangeWool) return;
                        orangeWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lOrange Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.COBWEB) {
                        if (magentaWool) return;
                        magentaWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lMagenta Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.BEETROOT_SOUP) {
                        if (lightBlueWool) return;
                        lightBlueWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lLight Blue Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.OBSERVER) {
                        if (yellowWool) return;
                        yellowWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lYellow Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.EXPERIENCE_BOTTLE) {
                        if (limeWool) return;
                        limeWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lLime Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.ENDER_CHEST) {
                        if (pinkWool) return;
                        pinkWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lPink Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.NAUTILUS_SHELL) {
                        if (grayWool) return;
                        grayWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lGray Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.DIAMOND_BLOCK) {
                        if (lightGrayWool) return;
                        lightGrayWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lLight Gray Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.TOTEM_OF_UNDYING) {
                        if (cyanWool) return;
                        cyanWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lCyan Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.MUSIC_DISC_11) {
                        if (purpleWool) return;
                        purpleWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lPurple Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.SPONGE) {
                        if (blueWool) return;
                        blueWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lBlue Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.BEE_NEST) {
                        if (brownWool) return;
                        brownWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lBrown Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.END_CRYSTAL) {
                        if (greenWool) return;
                        greenWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lGreen Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.DRAGON_EGG) {
                        if (redWool) return;
                        redWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lRed Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.TIPPED_ARROW) {
                        if (blackWool) return;
                        blackWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lBlack Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (Main.Minerals) {
                        if (item.getType() == Material.COAL_BLOCK) {
                            if (Coal) return;
                            Coal = Boolean.TRUE;
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.sendTitle(Utils.chat("&f&lCoal Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                                online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                            }
                        }
                        if (item.getType() == Material.IRON_BLOCK) {
                            if (Iron) return;
                            Iron = Boolean.TRUE;
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.sendTitle(Utils.chat("&f&lIron Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                                online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                            }
                        }
                        if (item.getType() == Material.GOLD_BLOCK) {
                            if (Gold) return;
                            Gold = Boolean.TRUE;
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.sendTitle(Utils.chat("&f&lGold Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                                online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                            }
                        }
                        if (item.getType() == Material.LAPIS_BLOCK) {
                            if (Lapis) return;
                            Lapis = Boolean.TRUE;
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.sendTitle(Utils.chat("&f&lLapis Lazuli Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                                online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                            }
                        }
                        if (item.getType() == Material.REDSTONE_BLOCK) {
                            if (Redstone) return;
                            Redstone = Boolean.TRUE;
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.sendTitle(Utils.chat("&f&lRedstone Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                                online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                            }
                        }
                        if (item.getType() == Material.EMERALD_BLOCK) {
                            if (Emerald) return;
                            Emerald = Boolean.TRUE;
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.sendTitle(Utils.chat("&f&lEmerald Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                                online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                            }
                        }
                        if (item.getType() == Material.DIAMOND_BLOCK) {
                            if (Diamond) return;
                            Diamond = Boolean.TRUE;
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.sendTitle(Utils.chat("&f&lDiamond Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                                online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                            }
                        }
                    }
                } else if (Main.HardMode) {
                    if (item.getType() == Material.CAKE) {
                        if (whiteWool) return;
                        whiteWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lWhite Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.DAMAGED_ANVIL) {
                        if (orangeWool) return;
                        orangeWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lOrange Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.RABBIT_STEW) {
                        if (magentaWool) return;
                        magentaWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lMagenta Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.OBSERVER) {
                        if (lightBlueWool) return;
                        lightBlueWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lLight Blue Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.HEART_OF_THE_SEA) {
                        if (yellowWool) return;
                        yellowWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lYellow Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.SKULL_BANNER_PATTERN) {
                        if (limeWool) return;
                        limeWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lLime Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.ENDER_CHEST) {
                        if (pinkWool) return;
                        pinkWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lPink Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.TRIDENT) {
                        if (grayWool) return;
                        grayWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lGray Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.TOTEM_OF_UNDYING) {
                        if (lightGrayWool) return;
                        lightGrayWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lLight Gray Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.MUSIC_DISC_11) {
                        if (cyanWool) return;
                        cyanWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lCyan Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.SPONGE) {
                        if (purpleWool) return;
                        purpleWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lPurple Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.BEE_NEST) {
                        if (blueWool) return;
                        blueWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lBlue Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.END_CRYSTAL) {
                        if (brownWool) return;
                        brownWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lBrown Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.DRAGON_EGG) {
                        if (greenWool) return;
                        greenWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lGreen Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.TIPPED_ARROW) {
                        if (redWool) return;
                        redWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lRed Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (item.getType() == Material.CYAN_SHULKER_BOX) {
                        if (blackWool) return;
                        blackWool = Boolean.TRUE;
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            online.sendTitle(Utils.chat("&f&lBlack Item"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                            online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        }
                    }
                    if (Main.Minerals) {
                        if (item.getType() == Material.COAL_BLOCK) {
                            if (Coal) return;
                            Coal = Boolean.TRUE;
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.sendTitle(Utils.chat("&f&lCoal Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                                online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                            }
                        }
                        if (item.getType() == Material.IRON_BLOCK) {
                            if (Iron) return;
                            Iron = Boolean.TRUE;
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.sendTitle(Utils.chat("&f&lIron Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                                online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                            }
                        }
                        if (item.getType() == Material.GOLD_BLOCK) {
                            if (Gold) return;
                            Gold = Boolean.TRUE;
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.sendTitle(Utils.chat("&f&lGold Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                                online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                            }
                        }
                        if (item.getType() == Material.LAPIS_BLOCK) {
                            if (Lapis) return;
                            Lapis = Boolean.TRUE;
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.sendTitle(Utils.chat("&f&lLapis Lazuli Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                                online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                            }
                        }
                        if (item.getType() == Material.REDSTONE_BLOCK) {
                            if (Redstone) return;
                            Redstone = Boolean.TRUE;
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.sendTitle(Utils.chat("&f&lRedstone Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                                online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                            }
                        }
                        if (item.getType() == Material.EMERALD_BLOCK) {
                            if (Emerald) return;
                            Emerald = Boolean.TRUE;
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.sendTitle(Utils.chat("&f&lEmerald Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                                online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                            }
                        }
                        if (item.getType() == Material.DIAMOND_BLOCK) {
                            if (Diamond) return;
                            Diamond = Boolean.TRUE;
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.sendTitle(Utils.chat("&f&lDiamond Block"), Utils.chat("&6Obtained by " + p.getDisplayName()));
                                online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if (Main.UHC) {
            p.setGameMode(GameMode.SPECTATOR);
        }
    }


    public static int getObtainedWools() {
        int wools = 0;

        if (whiteWool) wools++;

        if (orangeWool) wools++;

        if (magentaWool) wools++;

        if (lightBlueWool) wools++;

        if (yellowWool) wools++;

        if (limeWool) wools++;

        if (pinkWool) wools++;

        if (grayWool) wools++;

        if (lightGrayWool) wools++;

        if (cyanWool) wools++;

        if (purpleWool) wools++;

        if (blueWool) wools++;

        if (brownWool) wools++;

        if (greenWool) wools++;

        if (redWool) wools++;

        if (blackWool) wools++;

        return wools;
    }

    public static int getObtainedMinerals() {
        int minerals = 0;

        if (Coal) minerals++;

        if (Iron) minerals++;

        if (Gold) minerals++;

        if (Lapis) minerals++;

        if (Redstone) minerals++;

        if (Emerald) minerals++;

        if (Diamond) minerals++;

        return minerals;
    }
}
