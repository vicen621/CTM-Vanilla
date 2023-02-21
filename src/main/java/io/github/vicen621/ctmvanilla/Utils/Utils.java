package io.github.vicen621.ctmvanilla.Utils;

import io.github.vicen621.ctmvanilla.Main;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Copyright (c) 2021 Vicen621.
 * All rights reserved.
 */
@UtilityClass
public class Utils {

    private final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
    private final Color[] colors = new Color[]{
            Color.AQUA, Color.BLACK, Color.BLUE, Color.FUCHSIA, Color.GRAY, Color.GREEN, Color.LIME, Color.MAROON,
            Color.NAVY, Color.OLIVE, Color.ORANGE, Color.PURPLE, Color.RED, Color.SILVER, Color.TEAL, Color.WHITE,
            Color.YELLOW
    };

    public String chat(String s) {
        if (Bukkit.getVersion().contains("1.16") || Bukkit.getVersion().contains("1.17")) {
            s = s.replace("&#", "#").replace("&k", "");
            Matcher match = pattern.matcher(s);
            while (match.find()) {
                String color = s.substring(match.start(), match.end());
                s = s.replace(color, net.md_5.bungee.api.ChatColor.of(color) + "");
                match = pattern.matcher(s);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public boolean isInt(String s) {
        try {
            Float.parseFloat(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public String getTime() {
        TimeZone tz = TimeZone.getTimeZone(Main.config.getConfig().getString("scoreboard.timeZone"));
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());
        return nowAsISO;
    }

    public String ListChat(List<String> s) {
        return ChatColor.translateAlternateColorCodes('&', String.valueOf(s));
    }

    public String formatTime(int secs) {
        int remainder = secs % 86400;

        int days = secs / 86400;
        int hours = remainder / 3600;
        int minutes = (remainder / 60) - (hours * 60);
        int seconds = (remainder % 3600) - (minutes * 60);

        if (days > 0) {
            return days + " " + hours + ":" + minutes + ":" + seconds;
        } else if (hours > 0) {
            return hours + ":" + minutes + ":" + seconds;
        } else if (minutes > 0) {
            return minutes + ":" + seconds;
        } else {
            return String.valueOf(seconds);
        }
    }

    public Color getRandomColor() {
        return colors[ThreadLocalRandom.current().nextInt(colors.length)];
    }
}
