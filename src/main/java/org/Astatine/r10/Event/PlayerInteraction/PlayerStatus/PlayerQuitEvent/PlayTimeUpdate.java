package org.Astatine.r10.Event.PlayerInteraction.PlayerStatus.PlayerQuitEvent;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.Astatine.r10.Data.User.UserData.User;
import org.Astatine.r10.Data.User.UserData.UserBuilder;
import org.Astatine.r10.Data.User.UserData.UserController;
import org.Astatine.r10.Event.EventRegister;

public class PlayTimeUpdate implements EventRegister {

    private Player quitPlayer;
    private UserController userController;

    private final PlayerQuitEvent event;

    public PlayTimeUpdate(PlayerQuitEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.quitPlayer = this.event.getPlayer();
        this.userController = new UserController();
    }

    @Override
    public void execute() {
        User user = this.userController.readUser(this.quitPlayer.getUniqueId());

        new UserBuilder(user)
                .playTime(this.quitPlayer.getStatistic(Statistic.PLAY_ONE_MINUTE))
                .buildAndUpdate();
    }
}
