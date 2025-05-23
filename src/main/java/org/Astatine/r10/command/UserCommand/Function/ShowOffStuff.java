package org.Astatine.r10.command.UserCommand.Function;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.command.CommandRegisterSection;
import org.Astatine.r10.command.ListOfCommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ShowOffStuff extends CommandRegisterSection {

    public ShowOffStuff() {
        super(ListOfCommand.SHOW_OFF_STUFF);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String[] args) {

        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();

        // 2. 손에 아이템이 없거나 AIR인 경우
        if (item.isEmpty() || item.getType() == Material.AIR) {
            player.sendMessage(componentExchanger("손에 아이템을 들어야 자랑할 수 있습니다.", ColorType.RED));
            return true;
        }

        ItemStack single = item.asOne();
        HoverEvent<HoverEvent.ShowItem> hover = single.asHoverEvent(show -> show);

        Component nameComp = Bukkit.getItemFactory().displayName(single);
        Component message = Component.empty()
                .append(player.displayName())
                .append(Component.text("님의 자랑: "))
                .append(nameComp.hoverEvent(hover));

        Bukkit.getServer().sendMessage(message);

        return true;
    }
}
