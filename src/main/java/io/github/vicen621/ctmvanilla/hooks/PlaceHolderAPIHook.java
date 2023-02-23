package io.github.vicen621.ctmvanilla.hooks;

import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.Utils.Utils;
import io.github.vicen621.ctmvanilla.game.wool.Wool;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
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
        return List.of("ctmv_gamemode", "ctmv_obtained_wools", "ctmv_remaining_time", "ctmv_obtained_minerals","ctmv_time_played", "ctmv_total_deaths", "ctm_wool,{wool_material}");
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        switch (params) {
            case "gamemode" -> {
                return WordUtils.capitalizeFully(plugin.getGameManager().getGameMode().toString());
            }
            case "obtained_wools" -> {
                return String.valueOf(plugin.getWoolManager().getObtainedWools());
            }
            case "obtained_minerals" -> {
                return String.valueOf(plugin.getWoolManager().getObtainedMinerals());
            }
            case "time_played" -> {
                return plugin.getGameManager().getTimer().getCurrentTimeFormatted();
            }
            case "remaining_time" -> {
                return plugin.getGameManager().getTimer().getRemainingTimeFormatted();
            }
            case "total_deaths" -> {
                return String.valueOf(Utils.getTotalDeaths());
            }
        }

        String[] s = params.split(",");

        if (s[0].equalsIgnoreCase("wool")) {
            Material mat = Material.matchMaterial(s[1]);
            Wool wool = Main.getInstance().getWoolManager().getWoolByMaterial(mat).orElse(null);

            if (wool == null)
                return "NULL";

            return getBoolean(wool.isTaken());
        }

        return null;
    }

    public String getBoolean(boolean bool) {
        return bool ? "&a✓" : "&c❌";
    }
}
