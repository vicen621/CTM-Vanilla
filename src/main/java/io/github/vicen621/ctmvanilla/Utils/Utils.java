package io.github.vicen621.ctmvanilla.Utils;

import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import io.github.rysefoxx.inventory.plugin.pattern.ContentPattern;
import io.github.vicen621.ctmvanilla.Main;
import io.github.vicen621.ctmvanilla.game.GameManager;
import lombok.experimental.UtilityClass;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/*
 * Copyright (c) 2021 Vicen621.
 * All rights reserved.
 */
@UtilityClass
public class Utils {
    private final Color[] COLORS = new Color[]{
            Color.AQUA, Color.BLACK, Color.BLUE, Color.FUCHSIA, Color.GRAY, Color.GREEN, Color.LIME, Color.MAROON,
            Color.NAVY, Color.OLIVE, Color.ORANGE, Color.PURPLE, Color.RED, Color.SILVER, Color.TEAL, Color.WHITE,
            Color.YELLOW
    };

    public boolean isInt(String s) {
        try {
            Float.parseFloat(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static int getTotalDeaths() {
        int deaths = 0;

        for (UUID uuid : Main.getInstance().getGameManager().getPlaying()) {
            OfflinePlayer p = Bukkit.getOfflinePlayer(uuid);
            deaths += p.getStatistic(Statistic.DEATHS);
        }

        return deaths;
    }

    public String environment(World world) {
        return switch (world.getEnvironment()) {
            case NORMAL -> "Overworld";
            case NETHER -> "Nether";
            case THE_END -> "The End";
            default -> "Unknown";
        };
    }

    public Player getRandomPlayer() {
        List<UUID> players = Main.getInstance().getGameManager().getPlaying();
        return Bukkit.getPlayer(players.get(new Random().nextInt(players.size())));
    }

    public void spawnFirework() {
        Player p = getRandomPlayer();
        Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();

        // Our random generator
        Random r = new Random();

        FireworkEffect.Type[] types = FireworkEffect.Type.values();
        FireworkEffect.Type type = FireworkEffect.Type.valueOf(types[r.nextInt(types.length)].toString());

        //Create our effect with this
        FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean())
                .withColor(Utils.getRandomColor()).withFade(Utils.getRandomColor()).with(type)
                .trail(r.nextBoolean()).build();

        //Then apply the effect to the meta
        fwm.addEffect(effect);
        fwm.setPower(r.nextInt(2) + 1);

        //Then apply this to our rocket
        fw.setFireworkMeta(fwm);
    }

    public Color getRandomColor() {
        return COLORS[ThreadLocalRandom.current().nextInt(COLORS.length)];
    }

    public void openGameConfigInv(Player p) {
        GameManager gameManager = Main.getInstance().getGameManager();
        RyseInventory inv = RyseInventory.builder()
                .title("Config inventory")
                .rows(1)
                .disableUpdateTask()
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {
                        ContentPattern pattern = contents.contentPattern();
                        pattern.define("XMXXUXXRX");

                        pattern.set('X', new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name(" ").build());

                        pattern.set('M', IntelligentItem.of(
                                new ItemBuilder(Material.DIAMOND_ORE)
                                        .name("&7Minerals mode")
                                        .lore(" &3» " + getBoolean(gameManager.isMinerals()))
                                        .build(),
                                e -> {
                                    gameManager.toggleMinerals();
                                    openGameConfigInv(p);
                                })
                        );

                        pattern.set('U', IntelligentItem.of(
                                new ItemBuilder(Material.GOLDEN_APPLE)
                                        .name("&7UHC mode")
                                        .lore(" &3» " + getBoolean(gameManager.isUhc()))
                                        .build(),
                                e -> {
                                    gameManager.toggleUHC();
                                    openGameConfigInv(p);
                                })
                        );

                        pattern.set('R', IntelligentItem.of(
                                new ItemBuilder(Material.CAKE)
                                        .name("&7Rewards mode")
                                        .lore(" &3» " + getBoolean(gameManager.isRewards()))
                                        .build(),
                                e -> {
                                    gameManager.toggleRewards();
                                    openGameConfigInv(p);
                                })
                        );
                    }
                })
                .build(Main.getInstance());

        inv.open(p);
    }

    /**
     * Get a Boolean and return a String
     *
     * @param bool boolean
     * @return &bOn if is true, &cOff if is false
     */
    public String getBoolean(boolean bool) {
        return bool ? "&bOn" : "&cOff";
    }
}
