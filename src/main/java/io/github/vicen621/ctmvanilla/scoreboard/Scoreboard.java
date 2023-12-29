package io.github.vicen621.ctmvanilla.scoreboard;

import fr.mrmicky.fastboard.adventure.FastBoard;
import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.config.Config;
import io.github.vicen621.ctmvanilla.game.GameManager;
import lombok.experimental.UtilityClass;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

import java.util.List;

/*
 * Copyright (c) 2021 Vicen621.
 * All rights reserved.
 */

@UtilityClass
public class Scoreboard {
    private final GameManager gameManager = Main.getInstance().getGameManager();
    private final Config config = Main.getInstance().getConfiguration();

    public static void update(FastBoard board) {
        Player p = board.getPlayer();
        List<String> lines = null;
        switch (gameManager.getGameState()) {
            case WAITING -> lines = config.getScoreboard().waitingBoard();
            case PLAYING -> lines = config.getScoreboard().playingBoard();
            case LOSE -> lines = config.getScoreboard().loseBoard();
            case WON -> lines = config.getScoreboard().wonBoard();
        }

        board.updateLines(
                lines.stream()
                        .map(line -> replacePlaceHolders(p, line))
                        .map(MiniMessage.miniMessage()::deserialize)
                        .toList()
        );
    }

    public String replacePlaceHolders(Player p, String s) {
        return PlaceholderAPI.setPlaceholders(p, s);
    }
}
