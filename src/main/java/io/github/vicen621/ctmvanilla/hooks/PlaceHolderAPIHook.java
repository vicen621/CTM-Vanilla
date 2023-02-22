package io.github.vicen621.ctmvanilla.hooks;

import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.Utils.Utils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PlaceHolderAPIHook extends PlaceholderExpansion {
    private final Main plugin;

    public PlaceHolderAPIHook(Main plugin) {
        this.plugin = plugin;
    }


    @Override
    public @NotNull String getIdentifier() {
        return "ctmv";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Vicen621";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @NotNull List<String> getPlaceholders() {
        return List.of("ctmv_gamemode", "ctmv_obtained_wools", "ctmv_time_played", "ctmv_total_deaths");
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        // gamemode: Shows the current gamemode
        // obtained_wools: Displays your currents wools
        // time_Played: Displays the time played
        // total_deaths: Displays the deaths of all teams

        switch (params) {
            case "gamemode" -> {
                return WordUtils.capitalizeFully(plugin.getGameManager().getGameMode().toString());
            }
            case "obtained_wools" -> {
                return String.valueOf(plugin.getWoolManager().getObtainedWools());
            }
            case "time_played" -> {
                // TODO
                //  return plugin.getGameManager().getTimer().getTime();
                return "1";
            }
            case "total_deaths" -> {
                return String.valueOf(Utils.getTotalDeaths());
            }
        }

        return null;
    }
}
