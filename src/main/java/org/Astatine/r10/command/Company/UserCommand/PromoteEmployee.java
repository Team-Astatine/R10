package org.Astatine.r10.command.Company.UserCommand;

import org.Astatine.r10.command.CommandRegisterSection;
import org.Astatine.r10.command.ListOfCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class PromoteEmployee extends CommandRegisterSection {


    public PromoteEmployee() {
        super(ListOfCommand.PROMOTE_EMPLOYEE);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String[] args) {

        return true;
    }
}
