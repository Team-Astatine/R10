package org.Astatine.r10.Contents.Enhance.PlayerInteraction.UpdateItemLore;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.Astatine.r10.Contents.Enhance.Interface.EnhanceUtil;
import org.Astatine.r10.Contents.EventRegister;

public class UpdateEnhanceItemPrepareAnvil implements EventRegister {

    private ItemStack resultItem;

    private final PrepareAnvilEvent event;

    public UpdateEnhanceItemPrepareAnvil(PrepareAnvilEvent event) {
        this.event = event;

        init();
        execute();
    }

    @Override
     public void init() {
        this.resultItem = this.event.getResult();
    }

    @Override
    public void execute() {
        if (ObjectUtils.isEmpty(this.resultItem))
            return;

        if (BooleanUtils.isFalse(this.resultItem.hasItemMeta()))
            return;

        if (BooleanUtils.isFalse(this.resultItem.getItemMeta().hasCustomModelData()))
            return;

        if (this.resultItem.getEnchantmentLevel(Enchantment.SHARPNESS) < 1)
            return;

        int enhanceLevel = this.resultItem.getItemMeta().getCustomModelData();
        ItemMeta targetItemMeta = this.resultItem.getItemMeta();
        targetItemMeta.setCustomModelData(0);
        this.resultItem.setItemMeta(targetItemMeta);

        try {
            EnhanceUtil.increaseEnhanceItemLevel(this.resultItem, enhanceLevel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
