package io.github.vicen621.ctmvanilla.Utils;

import io.github.vicen621.ctmvanilla.Main;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/*
 * Copyright (c) 2021 Vicen621.
 * All rights reserved.
 */
@UtilityClass
public class Utils {
    private final Color[] COLORS = new Color[]{
            Color.AQUA, Color.BLACK, Color.BLUE, Color.FUCHSIA, Color.GRAY, Color.GREEN, Color.LIME, Color.MAROON,
            Color.NAVY, Color.OLIVE, Color.ORANGE, Color.PURPLE, Color.RED, Color.SILVER, Color.TEAL, Color.WHITE,
            Color.YELLOW
    };

    public boolean isInt(String s) {
        try {
            Float.parseFloat(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static int getTotalDeaths() {
        int deaths = 0;

        for (UUID uuid : Main.getInstance().getGameManager().getPlaying()) {
            OfflinePlayer p = Bukkit.getOfflinePlayer(uuid);
            deaths += p.getStatistic(Statistic.DEATHS);
        }

        return deaths;
    }

    public Color getRandomColor() {
        return COLORS[ThreadLocalRandom.current().nextInt(COLORS.length)];
    }
}
