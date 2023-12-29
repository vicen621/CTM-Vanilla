package io.github.vicen621.ctmvanilla.Utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionType;

import java.util.*;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class ItemBuilder {
    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
    private final ItemMeta meta;
    private final ItemStack item;

    public ItemBuilder(Material material) {
        this(new ItemStack(material));
    }

    public ItemBuilder(ItemStack item) {
        this.item = Objects.requireNonNull(item, "item");
        this.meta = item.getItemMeta();

        if (this.meta == null) {
            throw new IllegalArgumentException("The type " + item.getType() + " doesn't support item meta");
        }
    }

    public ItemBuilder type(Material material) {
        this.item.setType(material);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        this.meta.setUnbreakable(unbreakable);
        return this;
    }

    public ItemBuilder damage(int damage) {
        return meta(Damageable.class, m -> m.setDamage(damage));
    }

    public ItemBuilder amount(int amount) {
        this.item.setAmount(amount);
        return this;
    }

    public ItemBuilder enchant(Enchantment enchantment) {
        return enchant(enchantment, 1);
    }

    public ItemBuilder enchant(Enchantment enchantment, int level) {
        this.meta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder removeEnchant(Enchantment enchantment) {
        this.meta.removeEnchant(enchantment);
        return this;
    }

    public ItemBuilder removeEnchants() {
        this.meta.getEnchants().keySet().forEach(this.meta::removeEnchant);
        return this;
    }

    public ItemBuilder meta(Consumer<ItemMeta> metaConsumer) {
        metaConsumer.accept(this.meta);
        return this;
    }

    public <T extends ItemMeta> ItemBuilder meta(Class<T> metaClass, Consumer<T> metaConsumer) {
        if (metaClass.isInstance(this.meta)) {
            metaConsumer.accept(metaClass.cast(this.meta));
        }
        return this;
    }

    public ItemBuilder name(String name) {
        this.meta.displayName(MINI_MESSAGE.deserialize(name));
        return this;
    }

    public ItemBuilder lore(String lore) {
        return lore(Collections.singletonList(lore));
    }

    public ItemBuilder lore(String... lore) {
        return lore(Arrays.asList(lore));
    }

    public ItemBuilder lore(List<String> lore) {
        this.meta.lore(lore.stream().map(MINI_MESSAGE::deserialize).toList());
        return this;
    }

    public ItemBuilder addLore(String line) {
        return addLore(Collections.singletonList(line));
    }

    public ItemBuilder addLore(String... lines) {
        return addLore(Arrays.asList(lines));
    }

    public ItemBuilder addLore(List<String> lines) {
        List<Component> lore = this.meta.lore();

        if (lore == null || lore.isEmpty()) {
            return lore(lines);
        }

        lore.addAll(lines.stream().map(MINI_MESSAGE::deserialize).toList());
        this.meta.lore(lore);
        return this;
    }

    public ItemBuilder flags(ItemFlag... flags) {
        this.meta.addItemFlags(flags);
        return this;
    }

    public ItemBuilder setCustomModelData(int data) {
        this.meta.setCustomModelData(data);
        return this;
    }

    public ItemBuilder storeEnchant(Enchantment enchantment, int level, boolean lvlRestriction) {
        return meta(EnchantmentStorageMeta.class, m -> m.addStoredEnchant(enchantment, level, lvlRestriction));
    }

    public ItemBuilder flags() {
        return flags(ItemFlag.values());
    }

    public ItemBuilder removeFlags(ItemFlag... flags) {
        this.meta.removeItemFlags(flags);
        return this;
    }

    public ItemBuilder removeFlags() {
        return removeFlags(ItemFlag.values());
    }

    public ItemBuilder armorColor(Color color) {
        return meta(LeatherArmorMeta.class, m -> m.setColor(color));
    }

    public ItemBuilder potionColor(Color color) {
        return meta(PotionMeta.class, m -> m.setColor(color));
    }

    public ItemBuilder setOwner(OfflinePlayer p) {
        return meta(SkullMeta.class, m -> m.setOwningPlayer(p));
    }

    public ItemBuilder setBasePotionType(PotionType potionType) {
        return meta(PotionMeta.class, m -> m.setBasePotionType(potionType));
    }

    public ItemBuilder addAttribute(Attribute attribute, AttributeModifier modifier) {
        this.meta.addAttributeModifier(attribute, modifier);
        return this;
    }

    public ItemBuilder setPersistentDataContainer(NamespacedKey key, String content) {
        this.meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, content);
        return this;
    }

    public ItemStack build() {
        this.item.setItemMeta(this.meta);
        return this.item;
    }
}