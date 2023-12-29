package io.github.vicen621.ctmvanilla.config.serializers;

import de.exlll.configlib.Serializer;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class WorldToStringSerializer implements Serializer<World, String> {

    @Override
    public String serialize(World element) {
        return element.getName();
    }

    @Override
    public World deserialize(String element) {
        return Bukkit.getWorld(element);
    }
}