package io.github.vicen621.ctmvanilla.game.wool;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;

@Getter
@Setter
public class Wool {

    private final Material material;
    private final String name;
    private final boolean mineral;
    private boolean taken;

    public Wool(Material material, String name) {
        this(material, name, false);
    }

    public Wool(Material material, String name, boolean mineral) {
        this.material = material;
        this.name = name;
        this.mineral = false;
        this.taken = false;
    }

    @UtilityClass
    public static class NormalWools {
        private final Wool WHITE_WOOL = new Wool(Material.CAKE, "White");
        private final Wool ORANGE_WOOL = new Wool(Material.DAMAGED_ANVIL, "Orange");
        private final Wool MAGENTA_WOOL = new Wool(Material.COBWEB, "Magenta");
        private final Wool LIGHT_BLUE_WOOL = new Wool(Material.BEETROOT_SOUP, "Light_blue");
        private final Wool YELLOW_WOOL = new Wool(Material.OBSERVER, "Yellow");
        private final Wool LIME_WOOL = new Wool(Material.EXPERIENCE_BOTTLE, "Lime");
        private final Wool PINK_WOOL = new Wool(Material.ENDER_CHEST, "Pink");
        private final Wool GRAY_WOOL = new Wool(Material.NAUTILUS_SHELL, "Gray");
        private final Wool LIGHT_GRAY_WOOL = new Wool(Material.DIAMOND_BLOCK, "Light_gray");
        private final Wool CYAN_WOOL = new Wool(Material.TOTEM_OF_UNDYING, "Cyan");
        private final Wool PURPLE_WOOL = new Wool(Material.MUSIC_DISC_11, "Purple");
        private final Wool BLUE_WOOL = new Wool(Material.SPONGE, "Blue");
        private final Wool BROWN_WOOL = new Wool(Material.BEE_NEST, "Brown");
        private final Wool GREEN_WOOL = new Wool(Material.END_CRYSTAL, "Green");
        private final Wool RED_WOOL = new Wool(Material.DRAGON_EGG, "Red");
        private final Wool BLACK_WOOL = new Wool(Material.TIPPED_ARROW, "Black");

        public final Wool[] NORMAL_WOOLS = new Wool[]{
                WHITE_WOOL, ORANGE_WOOL, MAGENTA_WOOL, LIGHT_BLUE_WOOL, YELLOW_WOOL, LIME_WOOL, PINK_WOOL, GRAY_WOOL,
                LIGHT_GRAY_WOOL, CYAN_WOOL, PURPLE_WOOL, BLUE_WOOL, BROWN_WOOL, GREEN_WOOL, RED_WOOL, BLACK_WOOL
        };
    }

    @UtilityClass
    public static class HardWools {
        private final Wool WHITE_WOOL = new Wool(Material.CAKE, "White");
        private final Wool ORANGE_WOOL = new Wool(Material.DAMAGED_ANVIL, "Orange");
        private final Wool MAGENTA_WOOL = new Wool(Material.RABBIT_STEW, "Magenta");
        private final Wool LIGHT_BLUE_WOOL = new Wool(Material.OBSERVER, "Light_blue");
        private final Wool YELLOW_WOOL = new Wool(Material.HEART_OF_THE_SEA, "Yellow");
        private final Wool LIME_WOOL = new Wool(Material.SKULL_BANNER_PATTERN, "Lime");
        private final Wool PINK_WOOL = new Wool(Material.ENDER_CHEST, "Pink");
        private final Wool GRAY_WOOL = new Wool(Material.TRIDENT, "Gray");
        private final Wool LIGHT_GRAY_WOOL = new Wool(Material.TOTEM_OF_UNDYING, "Light_gray");
        private final Wool CYAN_WOOL = new Wool(Material.MUSIC_DISC_11, "Cyan");
        private final Wool PURPLE_WOOL = new Wool(Material.SPONGE, "Purple");
        private final Wool BLUE_WOOL = new Wool(Material.BEE_NEST, "Blue");
        private final Wool BROWN_WOOL = new Wool(Material.END_CRYSTAL, "Brown");
        private final Wool GREEN_WOOL = new Wool(Material.DRAGON_EGG, "Green");
        private final Wool RED_WOOL = new Wool(Material.TIPPED_ARROW, "Red");
        private final Wool BLACK_WOOL = new Wool(Material.CYAN_SHULKER_BOX, "Black");

        public final Wool[] HARD_WOOLS = new Wool[]{
                WHITE_WOOL, ORANGE_WOOL, MAGENTA_WOOL, LIGHT_BLUE_WOOL, YELLOW_WOOL, LIME_WOOL, PINK_WOOL, GRAY_WOOL,
                LIGHT_GRAY_WOOL, CYAN_WOOL, PURPLE_WOOL, BLUE_WOOL, BROWN_WOOL, GREEN_WOOL, RED_WOOL, BLACK_WOOL
        };
    }

    @UtilityClass
    public static class Minerals {
        private final Wool COAL_BLOCK = new Wool(Material.COAL_BLOCK, "Block of Coal", true);
        private final Wool IRON_BLOCK = new Wool(Material.IRON_BLOCK, "Block of Iron", true);
        private final Wool GOLD_BLOCK = new Wool(Material.GOLD_BLOCK, "Block of Gold", true);
        private final Wool LAPIS_BLOCK = new Wool(Material.LAPIS_BLOCK, "Block of Lapis", true);
        private final Wool REDSTONE_BLOCK = new Wool(Material.REDSTONE_BLOCK, "Block of Redstone", true);
        private final Wool EMERALD_BLOCK = new Wool(Material.EMERALD_BLOCK, "Block of Emerald", true);
        private final Wool DIAMOND_BLOCK = new Wool(Material.DIAMOND_BLOCK, "Block of Diamond", true);

        public final Wool[] MINERALS = new Wool[]{
                COAL_BLOCK, IRON_BLOCK, GOLD_BLOCK, LAPIS_BLOCK, REDSTONE_BLOCK, EMERALD_BLOCK, DIAMOND_BLOCK
        };
    }
}
