package me.vicen621.ctmvanilla.Scoreboard;

import me.vicen621.ctmvanilla.Commands.Start;
import me.vicen621.ctmvanilla.Listeners.Wools;
import me.vicen621.ctmvanilla.Main;
import me.vicen621.ctmvanilla.Utils.Utils;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.util.List;

public class Scoreboard {
    public static void update(FastBoard board){
        if (!Main.started && !Main.lose && !Main.won){
            List<String> lines = Main.config.getConfig().getStringList("scoreboard.WaitingBoard");

            for (int i = 0; i < lines.toArray().length; i++) {
                String currentLine = lines.get(i);
                if (currentLine.contains("%Current_Time%")){
                    currentLine = currentLine.replace("%Current_Time%", Utils.getTime());
                }
                board.updateLine(i, Utils.chat(currentLine));
            }
        } else if (Main.started){
            List<String> lines = Main.config.getConfig().getStringList("scoreboard.PlayingBoard");

            for (int i = 0; i < lines.toArray().length; i++){
                String currentLine = lines.get(i);
                if (currentLine.contains("%gamemode%")){
                    if (Main.HardMode){
                        currentLine = currentLine.replace("%gamemode%", "HardMode");
                    }
                    if (Main.NormalMode){
                        currentLine = currentLine.replace("%gamemode%", "Normal");
                    }
                }
                if (currentLine.contains("%obtained_wools%")){
                    currentLine = currentLine.replace("%obtained_wools%", String.valueOf(Wools.getObtainedWools()));
                }
                if (currentLine.contains("%time_Played%")){
                    currentLine = currentLine.replace("%time_Played%", Start.timer);
                }
                if (currentLine.contains("%total_deaths%")){
                    currentLine = currentLine.replace("%total_deaths%", String.valueOf(getTotalDeaths()));
                }

                board.updateLine(i, Utils.chat(currentLine));
            }
        }else if (Main.lose){
            List<String> lines = Main.config.getConfig().getStringList("scoreboard.LoseBoard");
            board.updateLines("");

            for (int i = 0; i < lines.toArray().length; i++) {
                String currentLine = lines.get(i);

                board.updateLine(i, Utils.chat(currentLine));
            }
        }else if (Main.won){
            List<String> lines = Main.config.getConfig().getStringList("scoreboard.WonBoard");
            board.updateLines("");

            for (int i = 0; i < lines.toArray().length; i++) {
                String currentLine = lines.get(i);

                board.updateLine(i, Utils.chat(currentLine));
            }
        }
    }

    public static int getTotalDeaths(){
        int Deaths = 0;

        for (int i = 0; i < Main.Playing.size(); i++){
            Player p = Main.Playing.get(i);
            Deaths = Deaths + p.getStatistic(Statistic.DEATHS);
        }
        return Deaths;
    }
}
