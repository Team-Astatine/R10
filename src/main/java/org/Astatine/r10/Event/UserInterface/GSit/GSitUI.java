package org.Astatine.r10.Event.UserInterface.GSit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Event.UserInterface.Function.Interface.Type;
import org.Astatine.r10.Event.UserInterface.Function.Interface.UIHolder;
import org.Astatine.r10.Event.UserInterface.Function.Interface.UIType;
import org.Astatine.r10.Event.UserInterface.Function.UIGenerator.CreatePanelItem;
import org.Astatine.r10.Event.UserInterface.Function.UIGenerator.InventoryUIGenerator;
import org.Astatine.r10.Event.UserInterface.Function.UIGenerator.SlotItemMapping;
import org.Astatine.r10.Util.Function.StringComponentExchanger;

import java.util.ArrayList;

@UIType(Type.GSIT)
public class GSitUI extends StringComponentExchanger implements UIHolder {
    private Player chestOwner;
    private Inventory inventory;
    private int slotCount;

    public GSitUI(Player player) {
        this.chestOwner = player;
        this.slotCount = 9;
        UIExecutor();
    }

    @Override
    public Player getOwner() {
        return this.chestOwner;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public void UIExecutor() {
        /**
         * 총 4개의 매뉴, 각 메뉴별로 아이템으로 표기예정
         * ⬜️🟧⬜️🟧⬜️🟧⬜️🟧⬜️
         * 0 1  2 3 4 5  6 7 8
         */

        this.inventory = new InventoryUIGenerator()
                .bindHolder(this)
                .inventoryGenerator(
                        this.slotCount,
                        componentExchanger("행동 상호작용", ColorType.PINK)
                )
                .setEnhanceUIItem(itemPanelList())
                .executeUI();
    }

    private ArrayList<SlotItemMapping> itemPanelList() {
        ArrayList<SlotItemMapping> result = new ArrayList<>();
        for (int i = 0; i < this.slotCount; i++)
            result.add(new SlotItemMapping(
                    i,
                    createItem(
                            Material.WHITE_STAINED_GLASS_PANE,
                            "",
                            ColorType.WHITE
                    )
                )
            );

        result.add(new SlotItemMapping(
                1,
                createButtonItem(
                Material.BRICK_STAIRS,
                "앉기",
                ColorType.ORANGE)
        ));

        result.add(new SlotItemMapping(
                3,
                createButtonItem(
                Material.RED_BED,
                "눕기",
                ColorType.ORANGE
                )
        ));

        result.add(new SlotItemMapping(
                5,
                createButtonItem(
                Material.TRIDENT,
                "돌기",
                ColorType.ORANGE
                )
        ));

        result.add(new SlotItemMapping(
                7,
                createButtonItem(
                Material.IRON_TRAPDOOR,
                "기어가기",
                ColorType.ORANGE
        )
        ));

        return result;
    }

    private ItemStack createItem(Material material, String comment, ColorType color) {
        return new CreatePanelItem()
                .setPanelItem(material)
                .setDisplayName(comment, color)
                .isEnchantGlowing(false)
                .createItem();
    }

    private ItemStack createButtonItem(Material material, String comment, ColorType color) {
        return new CreatePanelItem()
                .setPanelItem(material)
                .setDisplayName(comment, color)
                .isEnchantGlowing(true)
                .createItem();
    }
}
