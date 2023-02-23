package io.github.vicen621.ctmvanilla.game.wool;

import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.Utils.StringUtils;
import io.github.vicen621.ctmvanilla.game.GameManager;
import lombok.Getter;
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
        if (wool.isTaken())
            return;

        wool.setTaken(true);

        if (wool.isMineral())
            obtainedMinerals++;
        else
            obtainedWools++;

        if (plugin.getGameManager().isRewards())
            player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));

        StringUtils.broadcast("", "&6" + wool.getName() + (wool.isMineral() ? "" : " wool") + " obtained in " + currentTime + ". Awesome!");

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendTitle(StringUtils.chat("&f&l" + wool.getName() + (wool.isMineral() ? "" : " item")), StringUtils.chat("&6Obtained by " + player.getName()));
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
        }

        if (getObtainedWools() + getObtainedMinerals() == getWools().size()) {
            plugin.getGameManager().won();
        }
    }
}
