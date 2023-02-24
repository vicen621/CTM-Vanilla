package io.github.vicen621.ctmvanilla.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.Utils.StringUtils;
import io.github.vicen621.ctmvanilla.Utils.Utils;
import io.github.vicen621.ctmvanilla.game.GameManager;
import io.github.vicen621.ctmvanilla.game.wool.Wool;
import io.github.vicen621.ctmvanilla.game.wool.WoolManager;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

@CommandAlias("ctm|ctmfv|ctmvanilla")
public class CTMVanillaCommand extends BaseCommand {

    @Dependency
    private static Main plugin;
    @Dependency
    private static GameManager gameManager;
    @Dependency
    private static WoolManager woolManager;

    @Default
    @CatchUnknown
    @HelpCommand
    public void onDefault(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("wools")
    @Description("Shows the missing wools and minerals to won the game")
    public void onWools(CommandSender sender) {
        if (gameManager.getGameState() != GameManager.GameState.PLAYING) {
            StringUtils.sendMessage(sender, "&cNo game running, please start the game to see the missing items");
            return;
        }

        List<Wool> missingWools = woolManager.getWools().stream().filter(wool -> !wool.isTaken()).toList();

        sender.sendMessage(StringUtils.chat("&5------------ &dMissing Items &5------------"));
        for (Wool wool :missingWools)
            sender.sendMessage(StringUtils.chat("&5- &d" + WordUtils.capitalizeFully(wool.getMaterial().toString().replace("_", " "))));
        sender.sendMessage(StringUtils.chat("&5---------------------------------------"));
    }

    @Subcommand("reload")
    @CommandPermission("ctm.op")
    @Description("Reloads the configuration file")
    public void onReload(CommandSender sender) {
        plugin.updateConfig();
        StringUtils.sendMessage(sender, "Reload complete");
    }

    @Subcommand("guide")
    @Description("Gives the game guide to the player")
    public void onGuide(Player p) {
        switch (plugin.getGameManager().getGameMode()) {
            case NORMAL -> plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " written_book{author:\"CTM Vanilla\",pages:['[\"\",{\"text\":\" \\\\u0020 \\\\u0020 CTM Vanilla\",\"bold\":true,\"color\":\"gold\"},{\"text\":\"\\\\n\",\"color\":\"reset\",\"bold\":true},{\"text\":\"\\\\u0020\\\\u0020 SURVIVAL MODE\",\"bold\":true,\"color\":\"red\"},{\"text\":\"\\\\n\\\\nThis is a completly new gamemode designed and made by ElRichMC\\\\n\\\\nComplete The Monument Vanilla is a cooperative gamemode in a random seed where \",\"color\":\"reset\"},{\"text\":\"you has to find 16 diferent items.\",\"underlined\":true}]','[\"\",{\"text\":\"You can find the items without a specific order using whatever you need. You can\\'t use cheats or glitches.\\\\n\\\\n\"},{\"text\":\"\\\\u0020\\\\u0020 \\\\u0020 \\\\u0020ITEM LIST\",\"bold\":true,\"color\":\"light_purple\"},{\"text\":\"\\\\n\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 White Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n \\\\u0020Cake\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Orange Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\" \\\\u0020Very Damaged Anvil\",\"color\":\"black\"}]','[\"\",{\"text\":\"\\\\u2b25 Magenta Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n \\\\u0020Cobweb\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Light Blue Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n \\\\u0020Beetroot Soup\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Yellow Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n \\\\u0020Observer\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Lime Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n \\\\u0020Bottle o\\' Enchanting\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Pink Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\" \\\\u0020Ender Chest\",\"color\":\"black\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Gray Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n \\\\u0020Nautilus Shell\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Light Gray Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n \\\\u0020Diamond Block\",\"color\":\"reset\"}]','[\"\",{\"text\":\"\\\\u2b25 Cyan Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n \\\\u0020Totem Of Undying\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Purple Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n \\\\u0020Music Disc 11\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Blue Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n \\\\u0020Sponge\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Brown Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n \\\\u0020Bee Nest\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Green Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\" \\\\u0020Ender Crystal\\\\n\"},{\"text\":\"\\\\u2b25 Red Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\" \\\\u0020Dragon Egg\\\\n\"},{\"text\":\"\\\\u2b25 Black Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n \\\\u0020Tipped Arrow\",\"color\":\"reset\"}]','[\"\",{\"text\":\" \\\\u0020 \\\\u0020 \\\\u0020MINERALS\",\"bold\":true,\"color\":\"red\"},{\"text\":\"\\\\n\\\\u2b25 Coal Block\\\\n\\\\u2b25 Iron Block\\\\n\\\\u2b25 Gold Block\\\\n\\\\u2b25 Lapislazuli Block\\\\n\\\\u2b25 Redstone Block\\\\n\\\\u2b25 Emerald Block\\\\n\\\\u2b25 Diamond Block\\\\n\\\\nIf you or your team can get all of the items you\\'ll win the game!\",\"color\":\"reset\"}]','{\"text\":\"There\\'s three types of games to play CTM Vanilla:\\\\n\\\\n\\\\u2b25 Survival\\\\n\\\\u2b25 UHC\\\\n\\\\u2b25 HardMode\"}','[\"\",{\"text\":\"Commands\\\\n\\\\n\\\\u2560\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2563\\\\n\\\\n/ctm start \"},{\"text\":\"normal | hardmode\",\"color\":\"red\"},{\"text\":\"\\\\n\\\\n/ctm config (Configures all the gamemodes for the game)\",\"color\":\"reset\"}]'],title:\"A guide to CTM Vanilla: Normal\"}");
            case HARD -> plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " written_book{author:\"CTM Vanilla\",pages:['[\"\",{\"text\":\"  \\\\u0020\\\\u0020 CTM Vanilla\",\"bold\":true,\"color\":\"gold\"},{\"text\":\"\\\\n\",\"color\":\"reset\",\"bold\":true},{\"text\":\" \\\\u0020 \\\\u0020 HARDMODE\",\"bold\":true,\"color\":\"red\"},{\"text\":\"\\\\n\\\\nThis is a completly new gamemode designed and made by ElRichMC\\\\n\\\\nComplete The Monument Vanilla is a cooperative gamemode in a random seed where \",\"color\":\"reset\"},{\"text\":\"you has to find 16 diferent items.\",\"underlined\":true}]','{\"text\":\"You can find the items without a specific order using whatever you need. You can\\'t use cheats or glitches.\\\\n\\\\nThe hardmode further increases the gameplay with new items and reduced time to obtain them.\"}','[\"\",{\"text\":\"\\\\u0020\\\\u0020 \\\\u0020\\\\u0020ITEM LIST\",\"bold\":true,\"color\":\"light_purple\"},{\"text\":\"\\\\n\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 White Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n \\\\u0020Cake\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Orange Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\" \\\\u0020Damaged Anvil\\\\n\"},{\"text\":\"\\\\u2b25 Magenta Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n \\\\u0020Rabbit Stew\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Light blue Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n \\\\u0020Observer\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Yellow Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n \\\\u0020Heart Of The Sea\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Lime Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\" \\\\u0020Skull Charge Pattern\"}]','[\"\",{\"text\":\"\\\\u2b25 Pink Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\" \\\\u0020Ender Chest\\\\n\"},{\"text\":\"\\\\u2b25 Gray Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n \\\\u0020Trident\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Light Gray Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\" \\\\u0020Totem Of Undying\\\\n\"},{\"text\":\"\\\\u2b25 Cyan Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\" \\\\u0020Music Disc 11\\\\n\"},{\"text\":\"\\\\u2b25 Purple Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\" \\\\u0020Sponge\\\\n\"},{\"text\":\"\\\\u2b25 Blue Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\" \\\\u0020Bee Nest\\\\n\"},{\"text\":\"\\\\u2b25 Brown Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\" \\\\u0020End Crystal\",\"bold\":true}]','[\"\",{\"text\":\"\\\\u2b25 Green Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\" \\\\u0020Dragon Egg\",\"bold\":true},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Red Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\" \\\\u0020Tipped Arrow\",\"bold\":true},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Black Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\" \\\\u0020Cyan Shulker Box\\\\n\",\"bold\":true},{\"text\":\"    \\\\u0020 \\\\u0020\\\\u0020MINERALS\",\"bold\":true,\"color\":\"red\"},{\"text\":\"\\\\n\\\\u2b25 Coal Block\\\\n\\\\u2b25 Iron Block\\\\n\\\\u2b25 Gold Block\\\\n\\\\u2b25 Lapislazuli Block\\\\n\\\\u2b25 Redstone Block\\\\n\\\\u2b25 Emerald Block\\\\n\\\\u2b25 Diamond Block\",\"color\":\"reset\"}]','{\"text\":\"If you or your team can get all of the items you\\'ll win the game!\\\\n\\\\nThere\\'s three types of games to play CTM Vanilla:\\\\n\\\\n\\\\u2b25 Survival\\\\n\\\\u2b25 UHC\\\\n\\\\u2b25 HardMode\"}','[\"\",{\"text\":\"Commands\\\\n\\\\n\\\\u2560\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2563\\\\n\\\\n/start \"},{\"text\":\"normal | hardmode\",\"color\":\"red\"},{\"text\":\"\\\\n\\\\n/uhc (toggle\\'s UHC mode)\\\\n\\\\n/minerals (toggle\\'s Minerals mode\",\"color\":\"reset\"}]'],title:\"A guide to CTM Vanilla: Hard\"}");
            case NIGHTMARE -> { /*TODO*/ }
        }
    }

    @Subcommand("tl")
    @Description("Broadcast you're location to the other players, if you put any arguments it sends before the location")
    public void onTeamLocation(Player p, @Optional String message) {
        if (!gameManager.isPlaying(p)) {
            StringUtils.sendMessage(p, "Este comando solo puede ser usado por los jugadores de la partida!");
            return;
        }

        String prefix = StringUtils.chat("&#313535[&bTL&#313535] &#4b5061» ");
        String format = "&3&o{name}&8&o:&7X:&a{x} &7Y:&a{y} &7Z:&a{z} &8(&c{dimension}&8)";

        Location loc = p.getLocation();

        format = format.replace("{name}", p.getName());
        format = format.replace("{x}", "" + loc.getBlockX());
        format = format.replace("{y}", "" + loc.getBlockY());
        format = format.replace("{z}", "" + loc.getBlockZ());
        format = format.replace("{dimension}", Utils.environment(p.getWorld()));

        StringUtils.broadcast(prefix, (message == null ? "" :"&f" + message + " &7| ") + format);
    }

    @Subcommand("reset")
    @CommandPermission("ctm.op")
    @Description("Resets all the game and his configuration")
    public void onReset(CommandSender sender) {
        gameManager.resetGame();
        StringUtils.broadcast("The game has been reset");
    }

    @Subcommand("config")
    @CommandPermission("ctm.op")
    @Description("Opens the config gui")
    public void onConfig(Player p) {
        Utils.openGameConfigInv(p);
    }

    @Subcommand("start")
    @CommandPermission("ctm.op")
    @Description("Starts the game")
    public void onStart(CommandSender sender, GameManager.GameMode gameMode) {
        if (gameManager.getGameState() == GameManager.GameState.PLAYING) {
            StringUtils.sendMessage(sender, "Este comando solo puede ser ejecutado una vez por partida!");
            return;
        }

        gameManager.startGame(gameMode);
    }

    @Subcommand("timer")
    @CommandPermission("ctm.op")
    @Description("Manipulates the timer")
    public class TimerCommand extends BaseCommand {
        @Default @CatchUnknown @HelpCommand
        public void onHelp(CommandSender sender, CommandHelp help) {
            help.showHelp();
        }

        @Subcommand("set")
        @Description("Set the game time to an specific second")
        public void onTimerSet(CommandSender sender, int seconds) {
            gameManager.getTimer().setTime(seconds);
            StringUtils.broadcast("Timer set to &3" + StringUtils.formatTime(seconds));
        }

        @Subcommand("stop")
        @Description("Stops the timer")
        public void onTimerStop(CommandSender sender) {
            gameManager.getTimer().stopTimer();
            StringUtils.broadcast("Timer stopped");
        }

        @Subcommand("resume")
        @Description("Resumes the timer")
        public void onTimerResume(CommandSender sender) {
            gameManager.getTimer().startTimer();
            StringUtils.broadcast("Timer resumed from &3" + gameManager.getTimer().getCurrentTimeFormatted());
        }
    }

    @Subcommand("playing")
    @CommandPermission("ctm.op")
    @Description("Manipulates the player's list")
    public class PlayingCommand extends BaseCommand {

        @Default @CatchUnknown @HelpCommand
        public void onHelp(CommandSender sender, CommandHelp help) {
            help.showHelp();
        }

        @Subcommand("add")
        @Description("Adds a player to the player's list")
        public void onAdd(CommandSender sender, OnlinePlayer player) {
            Player p = player.getPlayer();

            if (gameManager.isPlaying(p)) {
                StringUtils.sendMessage(sender, p.getName() + " ya esta en la lista de jugadores!");
                return;
            }

            plugin.getGameManager().getPlaying().add(p.getUniqueId());
            StringUtils.sendMessage(sender, "Agregaste a " + p.getName() + " a la lista de jugadores");
        }

        @Subcommand("remove")
        @Description("Removes a player from the player's list")
        public void onRemove(CommandSender sender, OnlinePlayer player) {
            Player p = player.getPlayer();

            if (!plugin.getGameManager().isPlaying(p)) {
                StringUtils.sendMessage(sender, p.getName() + " ya esta fuera de la lista de jugadores!");
                return;
            }

            plugin.getGameManager().getPlaying().remove(p.getUniqueId());
            StringUtils.sendMessage(sender, "Sacaste a " + p.getName() + " de la lista de jugadores");
        }
    }
}
