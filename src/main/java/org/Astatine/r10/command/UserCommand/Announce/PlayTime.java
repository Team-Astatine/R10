package org.Astatine.r10.command.UserCommand.Announce;

import org.Astatine.r10.Contents.PlayerInteraction.Announce.Tip.Announcer;
import org.Astatine.r10.command.CommandRegisterSection;
import org.Astatine.r10.command.ListOfCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayTime extends CommandRegisterSection {

    public PlayTime() {
        super(ListOfCommand.PLAY_TIME);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Announcer.getAnnouncer().joinPlayerInformationAnnouncer((Player) commandSender);
        return true;
    }
}
