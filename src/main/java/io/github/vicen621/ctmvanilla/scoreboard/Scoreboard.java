package io.github.vicen621.ctmvanilla.scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.Utils.StringUtils;
import io.github.vicen621.ctmvanilla.config.Config;
import io.github.vicen621.ctmvanilla.game.GameManager;
import lombok.experimental.UtilityClass;
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

        lines = lines.stream().map(line -> StringUtils.replacePlaceHolders(p, line)).toList();
        board.updateLines(lines);
    }
}
