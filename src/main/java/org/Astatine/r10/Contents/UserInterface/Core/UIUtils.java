package org.Astatine.r10.Contents.UserInterface.Core;

import net.ess3.api.IEssentials;
import net.ess3.api.IUser;
import net.kyori.adventure.text.Component;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Contents.UserInterface.Core.UIGenerator.CreatePanelItem;
import org.Astatine.r10.Contents.UserInterface.Core.UIGenerator.SlotItemMapping;
import org.Astatine.r10.Util.Function.StringComponentExchanger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public abstract class UIUtils extends StringComponentExchanger {
    public final int MINIUM_TAB_CNT = 9;

    private IEssentials getEssentialsAPI() {
        return (IEssentials) Bukkit.getServer()
                .getPluginManager()
                .getPlugin("Essentials");
    }

    public IUser getEssentialPluginUserObject(Player player) {
        return getEssentialsAPI().getUser(player.getUniqueId());
    }

    public void performCommand(Player player, String command, String targetName) {
        player.performCommand(String.format("%s %s", command, targetName));
    }

    public ItemStack getHeadItemStack(Player player) {
        ItemStack headItemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta data = (SkullMeta) headItemStack.getItemMeta();
        data.setOwningPlayer(player);
        headItemStack.setItemMeta(data);
        return headItemStack;
    }

    public List<SlotItemMapping> defaultPanelItems(int size) {
        return IntStream.range(0, size)
            .mapToObj(i -> new SlotItemMapping(
                i,
                createItem(Material.WHITE_STAINED_GLASS_PANE, "", ColorType.WHITE, false)
            ))
            .collect(Collectors.toList());
    }

    public ItemStack createItem(Material material, String comment, ColorType color, boolean glowing) {
        return createItem(material, comment, color, glowing, Collections.emptyList());
    }

    public ItemStack createItem(Material material, String comment, ColorType color, boolean glowing, List<Component> lore) {
        return new CreatePanelItem()
            .setPanelItem(material)
            .setDisplayName(comment, color)
            .setLore(new ArrayList<>(lore))
            .isEnchantGlowing(glowing)
            .createItem();
    }

    public ItemStack createItem(ItemStack targetItem, String comment, ColorType color, boolean glowing, List<Component> lore) {
        return new CreatePanelItem()
                .setPanelItem(targetItem)
                .setDisplayName(comment, color)
                .setLore(new ArrayList<>(lore))
                .isEnchantGlowing(false)
                .createItem();
    }
}
