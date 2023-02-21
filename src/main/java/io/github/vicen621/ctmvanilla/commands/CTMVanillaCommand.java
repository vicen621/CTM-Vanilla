package io.github.vicen621.ctmvanilla.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.HelpCommand;
import org.bukkit.command.CommandSender;

@CommandAlias("ctm|ctmfv|ctmvanilla")
public class CTMVanillaCommand extends BaseCommand {

    @Default @CatchUnknown @HelpCommand
    public void onDefault(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

    @CommandAlias("guide")
    public static class GuideCommand extends BaseCommand {

        @Default @CatchUnknown @HelpCommand
        public void onDefault(CommandSender sender, CommandHelp help) {
            help.showHelp();
        }


    }
}
