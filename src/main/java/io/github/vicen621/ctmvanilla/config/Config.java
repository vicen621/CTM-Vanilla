package io.github.vicen621.ctmvanilla.config;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import de.exlll.configlib.Serializer;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.List;

@Configuration
@Getter
public class Config {

    private ScoreboardConfig scoreboard = new ScoreboardConfig(
            "&6&lCTM Vanilla",
            List.of(
                    "  &a%gamemode%  ",
                    " ",
                    "  &bObtained wools  ",
                    "  %obtained_wools% / 16  ",
                    " ",
                    "  &cTime  ",
                    "  %time_Played%  ",
                    " ",
                    "  &dDeaths&f: %total_deaths%  ",
                    " "
            ),
            List.of(
                    " ",
                    "  &cWaiting...  ",
                    " ",
                    " ",
                    "  &bArgentina Time  ",
                    "  %Current_Time%  ",
                    " "
            ),
            List.of(
                    " ",
                    "  &cYou Lose  ",
                    "  &cYou exceeded the time limit  ",
                    " "
            ),
            List.of(
                    " ",
                    "  &aYOU WON!  ",
                    "  &bYou completed the monument  ",
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
    ) { }

    public record GameConfig(
            int maxGameTimeNormalMode,
            int maxGameTimeHardMode
    ) { }

    public record WorldsConfig(
            World overworld,
            World nether,
            World theEnd
    ) { }

    public static class WorldToStringSerializer implements Serializer<World, String> {

        @Override
        public String serialize(World element) {
            return element.getName();
        }

        @Override
        public World deserialize(String element) {
            return Bukkit.getWorld(element);
        }
    }
}
