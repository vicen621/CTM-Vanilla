package io.github.vicen621.ctmvanilla.scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import io.github.vicen621.ctmvanilla.commands.Start;
import io.github.vicen621.ctmvanilla.listeners.Wools;
import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;

import java.util.List;
import java.util.UUID;

/*
 * Copyright (c) 2021 Vicen621.
 * All rights reserved.
 */

public class Scoreboard {
    public static void update(FastBoard board) {
        if (!Main.started && !Main.lose && !Main.won) {
            List<String> lines = Main.config.getConfig().getStringList("scoreboard.WaitingBoard");

            for (int i = 0; i < lines.toArray().length; i++) {
                String currentLine = lines.get(i);
                if (currentLine.contains("%Current_Time%")) {
                    currentLine = currentLine.replace("%Current_Time%", Utils.getTime());
                }
                board.updateLine(i, Utils.chat(currentLine));
            }
        } else if (Main.started) {
            List<String> lines = Main.config.getConfig().getStringList("scoreboard.PlayingBoard");

            for (int i = 0; i < lines.toArray().length; i++) {
                String currentLine = lines.get(i);
                if (currentLine.contains("%gamemode%")) {
                    if (Main.HardMode) {
                        currentLine = currentLine.replace("%gamemode%", "HardMode");
                    }
                    if (Main.NormalMode) {
                        currentLine = currentLine.replace("%gamemode%", "Normal");
                    }
                }
                if (currentLine.contains("%obtained_wools%")) {
                    currentLine = currentLine.replace("%obtained_wools%", String.valueOf(Wools.getObtainedWools()));
                }
                if (currentLine.contains("%time_Played%")) {
                    currentLine = currentLine.replace("%time_Played%", Start.timer);
                }
                if (currentLine.contains("%total_deaths%")) {
                    currentLine = currentLine.replace("%total_deaths%", String.valueOf(getTotalDeaths()));
                }

                board.updateLine(i, Utils.chat(currentLine));
            }
        } else if (Main.lose) {
            List<String> lines = Main.config.getConfig().getStringList("scoreboard.LoseBoard");
            board.updateLines("");

            for (int i = 0; i < lines.toArray().length; i++) {
                String currentLine = lines.get(i);

                board.updateLine(i, Utils.chat(currentLine));
            }
        } else if (Main.won) {
            List<String> lines = Main.config.getConfig().getStringList("scoreboard.WonBoard");
            board.updateLines("");

            for (int i = 0; i < lines.toArray().length; i++) {
                String currentLine = lines.get(i);

                board.updateLine(i, Utils.chat(currentLine));
            }
        }
    }

    public static int getTotalDeaths() {
        int Deaths = 0;

        for (UUID uuid : Main.Playing){
            OfflinePlayer p = Bukkit.getOfflinePlayer(uuid);
            Deaths = Deaths + p.getStatistic(Statistic.DEATHS);
        }
        return Deaths;
    }
}