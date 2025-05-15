package org.Astatine.r10.Event.UserInterface.Function.Executor;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.Astatine.r10.Event.UserInterface.Enhance.EnhanceUICloseEvent;
import org.Astatine.r10.Event.UserInterface.Function.Interface.Type;
import org.Astatine.r10.Event.UserInterface.Function.Interface.UIHolder;
import org.Astatine.r10.Event.UserInterface.Function.Interface.UIType;

public class UICloser {

    public UICloser(InventoryCloseEvent event) {

        if (!(event.getView().getTopInventory().getHolder() instanceof UIHolder holder))
            return;

        if (ObjectUtils.isEmpty(holder.getClass().getAnnotation(UIType.class)))
            return;

//        System.out.println("Annotations: " +
//                Arrays.toString(holder.getClass().getDeclaredAnnotations()));

        Type type = holder.getClass().getAnnotation(UIType.class).value();
        switch (type) {
            case ENHANCE    -> new EnhanceUICloseEvent(event);
        }

    }
}
