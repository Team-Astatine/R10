package org.Astatine.r10.Contents.PlayerInteraction.Announce.JoinAndQuitMessage;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatus;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatusController;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Util.Function.StringComponentExchanger;

public class QuitMessageHandler extends StringComponentExchanger implements EventRegister {
    private Player player;
    private UserKillStatus userKillStatus;

    private final PlayerQuitEvent event;

    public QuitMessageHandler(PlayerQuitEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.player = this.event.getPlayer();
        this.userKillStatus = new UserKillStatusController().readUser(this.player.getUniqueId());
    }

    @Override
    public void execute() {
        if (this.userKillStatus.killCount() == 0)
            this.event.quitMessage(
                    componentExchanger(" - " + this.player.getName(), ColorType.RED)
            );

        else
            this.event.quitMessage(
                    componentExchanger(" - [ " + this.userKillStatus.killCount() + "KILL ] " + this.player.getName(), ColorType.RED)
            );
    }
}