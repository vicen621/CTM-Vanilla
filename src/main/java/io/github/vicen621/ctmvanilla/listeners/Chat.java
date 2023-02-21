package io.github.vicen621.ctmvanilla.listeners;

import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/*
 * Copyright (c) 2021 Vicen621.
 * All rights reserved.
 */

public class Chat implements Listener {

    private final Main plugin;

    public Chat(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage();

        msg = msg.replace("%", "");

        if (Main.started){
            if (Main.Playing.contains(p.getUniqueId())) {
                e.setFormat(Utils.chat("&#313535[&#0c768bPlayer&#313535] &7" + p.getDisplayName() + " &#4b5061» &f" + msg));
            }else{
                e.setFormat(Utils.chat("&#313535[&#00f7ffSpectator&#313535] &7" + p.getDisplayName() + " &#4b5061» &f" + msg));
            }
        }else{
            e.setFormat(Utils.chat("&7" + p.getDisplayName() + " &#4b5061» &f" + msg));
        }
    }
}
