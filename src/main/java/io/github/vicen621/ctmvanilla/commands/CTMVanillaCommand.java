package io.github.vicen621.ctmvanilla.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.Utils.StringUtils;
import io.github.vicen621.ctmvanilla.game.GameManager;
import io.github.vicen621.ctmvanilla.game.wool.Wool;
import io.github.vicen621.ctmvanilla.game.wool.WoolManager;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.command.CommandSender;

import java.util.List;

@CommandAlias("ctm|ctmfv|ctmvanilla")
public class CTMVanillaCommand extends BaseCommand {

    @Dependency
    private Main plugin;

    @Dependency
    private GameManager gameManager;

    @Dependency
    private WoolManager woolManager;

    @Default
    @CatchUnknown
    @HelpCommand
    public void onDefault(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

    @CommandAlias("wools")
    @Description("Shows the missing wools and minerals to won the game")
    public void onWools(CommandSender sender) {
        if (gameManager.getGameState() != GameManager.GameState.PLAYING) {
            StringUtils.sendMessage(sender, "&cNo game running, please start the game to see the missing items");
            return;
        }

        List<Wool> missingWools = woolManager.getWools().stream().filter(wool -> !wool.isTaken()).toList();

        sender.sendMessage(StringUtils.chat("&5------------ &dMissing Items &5------------"));
        for (Wool wool : missingWools)
            sender.sendMessage(StringUtils.chat("&5- &d" + WordUtils.capitalizeFully(wool.getMaterial().toString())));
        sender.sendMessage(StringUtils.chat("&5---------------------------------------"));
    }

    @CommandAlias("reload")
    @CommandPermission("ctm.op")
    @Description("Reloads the configuration file")
    public void onReload(CommandSender sender) {
        plugin.updateConfig();
        StringUtils.sendMessage(sender, "Reload complete");
    }

    @CommandAlias("guide")
    @Description("Gives the guide to the player")
    public static class GuideCommand extends BaseCommand {

        @Default
        @CatchUnknown
        @HelpCommand
        public void onDefault(CommandSender sender, CommandHelp help) {
            help.showHelp();
        }


    }
}
