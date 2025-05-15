package org.Astatine.r10.Event.UserInterface.Function.UIGenerator;

import org.bukkit.inventory.ItemStack;

public record SlotItemMapping (
        int slot,
        ItemStack itemStack
){};