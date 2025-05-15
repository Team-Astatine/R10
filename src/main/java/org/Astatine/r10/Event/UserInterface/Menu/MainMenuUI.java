package org.Astatine.r10.Event.UserInterface.Menu;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Event.EventRegister;
import org.Astatine.r10.Event.UserInterface.Function.Interface.Type;
import org.Astatine.r10.Event.UserInterface.Function.Interface.UIHolder;
import org.Astatine.r10.Event.UserInterface.Function.Interface.UIType;
import org.Astatine.r10.Event.UserInterface.Function.UIGenerator.CreatePanelItem;
import org.Astatine.r10.Event.UserInterface.Function.UIGenerator.InventoryUIGenerator;
import org.Astatine.r10.Event.UserInterface.Function.UIGenerator.SlotItemMapping;
import org.Astatine.r10.Util.Function.StringComponentExchanger;

import java.util.ArrayList;

@UIType(Type.MAIN_MENU)
public class MainMenuUI extends StringComponentExchanger implements EventRegister, UIHolder {
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

        ItemStack emerald = new ItemStack(Material.EMERALD);

        ItemStack headItemStack = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta data = (SkullMeta) headItemStack.getItemMeta();
        data.setOwningPlayer(this.chestOwner);
        headItemStack.setItemMeta(data);

        ArrayList<SlotItemMapping> result = new ArrayList<>();
        for (int i = 0; i < this.slotCount; i++)
            result.add(new SlotItemMapping(
                    i,
                    createItem(
                            Material.WHITE_STAINED_GLASS_PANE,
                            "",
                            ColorType.WHITE
                    )));

//        result.add(new SlotItemMapping(0, item));
//        result.add(new SlotItemMapping(1, item));
//        result.add(new SlotItemMapping(9, item));
        result.add(new SlotItemMapping(
                10,
                createButtonItem(
                        Material.ENDER_PEARL,
                        "TPA 요청",
                        ColorType.GREEN
                ))
        );

//        result.add(new SlotItemMapping(3, item));
//        result.add(new SlotItemMapping(4, item));
//        result.add(new SlotItemMapping(5, item));
//        result.add(new SlotItemMapping(12, item));
        result.add(new SlotItemMapping(
                13,
                createButtonItem(
                        Material.DIAMOND,
                        "상점",
                        ColorType.GRAY
                ))
        );
//        result.add(new SlotItemMapping(14, item));

//        result.add(new SlotItemMapping(7, item));
//        result.add(new SlotItemMapping(8, item));
        result.add(new SlotItemMapping(
                16,
                createButtonItem(
                        Material.ANVIL,
                        "강화",
                        ColorType.RED
                ))
        );
//        result.add(new SlotItemMapping(17, item));


//        result.add(new SlotItemMapping(18, item));
//        result.add(new SlotItemMapping(19, item));
//        result.add(new SlotItemMapping(27, item));
        result.add(new SlotItemMapping(
                28,
                createButtonItem(
                        Material.BARRIER,
                        "준비중",
                        ColorType.GRAY
                ))
        );

//        result.add(new SlotItemMapping(21, item));
//        result.add(new SlotItemMapping(22, item));
//        result.add(new SlotItemMapping(23, item));
//        result.add(new SlotItemMapping(30, item));
        result.add(new SlotItemMapping(
                31,
                createButtonItem(
                        Material.BARRIER,
                        "준비중",
                        ColorType.GRAY
                ))
        );
//        result.add(new SlotItemMapping(32, item));

//        result.add(new SlotItemMapping(25, item));
//        result.add(new SlotItemMapping(26, item));
        result.add(new SlotItemMapping(
                34,
                createButtonItem(
                        Material.COMMAND_BLOCK,
                        "행동 상호작용",
                        ColorType.PINK
                ))
        );
//        result.add(new SlotItemMapping(35, item));

        result.add(new SlotItemMapping(36, emerald));
        result.add(new SlotItemMapping(44, headItemStack));

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