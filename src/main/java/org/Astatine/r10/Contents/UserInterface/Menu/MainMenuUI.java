package org.Astatine.r10.Contents.UserInterface.Menu;

import org.Astatine.r10.Contents.UserInterface.Core.UIUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Contents.UserInterface.Core.Interface.Type;
import org.Astatine.r10.Contents.UserInterface.Core.Interface.UIHolder;
import org.Astatine.r10.Contents.UserInterface.Core.Interface.UIType;
import org.Astatine.r10.Contents.UserInterface.Core.UIGenerator.InventoryUIGenerator;
import org.Astatine.r10.Contents.UserInterface.Core.UIGenerator.SlotItemMapping;

import java.util.ArrayList;

@UIType(Type.MAIN_MENU)
public class MainMenuUI extends UIUtils implements EventRegister, UIHolder {
    private Player chestOwner;
    private Inventory inventory;
    private int slotCount;

    private final PlayerSwapHandItemsEvent event;

    public MainMenuUI(PlayerSwapHandItemsEvent event) {
        this.event = event;
        this.chestOwner = this.event.getPlayer();
        this.slotCount = 45;

        if (BooleanUtils.isFalse(this.chestOwner.isSneaking()))
            return;

        this.event.setCancelled(true);
        init();
        execute();
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
    public void init() {}

    @Override
    public void execute() {}

    @Override
    public void UIExecutor() {
        this.inventory = new InventoryUIGenerator()
                .bindHolder(this)
                .inventoryGenerator(
                        this.slotCount,
                        componentExchanger("서버 메뉴", ColorType.YELLOW)
                )
                .setEnhanceUIItem(itemPanelList())
                .executeUI();
    }

    private ArrayList<SlotItemMapping> itemPanelList() {
        ArrayList<SlotItemMapping> result = new ArrayList<>(defaultPanelItems(this.slotCount));

//        result.add(new SlotItemMapping(0, item));
//        result.add(new SlotItemMapping(1, item));
//        result.add(new SlotItemMapping(9, item));
        result.add(new SlotItemMapping(
            10,
            createItem(
                    Material.ENDER_PEARL,
                    "TPA 요청", ColorType.GREEN,
                    true)
            ));

//        result.add(new SlotItemMapping(3, item));
//        result.add(new SlotItemMapping(4, item));
//        result.add(new SlotItemMapping(5, item));
//        result.add(new SlotItemMapping(12, item));
        result.add(new SlotItemMapping(
                13,
                createItem(
                        Material.CHEST_MINECART,
                        "상점", ColorType.GRAY,
                        true)
        ));
//        result.add(new SlotItemMapping(14, item));

//        result.add(new SlotItemMapping(7, item));
//        result.add(new SlotItemMapping(8, item));

        result.add(new SlotItemMapping(
                16,
                createItem(
                        Material.ANVIL,
                        "강화", ColorType.RED,
                        true)
        ));
//        result.add(new SlotItemMapping(17, item));


//        result.add(new SlotItemMapping(18, item));
//        result.add(new SlotItemMapping(19, item));
//        result.add(new SlotItemMapping(27, item));
        result.add(new SlotItemMapping(
                28,
                createItem(
                        Material.BARRIER,
                        "준비중", ColorType.GRAY,
                        true)
        ));

//        result.add(new SlotItemMapping(21, item));
//        result.add(new SlotItemMapping(22, item));
//        result.add(new SlotItemMapping(23, item));
//        result.add(new SlotItemMapping(30, item));
        result.add(new SlotItemMapping(
                31,
                createItem(
                        Material.BARRIER,
                        "준비중", ColorType.GRAY,
                        true)
        ));
//        result.add(new SlotItemMapping(32, item));

//        result.add(new SlotItemMapping(25, item));
//        result.add(new SlotItemMapping(26, item));
        result.add(new SlotItemMapping(
                34,
                createItem(
                        Material.COMMAND_BLOCK,
                        "행동 상호작용", ColorType.PINK,
                        true)
        ));
//        result.add(new SlotItemMapping(35, item));

        result.add(new SlotItemMapping(36, new ItemStack(Material.EMERALD)));
        result.add(new SlotItemMapping(44, getHeadItemStack(this.chestOwner)));

        return result;
    }
}