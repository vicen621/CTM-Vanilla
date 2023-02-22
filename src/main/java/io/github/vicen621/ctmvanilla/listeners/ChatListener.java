package io.github.vicen621.ctmvanilla.listeners;

import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.Utils.StringUtils;
import io.github.vicen621.ctmvanilla.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/*
 * Copyright (c) 2021 Vicen621.
 * All rights reserved.
 */

public class ChatListener implements Listener {

    private final GameManager gameManager;

    public ChatListener(Main plugin) {
        this.gameManager = plugin.getGameManager();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage();

        msg = msg.replace("%", "");

        if (gameManager.getGameState() != GameManager.GameState.PLAYING) {
            e.setFormat(StringUtils.chat("&7" + p.getDisplayName() + " &#4b5061» &f" + msg));
            return;
        }

        e.setFormat(StringUtils.chat("&#313535[" +
                (gameManager.isPlaying(p) ? "&#0c768bPlayer" : "&#00f7ffSpectator") +
                "&#313535] &7" + p.getDisplayName() + " &#4b5061» &f" + msg)
        );
    }
}
