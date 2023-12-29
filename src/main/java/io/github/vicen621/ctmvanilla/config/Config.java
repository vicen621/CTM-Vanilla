package io.github.vicen621.ctmvanilla.config;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import de.exlll.configlib.Serializer;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.List;


@Getter
@Configuration
@SuppressWarnings("FieldMayBeFinal")
public class Config {

    private ScoreboardConfig scoreboard = new ScoreboardConfig(
            "<gold><bold>CTM Vanilla",
            List.of(
                    " ",
                    "  <red>Waiting...  ",
                    " ",
                    " ",
                    "  <aqua>Argentina Time  ",
                    "  %localtime_timezone_America/Argentina/Buenos_Aires,HH:mm:ss%  ",
                    " "
            ),
            List.of(
                    "  <green>Gamemode<white>: %ctmv_gamemode%  ",
                    " ",
                    "  <aqua>Obtained wools  ",
                    "  %ctmv_obtained_wools% / 16  ",
                    " ",
                    "  <red>Time  ",
                    "  %ctmv_time_played%  ",
                    " ",
                    "  <light_purple>Deaths<white>: %ctmv_total_deaths%  ",
                    " "
            ),
            List.of(
                    " ",
                    "  <red>You Lose  ",
                    "  <red>You exceeded the time limit  ",
                    " "
            ),
            List.of(
                    " ",
                    "  <green>YOU WON!  ",
                    "  <aqua>You completed the monument  ",
                    " "
            )
    );

    private WorldsConfig worlds = new WorldsConfig(
            Bukkit.getWorld("world"),
            Bukkit.getWorld("world_nether"),
            Bukkit.getWorld("world_the_end")
    );

    @Comment("Time in minutes")
    private GameConfig game = new GameConfig(120, 180);

    public record ScoreboardConfig(
            String title,

            @Comment({"", "Scoreboard that shown where you are waiting"})
            List<String> waitingBoard,

            @Comment({"", "Scoreboard that shown when you are playing"})
            List<String> playingBoard,

            @Comment({"", "Scoreboard that shown when you lose the game"})
            List<String> loseBoard,

            @Comment({"", "Scorebard that shown when you won the game"})
            List<String> wonBoard
    ) {
    }

    public record GameConfig(
            int maxGameTimeNormalMode,
            int maxGameTimeHardMode
    ) {
    }

    public record WorldsConfig(
            World overworld,
            World nether,
            World theEnd
    ) {
    }
}
