package io.github.vicen621.ctmvanilla.commands;

import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.Utils.StringUtils;
import io.github.vicen621.ctmvanilla.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

/*
 * Copyright (c) 2021 Vicen621.
 * All rights reserved.
 */

//TODO Agregar los 2 comandos a la config para que puedan ser modificables
//description: gives the guide to the player
public class Guides implements CommandExecutor {

    private final Main plugin;

    public Guides(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("guide").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("guide")) {
            if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage(StringUtils.chat("&cThis command only can be used by players"));
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage(StringUtils.chat("&cPlease specify: normal | hardmode"));
                return true;
            }
            if (args.length == 1 && args[0].equalsIgnoreCase("normal")) {
                plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + sender.getName() + " written_book{pages:['[\"\",{\"text\":\" \\\\u0020 \\\\u0020 CTM Vanilla\",\"bold\":true,\"color\":\"gold\"},{\"text\":\"\\\\n\",\"color\":\"reset\",\"bold\":true},{\"text\":\" \\\\u0020 \\\\u0020 SURVIVAL MODE\",\"bold\":true,\"color\":\"red\"},{\"text\":\"\\\\n\\\\nThis is a completly new gamemode designed and made by ElRichMC\\\\n\\\\nComplete The Monument Vanilla is a cooperative gamemode in a random seed where \",\"color\":\"reset\"},{\"text\":\"you has to find 16 diferent items.\",\"underlined\":true}]','[\"\",{\"text\":\"You can find the items without a specific order using whatever you need. You can\\'t use cheats or glitches.\\\\n\\\\n\"},{\"text\":\" \\\\u0020 \\\\u0020 \\\\u0020 \\\\u0020ITEM LIST\",\"bold\":true,\"color\":\"light_purple\"},{\"text\":\"\\\\n\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 White Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n \\\\u0020Cake\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Orange Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\" \\\\u0020Very Damaged Anvil\",\"color\":\"black\"}]','[\"\",{\"text\":\"\\\\u2b25 Magenta Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n  Cobweb\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Light Blue Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n  Beetroot Soup\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Yellow Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n  Observer\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Lime Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n  Bottle o\\' Enchanting\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Pink Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"  Ender Chest\",\"color\":\"black\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Gray Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n  Nautilus Shell\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Light Gray Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n  Diamond Block\",\"color\":\"reset\"}]','[\"\",{\"text\":\"\\\\u2b25 Cyan Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n  Undying Totem\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Purple Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n  Music Disc 11\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Blue Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n  Sponge\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Brown Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\nBee Nest\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Green Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"  Ender Crystal\\\\n\"},{\"text\":\"\\\\u2b25 Red Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"  Dragon Egg\\\\n\"},{\"text\":\"\\\\u2b25 Black Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n  Tipped Arrow\",\"color\":\"reset\"}]','[\"\",{\"text\":\" \\\\u0020 \\\\u0020 \\\\u0020 MINERALS\",\"bold\":true,\"color\":\"red\"},{\"text\":\"\\\\n\\\\u2b25 Coal Block\\\\n\\\\u2b25 Iron Block\\\\n\\\\u2b25 Gold Block\\\\n\\\\u2b25 Lapislazuli Block\\\\n\\\\u2b25 Redstone Block\\\\n\\\\u2b25 Emerald Block\\\\n\\\\u2b25 Diamond Block\\\\n\\\\nIf you or your team can get all of the items you\\'ll win the game!\",\"color\":\"reset\"}]','{\"text\":\"There\\'s three types of games to play CTM Vanilla:\\\\n\\\\n\\\\u2b25 Survival\\\\n\\\\u2b25 UHC\\\\n\\\\u2b25 HardMode\"}','[\"\",{\"text\":\"Commands\\\\n\\\\n\\\\u2560\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2563\\\\n\\\\n/start \"},{\"text\":\"normal | hardmode\",\"color\":\"red\"},{\"text\":\"\\\\n\\\\n/uhc (toggle\\'s UHC mode)\\\\n\\\\n/minerals (toggle\\'s Minerals mode\",\"color\":\"reset\"}]'],title:\"CTM Vanilla guide\",author:\"CTM Vanilla\"}");
                return true;
            } else if (args.length == 1 && args[0].equalsIgnoreCase("hardmode")) {
                plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + sender.getName() + " written_book{pages:['[\"\",{\"text\":\" \\\\u0020 CTM Vanilla\",\"bold\":true,\"color\":\"gold\"},{\"text\":\"\\\\n\",\"color\":\"reset\",\"bold\":true},{\"text\":\" \\\\u0020 \\\\u0020 HARDMODE\",\"bold\":true,\"color\":\"red\"},{\"text\":\"\\\\n\\\\nThis is a completly new gamemode designed and made by ElRichMC\\\\n\\\\nComplete The Monument Vanilla is a cooperative gamemode in a random seed where \",\"color\":\"reset\"},{\"text\":\"you has to find 16 diferent items.\",\"underlined\":true}]','{\"text\":\"You can find the items without a specific order using whatever you need. You can\\'t use cheats or glitches.\\\\n\\\\nThe hardmode further increases the gameplay with new items and reduced time to obtain them.\"}','[\"\",{\"text\":\"     ITEM LIST\",\"bold\":true,\"color\":\"light_purple\"},{\"text\":\"\\\\n\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 White Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n   Cake\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Orange Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"  Damaged Anvil\\\\n\"},{\"text\":\"\\\\u2b25 Magent Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n  Rabbit Stew\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Light blue Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n  Observer\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Yellow Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n  Heart Of The Sea\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Lime Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"  Skull Charge Pattern\"}]','[\"\",{\"text\":\"\\\\u2b25 Pink Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"  Ender Chest\\\\n\"},{\"text\":\"\\\\u2b25 Gray Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n  Trident\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Light Gray Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"  Totem Of Undying\\\\n\"},{\"text\":\"\\\\u2b25 Cyan Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"  Music Disc 11\\\\n\"},{\"text\":\"\\\\u2b25 Purple Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"  Sponge\\\\n\"},{\"text\":\"\\\\u2b25 Blue Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"  Bee Nest\\\\n\"},{\"text\":\"\\\\u2b25 Brown Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"  End Crystal\",\"bold\":true}]','[\"\",{\"text\":\"\\\\u2b25 Green Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"  Dragon Egg\",\"bold\":true},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Red Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"  Tipped Arrow\",\"bold\":true},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"\\\\u2b25 Black Item:\",\"color\":\"#3DEFEF\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"  Cyan Shulker Box\\\\n     \",\"bold\":true},{\"text\":\"MINERALS\",\"bold\":true,\"color\":\"red\"},{\"text\":\"\\\\n\\\\u2b25 Coal Block\\\\n\\\\u2b25 Iron Block\\\\n\\\\u2b25 Gold Block\\\\n\\\\u2b25 Lapislazuli Block\\\\n\\\\u2b25 Redstone Block\\\\n\\\\u2b25 Emerald Block\\\\n\\\\u2b25 Diamond Block\",\"color\":\"reset\"}]','{\"text\":\"If you or your team can get all of the items you\\'ll win the game!\\\\n\\\\nThere\\'s three types of games to play CTM Vanilla:\\\\n\\\\n\\\\u2b25 Survival\\\\n\\\\u2b25 UHC\\\\n\\\\u2b25 HardMode\"}','[\"\",{\"text\":\"Commands\\\\n\\\\n\\\\u2560\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2550\\\\u2563\\\\n\\\\n/start \"},{\"text\":\"normal | hardmode\",\"color\":\"red\"},{\"text\":\"\\\\n\\\\n/uhc (toggle\\'s UHC mode)\\\\n\\\\n/minerals (toggle\\'s Minerals mode\",\"color\":\"reset\"}]'],title:\"A guide to CTM Vanilla: HardMode\",author:\"CTM Vanilla\"}");
                return true;
            }
        }
        return false;
    }
}

