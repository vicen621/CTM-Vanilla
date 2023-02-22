package io.github.vicen621.ctmvanilla.config;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import lombok.Getter;

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

    @Comment("Time in minutes")
    private GameConfig game = new GameConfig(180, 120);

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
}
