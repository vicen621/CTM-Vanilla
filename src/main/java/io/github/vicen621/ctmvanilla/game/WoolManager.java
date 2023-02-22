package io.github.vicen621.ctmvanilla.game;

import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.Utils.StringUtils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

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
    }

    public void registerWools(GameManager.GameMode mode) {
        wools.clear();
        materials.clear();

        switch (mode) {
            case NORMAL -> wools.addAll(Arrays.stream(Wool.NormalWools.NORMAL_WOOLS).toList());
            case HARD -> wools.addAll(Arrays.stream(Wool.HardWools.HARD_WOOLS).toList());
            case NIGHTMARE -> {/*TODO*/}
        }

        materials.addAll(wools.stream().map(Wool::getMaterial).toList());
    }

    public void toggleMinerals(boolean toggle) {
        if (toggle) {
            wools.addAll(Arrays.stream(Wool.Minerals.MINERALS).toList());
            materials.addAll(Arrays.stream(Wool.Minerals.MINERALS).map(Wool::getMaterial).toList());
            return;
        }

        Arrays.stream(Wool.Minerals.MINERALS).toList().forEach(wools::remove);
        Arrays.stream(Wool.Minerals.MINERALS).map(Wool::getMaterial).toList().forEach(materials::remove);
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

    public void woolObtained(Wool wool, String player) {
        if (wool.isTaken())
            return;

        wool.setTaken(true);

        if (wool.isMineral())
            obtainedMinerals++;
        else
            obtainedWools++;

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendTitle(StringUtils.chat("&f&l" + wool.getName() + " item"), StringUtils.chat("&6Obtained by " + player));
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
        }
    }
}
