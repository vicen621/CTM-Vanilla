package io.github.vicen621.ctmvanilla.config;

import de.exlll.configlib.ConfigLib;
import de.exlll.configlib.NameFormatters;
import de.exlll.configlib.YamlConfigurationProperties;
import de.exlll.configlib.YamlConfigurations;
import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.config.serializers.WorldToStringSerializer;
import org.bukkit.World;

import java.io.File;

public class ConfigManager<C> {
    private final Main plugin;
    private final YamlConfigurationProperties PROPERTIES = ConfigLib.BUKKIT_DEFAULT_PROPERTIES.toBuilder()
            .header("""
                                                Plugin made by:
                         ██╗░░░██╗██╗░█████╗░███████╗███╗░░██╗░█████╗░██████╗░░░███╗░░
                         ██║░░░██║██║██╔══██╗██╔════╝████╗░██║██╔═══╝░╚════██╗░████║░░
                         ╚██╗░██╔╝██║██║░░╚═╝█████╗░░██╔██╗██║██████╗░░░███╔═╝██╔██║░░
                         ░╚████╔╝░██║██║░░██╗██╔══╝░░██║╚████║██╔══██╗██╔══╝░░╚═╝██║░░
                         ░░╚██╔╝░░██║╚█████╔╝███████╗██║░╚███║╚█████╔╝███████╗███████╗
                         ░░░╚═╝░░░╚═╝░╚════╝░╚══════╝╚═╝░░╚══╝░╚════╝░╚══════╝╚══════╝
                                                
                        You can use any Placeholder API placeholder
                        Also this plugin adds this placeholders to the Placeholder API plugin:
                          %ctmv_gamemode%: Shows the current gamemode
                          %ctmv_obtained_wools%: Displays your currents wools
                          %ctmv_time_Played%: Displays the time played
                          %ctmv_total_deaths%: Displays the deaths of all teams
                        """)
            .setNameFormatter(NameFormatters.LOWER_UNDERSCORE)
            .addSerializer(World.class, new WorldToStringSerializer())
            .build();
    private final String name;
    private final Class<C> clazz;
    private C config;

    public ConfigManager(Main plugin, String name, Class<C> clazz) {
        this.plugin = plugin;
        this.name = name;
        this.clazz = clazz;
        updateConfig();
    }

    public void updateConfig() {
        config = YamlConfigurations.update(new File(plugin.getDataFolder(), name).toPath(), clazz, PROPERTIES);
    }

    public void saveConfig() {
        YamlConfigurations.save(new File(plugin.getDataFolder(), name).toPath(), clazz, config, PROPERTIES);
    }

    public C getConfig() {
        return config;
    }
}