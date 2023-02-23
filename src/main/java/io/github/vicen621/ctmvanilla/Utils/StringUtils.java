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
    private final Pattern PATTERN = Pattern.compile("#[a-fA-F0-9]{6}");
    public static String PREFIX = "&#313535[&#0c768bCTM Vanilla&#313535] &#4b5061» &7";

    public String chat(String s) {
        if (Bukkit.getVersion().contains("1.16") || Bukkit.getVersion().contains("1.17")) {
            s = s.replace("&#", "#").replace("&k", "");
            Matcher match = PATTERN.matcher(s);
            while (match.find()) {
                String color = s.substring(match.start(), match.end());
                s = s.replace(color, net.md_5.bungee.api.ChatColor.of(color) + "");
                match = PATTERN.matcher(s);
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

    public String replacePlaceHolders(Player p, String s) {
        return PlaceholderAPI.setPlaceholders(p, s);
    }
}
