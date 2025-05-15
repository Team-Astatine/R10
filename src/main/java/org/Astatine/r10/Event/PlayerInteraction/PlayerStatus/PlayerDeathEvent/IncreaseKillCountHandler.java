package org.Astatine.r10.Event.PlayerInteraction.PlayerStatus.PlayerDeathEvent;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.Astatine.r10.Data.User.UserData.UserController;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatus;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatusBuilder;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatusController;
import org.Astatine.r10.Event.EventRegister;

public class IncreaseKillCountHandler implements EventRegister {
    private UserKillStatusController controller = new UserKillStatusController();
    private UserController userController = new UserController();

    private UserKillStatus userKillStatus;
    private final PlayerDeathEvent event;

    public IncreaseKillCountHandler(PlayerDeathEvent event) {
        this.event = event;

        init();
        execute();
    }

    @Override
    public void init() {
    }

    @Override
    public void execute() {
        Player player = this.event.getPlayer().getKiller();

        if (ObjectUtils.isEmpty(player))
            return;

        if (this.userController.readUser(player.getUniqueId()).godMode())
            return;

        this.userKillStatus = this.controller.readUser(player.getUniqueId());
        new UserKillStatusBuilder(this.userKillStatus)
                .killCount(this.userKillStatus.killCount() + 1)
                .buildAndUpdate();
    }
}
