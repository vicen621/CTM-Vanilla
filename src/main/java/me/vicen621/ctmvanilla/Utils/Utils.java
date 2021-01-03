package me.vicen621.ctmvanilla.Utils;

import me.vicen621.ctmvanilla.Main;
import org.bukkit.ChatColor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Utils {

    public static String getTime(){
        TimeZone tz = TimeZone.getTimeZone(Main.config.getConfig().getString("scoreboard.timeZone"));
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());
        return nowAsISO;
    }

    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String ListChat(List<String> s) {
        return ChatColor.translateAlternateColorCodes('&', String.valueOf(s));
    }

    public static String formatTime(int secs) {
        int remainder = secs % 86400;

        int days    = secs / 86400;
        int hours    = remainder / 3600;
        int minutes    = (remainder / 60) - (hours * 60);
        int seconds    = (remainder % 3600) - (minutes * 60);

        if (days > 0) {
            return days+ " " + hours + ":" + minutes + ":" + seconds;
        } else if (hours > 0) {
            return hours + ":" + minutes + ":" + seconds;
        } else if (minutes > 0) {
            return minutes + ":" + seconds;
        } else {
            return String.valueOf(seconds);
        }
    }
}
