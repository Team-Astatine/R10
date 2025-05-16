package org.Astatine.r10.Contents.PlayerDeathEvent;

import org.Astatine.r10.Contents.UserInterface.Core.UIUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.Astatine.r10.Contents.EventRegister;

public class DropDeadsHeadEvent extends UIUtils implements EventRegister {

    private Player player;
    private final PlayerDeathEvent event;

    public DropDeadsHeadEvent(PlayerDeathEvent event) {
        this.event = event;

        init();
        execute();
    }

    @Override
    public void init() {
        this.player = event.getPlayer();
    }

    @Override
    public void execute() {
        event.getDrops().add(getHeadItemStack(this.player));
    }
}
