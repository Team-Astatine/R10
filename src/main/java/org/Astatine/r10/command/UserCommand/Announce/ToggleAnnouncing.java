package org.Astatine.r10.command.UserCommand.Announce;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ToggleAnnouncing extends CommandRegisterSection {

    public ToggleAnnouncing() {
        super(ListOfCommand.ANNOUNCING);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender commandSender,
                             final @NotNull Command command,
                             final @NotNull String s,
                             final @NotNull String[] strings) {

        User targetUser = new UserController().readUser(((Player) commandSender).getUniqueId());

        String comment = targetUser.announcingSkip() ? "비활성화" : "활성화";
        playerSendMsgComponentExchanger(commandSender, "공지 " + comment + " 완료", ColorType.YELLOW);

        new UserBuilder(targetUser)
                .isAnnouncingSkip(!targetUser.announcingSkip())
                .buildAndUpdate();
        return true;
    }
}