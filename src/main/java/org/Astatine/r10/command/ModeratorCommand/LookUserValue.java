package org.Astatine.r10.command.ModeratorCommand;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.Astatine.r10.Data.User.UserData.User;
import org.Astatine.r10.Data.User.UserData.UserController;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatus;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatusController;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.command.CommandRegisterSection;
import org.Astatine.r10.command.ListOfCommand;

import java.util.Optional;

public class LookUserValue extends CommandRegisterSection {

    public LookUserValue() {
        super(ListOfCommand.LOOK_USER_VALUE);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String @NotNull [] args) {

        User user = null;
        try {
            user = new UserController().readUser(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional.ofNullable(user)
                .ifPresentOrElse(
                        existUser -> {
                            UserKillStatus userKillStatus = new UserKillStatusController().readUser(existUser.uuid());
                            sendComment(sender, existUser + "\n\n" + userKillStatus);
                        },

                        () -> {
                            sendComment(sender, "존재하지 않는 유저");
                        }
                );

        return true;
    }

    private void sendComment(CommandSender sender, String comment) {
        if (sender instanceof Player)
            playerSendMsgComponentExchanger(sender, comment, ColorType.YELLOW);
        else Bukkit.getLogger().info(comment);
    }
}
