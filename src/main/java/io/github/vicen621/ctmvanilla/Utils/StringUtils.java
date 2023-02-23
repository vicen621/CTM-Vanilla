package io.github.vicen621.ctmvanilla.Utils;

import lombok.experimental.UtilityClass;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class StringUtils {
    public static String PREFIX = "&#313535[&#0c768bCTM Vanilla&#313535] &#4b5061» &7";

    public static String chat(String s) {
        Pattern pattern = Pattern.compile("(#|&#)([A-Fa-f0-9]{6})");
        s = s.replace("&k", "&r");
        if (Integer.parseInt(Bukkit.getVersion().split("\\.")[1]) >= 16) {
            Matcher match = pattern.matcher(s);
            while (match.find()) {
                String color = s.substring(match.start(), match.end());
                s = s.replace(color, net.md_5.bungee.api.ChatColor.of(color.replace("&#", "#")) + "");
                match = pattern.matcher(s);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public void broadcast(String message) {
        broadcast(PREFIX, message);
    }

    public void broadcast(String prefix, String message) {
        Bukkit.broadcastMessage(chat(prefix + message));
    }

    public void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(chat(PREFIX + message));
    }

    public String formatTime(int secs) {
        int remainder = secs % 86400;

        int days = secs / 86400;
        int hours = remainder / 3600;
        int minutes = (remainder / 60) - (hours * 60);
        int seconds = (remainder % 3600) - (minutes * 60);

        String dayy = (days < 10 ? "0" : "") + days;
        String horr = (hours < 10 ? "0" : "") + hours;
        String minn = (minutes < 10 ? "0" : "") + minutes;
        String secc = (seconds < 10 ? "0" : "") + seconds;

        if (days > 0)
            return dayy + " " + horr + ":" + minn + ":" + secc;
        else if (hours > 0)
            return horr + ":" + minn + ":" + secc;
        else
            return minn + ":" + secc;
    }

    public String replacePlaceHolders(Player p, String s) {
        return PlaceholderAPI.setPlaceholders(p, s);
    }
}
