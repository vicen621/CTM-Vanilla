package io.github.vicen621.ctmvanilla.Utils;

import io.github.vicen621.ctmvanilla.Main;
import lombok.experimental.UtilityClass;
import org.bukkit.*;
import org.bukkit.command.CommandSender;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        for (UUID uuid : Main.getInstance().getGameManager().getPlaying()){
            OfflinePlayer p = Bukkit.getOfflinePlayer(uuid);
            deaths += p.getStatistic(Statistic.DEATHS);
        }

        return deaths;
    }

    public Color getRandomColor() {
        return COLORS[ThreadLocalRandom.current().nextInt(COLORS.length)];
    }
}
