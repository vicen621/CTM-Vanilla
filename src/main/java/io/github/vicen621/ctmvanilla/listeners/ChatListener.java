package io.github.vicen621.ctmvanilla.listeners;

import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.game.GameManager;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


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
    public void onChat(AsyncChatEvent e) {
        Player p = e.getPlayer();
        MiniMessage mm = MiniMessage.miniMessage();

        if (gameManager.getGameState() != GameManager.GameState.PLAYING) {
            e.renderer((source, sourceDisplayName, msg, viewer) -> mm.deserialize(
                    "<gray><sender> <#4b5061>» <white><msg>",
                    Placeholder.component("sender", sourceDisplayName),
                    Placeholder.component("msg", msg)
            ));
            return;
        }

        e.renderer((source, sourceDisplayName, msg, viewer) -> mm.deserialize(
                "<#313535>[<prefix><#313535>] <gray><sender> <#4b5061>» <white><msg>",
                Placeholder.parsed("prefix", gameManager.isPlaying(p) ? "<#0c768b>Player" : "<#00f7ff>Spectator"),
                Placeholder.component("sender", sourceDisplayName),
                Placeholder.component("msg", msg)
        ));
    }
}
