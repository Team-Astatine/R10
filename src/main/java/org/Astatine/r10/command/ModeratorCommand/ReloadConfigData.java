package org.Astatine.r10.command.ModeratorCommand;

import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.Astatine.r10.Data.User.UserData.User;
import org.Astatine.r10.Data.User.UserData.UserController;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.R10;
import org.Astatine.r10.command.CommandRegisterSection;
import org.Astatine.r10.command.ListOfCommand;

import java.util.Optional;


public class ReloadConfigData extends CommandRegisterSection {

    private Player player;
    private boolean consoleSend = false;

    public ReloadConfigData() {
        super(ListOfCommand.CONFIG_RELOAD);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String[] args) {

        User user = null;
        try {
            user = new UserController().readUser(sender.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional.ofNullable(user).ifPresentOrElse(
                existUser -> this.player = Bukkit.getPlayer(existUser.uuid()),
                () -> this.consoleSend = true
        );

//        operation Check
        if (BooleanUtils.isFalse(player.isOp()) && BooleanUtils.isFalse(this.consoleSend)) {
            playerSendMsgComponentExchanger(player, "해당 명령어는 플레이어가 사용할 수 없습니다.", ColorType.RED);
            return false;
        }

        sendComment();
        R10.getPlugin(R10.class).configFileLoader();
        return true;
    }

    private void sendComment() {
        String comment = "Reload Done";
        if (this.consoleSend)
            Bukkit.getLogger().info("[R10] " + comment);
        else playerSendMsgComponentExchanger(this.player, comment, ColorType.YELLOW);
    }
}
