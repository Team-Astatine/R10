package org.Astatine.r10.Contents.UserInterface.Menu;

import org.Astatine.r10.Contents.UserInterface.Core.UIUtils;
import org.Astatine.r10.Data.DataIO.Config.ConfigIOHandler;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Contents.UserInterface.Core.Interface.UIHolder;
import org.Astatine.r10.command.ListOfCommand;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class MainMenuUIClickEvent extends UIUtils implements EventRegister {
    private UIHolder uiHolder;
    private Player clickPlayer;

    private final InventoryClickEvent event;

    public MainMenuUIClickEvent(InventoryClickEvent event) {
        this.event = event;

        if (ObjectUtils.isEmpty(this.event.getClickedInventory()))
            return;

        init();
        execute();
    }

    @Override
    public void init() {
        this.uiHolder = this.event.getClickedInventory().getHolder() instanceof UIHolder holder ? holder : null;
        this.clickPlayer = this.event.getWhoClicked() instanceof Player player ? player : null;
    }

    @Override
    public void execute() {
        if (ObjectUtils.isEmpty(this.uiHolder))
            return;

        if (ObjectUtils.notEqual(this.clickPlayer, this.uiHolder.getOwner()))
            return;

        this.event.setCancelled(true);
        switch (this.event.getSlot()) {
            case /*0,1,9,*/10 -> this.clickPlayer.performCommand(ListOfCommand.TPA_TAB_OPEN.getCommand());

            case /*3,4,5,12,*/13/*,14*/ -> this.clickPlayer.performCommand("shop");

            case /*7,8,*/16/*,17*/ -> this.clickPlayer.performCommand(ListOfCommand.ENHANCE_TAB_OPEN.getCommand());

            case /*18,19,27,*/28 -> this.clickPlayer.performCommand(ListOfCommand.HOME_TAB_OPEN.getCommand());

//            case /*21,22,23,30,*/31/*,32*/ -> this.event.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.PLUGIN);

            case /*25,26,*/34/*,35*/ -> this.clickPlayer.performCommand(ListOfCommand.GSIT_TAB_OPEN.getCommand());

            case /*46,*/47 -> {
                this.event.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.PLUGIN);
                this.event.getWhoClicked().sendMessage(createLinkComponentExchanger(
                        ConfigIOHandler.getConfigIOHandler().getMinelistLinkComment(),
                        ConfigIOHandler.getConfigIOHandler().getMinelistLink(),
                        ColorType.GREEN));
            }


            case /*48, */49/*, 50*/ -> {
                this.event.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.PLUGIN);
                this.event.getWhoClicked().sendMessage(createLinkComponentExchanger(
                        ConfigIOHandler.getConfigIOHandler().getNotionLinkComment(),
                        ConfigIOHandler.getConfigIOHandler().getNotionLink(),
                        ColorType.NOTION_COLOR));
            }

            case /*50, */51 -> {
                this.event.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.PLUGIN);
                this.event.getWhoClicked().sendMessage(createLinkComponentExchanger(
                        ConfigIOHandler.getConfigIOHandler().getDiscordInviteComment(),
                        ConfigIOHandler.getConfigIOHandler().getDiscordInviteLink(),
                        ColorType.DISCORD_COLOR));
            }
        }
    }
}
