package io.github.vicen621.ctmvanilla.listeners;

import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.game.GameManager;
import io.github.vicen621.ctmvanilla.game.wool.Wool;
import io.github.vicen621.ctmvanilla.game.wool.WoolManager;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/*
 * Copyright (c) 2021 Vicen621.
 * All rights reserved.
 */
public class WoolsListeners implements Listener {
    private final GameManager gameManager;
    private final WoolManager woolManager;

    public WoolsListeners(Main plugin) {
        this.gameManager = plugin.getGameManager();
        this.woolManager = plugin.getWoolManager();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player p)
            consumeEvent(p, e.getCurrentItem());
    }

    @EventHandler
    public void onPickUp(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player p)
            consumeEvent(p, e.getItem().getItemStack());
    }

    public void consumeEvent(Player p, ItemStack item) {
        if (gameManager.getGameState() != GameManager.GameState.PLAYING || item.getType() == Material.AIR)
            return;

        if (!gameManager.isPlaying(p))
            return;

        if (!woolManager.isWoolMaterial(item.getType()))
            return;

        Wool wool = woolManager.getWoolByMaterial(item.getType()).orElse(null);

        if (wool == null || wool.isTaken())
            return;

        woolManager.woolObtained(wool, p, gameManager.getTimer().getCurrentTimeFormatted());
    }
}
