package org.Astatine.r10.Event.UserInterface.Enhance;

import org.Astatine.r10.Event.UserInterface.Core.UIUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.Astatine.r10.Data.DataIO.Config.ConfigIOHandler;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Event.UserInterface.Core.Interface.Type;
import org.Astatine.r10.Event.UserInterface.Core.Interface.UIHolder;
import org.Astatine.r10.Event.UserInterface.Core.Interface.UIType;
import org.Astatine.r10.Event.UserInterface.Core.UIGenerator.InventoryUIGenerator;
import org.Astatine.r10.Event.UserInterface.Core.UIGenerator.SlotItemMapping;

import java.util.Arrays;
import java.util.List;

@UIType(Type.ENHANCE)
public class EnhanceUI extends UIUtils implements UIHolder {
    // 슬롯 인덱스 상수 정의
    private final int SLOT_WEAPON = 0;
    private final int SLOT_SCROLL = 1;
    private final int SLOT_PROTECT = 2;
    private final int SLOT_DISCORD = 6;
    private final int SLOT_EXECUTE = 7;
    private final int SLOT_NOTION = 8;

    private Player chestOwner;
    private Inventory inventory;

    public EnhanceUI(Player player) {
        this.chestOwner = player;
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

    /**
     * 인벤토리 UI 설정
     *
     * 해당 순서대로 Inventory index가 설정됩니다.
     * 0 1 2 - 🔪📜📜
     * 3 4 5
     * 6 7 8 - 🟦🟥◻️
     *
     * 0 - 강화용 아이템을 넣는 가이드 무기를 표기합니다.
     * 1 - 강화 주문서를 넣는 가이드를 표기 합니다.
     * 2 - 파괴방지 주문서를 넣는 가이드를 표기 합니다.
     *
     * 6 - 디스코드 링크를 표기합니다.
     * 7 - 강화를 실행합니다. {@link EnhanceUIClickEvent} 를 참고해주세요.
     * 8 - 노션링크를 표기합니다.
     * 웹 사이트 링크는 {@link ConfigIOHandler} 를 참고하면 됩니다.
     */
    @Override
    public void UIExecutor() {
        this.inventory = new InventoryUIGenerator()
                .bindHolder(this)
                .inventoryGenerator(
                        InventoryType.DROPPER,
                        componentExchanger("강화", ColorType.RED
                ))
                .setEnhanceUIItem(setSlotItemPannelList())
                .executeUI();
    }

    private @NotNull List<SlotItemMapping> setSlotItemPannelList() {
        return Arrays.asList(
                new SlotItemMapping(
                        SLOT_WEAPON,
                        createItem(
                            Material.NETHERITE_SWORD,
                            "강화할 아래슬롯에 무기를 올려주세요", ColorType.ORANGE,
                            true)
                ),

                new SlotItemMapping(
                        SLOT_SCROLL,
                        createItem(
                            Material.ANVIL,
                            "아이템에 들어갈 재료를 아래슬롯에 넣어주세요", ColorType.ORANGE,
                            true)
                ),

                new SlotItemMapping(
                        SLOT_PROTECT,
                        createItem(
                            Material.HEART_OF_THE_SEA,
                            "파괴방어 스크롤을 아래슬롯에 넣어주세요", ColorType.ORANGE,
                            true)
                ),

                new SlotItemMapping(
                        SLOT_DISCORD,
                        createItem(
                            Material.LIGHT_BLUE_STAINED_GLASS_PANE,
                            "디스코드 링크받기", ColorType.DISCORD_COLOR,
                            true)
                ),

                new SlotItemMapping(
                        SLOT_EXECUTE,
                        createItem(
                            Material.RED_STAINED_GLASS_PANE,
                            "강화 실행", ColorType.RED,
                            true)
                ),

                new SlotItemMapping(
                        SLOT_NOTION,
                        createItem(
                            Material.LIGHT_GRAY_STAINED_GLASS_PANE,
                            "강화법 확인하기",
                            ColorType.NOTION_COLOR,
                            true)
                )
        );
    }
}
