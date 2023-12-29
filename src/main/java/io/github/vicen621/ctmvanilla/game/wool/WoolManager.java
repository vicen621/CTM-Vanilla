package io.github.vicen621.ctmvanilla.game.wool;

import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.Utils.StringUtils;
import io.github.vicen621.ctmvanilla.game.GameManager;
import lombok.Getter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@SuppressWarnings("unused")
public class WoolManager {

    private final Main plugin;
    private final Set<Wool> wools;
    private final Set<Material> materials;
    private int obtainedWools;
    private int obtainedMinerals;

    public WoolManager(Main plugin) {
        this.plugin = plugin;
        wools = new HashSet<>();
        materials = new HashSet<>();
        obtainedWools = 0;
        obtainedMinerals = 0;
    }

    public void registerWools(GameManager.GameMode mode, boolean minerals) {
        wools.clear();
        materials.clear();

        switch (mode) {
            case NORMAL -> wools.addAll(Arrays.stream(Wool.NormalWools.NORMAL_WOOLS).toList());
            case HARD -> wools.addAll(Arrays.stream(Wool.HardWools.HARD_WOOLS).toList());
            case NIGHTMARE -> {/*TODO*/}
        }

        if (minerals)
            wools.addAll(Arrays.stream(Wool.Minerals.MINERALS).toList());

        materials.addAll(wools.stream().map(Wool::getMaterial).toList());
    }

    public void resetWools() {
        wools.clear();
        materials.clear();
        obtainedWools = 0;
        obtainedMinerals = 0;
    }

    public boolean isWoolMaterial(Material mat) {
        return materials.contains(mat);
    }

    public Optional<Wool> getWoolByMaterial(Material mat) {
        return wools.stream().filter(wool -> wool.getMaterial() == mat).findFirst();
    }

    public Optional<Wool> getWoolByColor(String color) {
        return wools.stream().filter(wool -> wool.getName().equalsIgnoreCase(color)).findFirst();
    }

    public void woolObtained(Wool wool, Player player, String currentTime) {
        wool.setTaken(true);

        if (wool.isMineral())
            obtainedMinerals++;
        else
            obtainedWools++;

        if (plugin.getGameManager().isRewards())
            player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));

        StringUtils.broadcast("", "<gold>" + wool.getName() + (wool.isMineral() ? "" : " wool") + " obtained in " + currentTime + ". Awesome!");

        MiniMessage mm = MiniMessage.miniMessage();

        Title title = Title.title(
                mm.deserialize(
                        "<white><bold><name><item>",
                        Placeholder.unparsed("name", wool.getName()),
                        Placeholder.unparsed("item", wool.isMineral() ? "" : " item")
                ),
                mm.deserialize(
                        "<gold>Obtained by <player>",
                        Placeholder.unparsed("player", player.getName())
                )
        );

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.showTitle(title);
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
        }

        if (getObtainedWools() + getObtainedMinerals() == getWools().size())
            plugin.getGameManager().won();
    }
}
