package org.Astatine.r10.Contents.PlayerInteraction.Announce.JoinAndQuitMessage;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatus;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatusController;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Contents.PlayerInteraction.Announce.Tip.Announcer;
import org.Astatine.r10.Util.Function.StringComponentExchanger;

public class JoinMessageHandler extends StringComponentExchanger implements EventRegister {
    private Player joinPlayer;
    private UserKillStatus userKillStatus;

    private final PlayerJoinEvent event;

    public JoinMessageHandler(PlayerJoinEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.joinPlayer = this.event.getPlayer();
        this.userKillStatus = new UserKillStatusController().readUser(this.joinPlayer.getUniqueId());
    }

    @Override
    public void execute() {
        this.joinPlayer.performCommand("help");
        Announcer.getAnnouncer().joinPlayerInformationAnnouncer(this.joinPlayer);

        if (this.userKillStatus.killCount() == 0)
            this.event.joinMessage(
                    componentExchanger(" + " + this.joinPlayer.getName(), ColorType.RED)
            );

        else this.event.joinMessage(
                componentExchanger(" + [ " + this.userKillStatus.killCount() + "KILL ] " + this.joinPlayer.getName(), ColorType.RED)
        );
    }
}