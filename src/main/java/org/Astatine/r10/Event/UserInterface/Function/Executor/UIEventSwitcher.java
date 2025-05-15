package org.Astatine.r10.Event.UserInterface.Function.Executor;

import org.Astatine.r10.Event.UserInterface.Enhance.EnhanceUICloseEvent;
import org.Astatine.r10.Event.UserInterface.Enhance.EnhanceUIClickEvent;
import org.Astatine.r10.Event.UserInterface.Function.Interface.Type;
import org.Astatine.r10.Event.UserInterface.Function.Interface.UIHolder;
import org.Astatine.r10.Event.UserInterface.Function.Interface.UIType;
import org.Astatine.r10.Event.UserInterface.GSit.GSitUIClickEvent;
import org.Astatine.r10.Event.UserInterface.Menu.MainMenuUIClickEvent;
import org.Astatine.r10.Event.UserInterface.TPA.TpaUIClickEvent;
import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryEvent;
import java.util.Map;
import java.util.function.Consumer;

/**
 * {@link Type} 각 UI 타입에 맞는 {@link org.bukkit.inventory.Inventory}를 생성하는 클래스입니다.
 * {@link UIHolder} 를 구현한 클래스에 {@link UIType} 어노테이션을 붙여서 구현합니다.
 */
public class UIEventSwitcher {

    private final Map<Type, Consumer<InventoryClickEvent>> CLICK_HANDLERS = Map.of(
        Type.MAIN_MENU, MainMenuUIClickEvent::new,
        Type.GSIT, GSitUIClickEvent::new,
        Type.ENHANCE, EnhanceUIClickEvent::new,
        Type.TPA, TpaUIClickEvent::new
    );

    private final Map<Type, Consumer<InventoryCloseEvent>> CLOSE_HANDLERS = Map.of(
        Type.ENHANCE, EnhanceUICloseEvent::new
    );

    public UIEventSwitcher(InventoryEvent event) {

        if (!(event.getView().getTopInventory().getHolder() instanceof UIHolder holder))
            return;

        UIType typeAnnotation = holder.getClass().getAnnotation(UIType.class);
        if (ObjectUtils.isEmpty(typeAnnotation))
            return;

        switch (event) {
            case InventoryClickEvent clickEvent ->
                CLICK_HANDLERS.getOrDefault(typeAnnotation.value(),
                        e -> {}).accept(clickEvent);

            case InventoryCloseEvent closeEvent ->
                    CLOSE_HANDLERS.getOrDefault(typeAnnotation.value(),
                        e -> {}).accept(closeEvent);

            default -> {}
        }

    }
}
