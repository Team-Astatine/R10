package org.Astatine.r10.Contents.UserInterface.Command.MainMenu;

import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.command.ListOfCommand;
import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class MainMenuTabKeyBindOpen implements EventRegister {

    private Player player;

    private final PlayerSwapHandItemsEvent event;

    public MainMenuTabKeyBindOpen(PlayerSwapHandItemsEvent event) {
        this.event = event;
        this.player = this.event.getPlayer();

        if (BooleanUtils.isFalse(this.player.isSneaking()))
            return;

        this.event.setCancelled(true);

        init();
        execute();
    }

    @Override
    public void init() {}

    @Override
    public void execute() {
        this.player.performCommand(ListOfCommand.MAIN_MENU_TAB_OPEN.getCommand());
    }
}
