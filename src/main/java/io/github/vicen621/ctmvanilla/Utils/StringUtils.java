package io.github.vicen621.ctmvanilla.Utils;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

@UtilityClass
public class StringUtils {
    public static String PREFIX = "<#313535>[<#0c768b>CTM Vanilla<#313535>] <#4b5061>» <gray>";

    public void broadcast(String message) {
        broadcast(PREFIX, message);
    }

    public void broadcast(String prefix, String message) {
        MiniMessage mm = MiniMessage.miniMessage();
        Bukkit.broadcast(mm.deserialize(prefix).append(mm.deserialize(message)));
    }

    public void sendMessage(CommandSender sender, String message) {
        sender.sendRichMessage(PREFIX + message);
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
}
