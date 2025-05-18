package org.Astatine.r10.Contents.UserInterface.Core.Interface;

import org.Astatine.r10.Contents.UserInterface.Enhance.EnhanceUIClickEvent;
import org.Astatine.r10.Contents.UserInterface.Enhance.EnhanceUICloseEvent;
import org.Astatine.r10.Contents.UserInterface.GSit.GSitUIClickEvent;
import org.Astatine.r10.Contents.UserInterface.HOME.HomeUIClickEvent;
import org.Astatine.r10.Contents.UserInterface.Menu.MainMenuUIClickEvent;
import org.Astatine.r10.Contents.UserInterface.TPA.TpaUIClickEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.function.Consumer;

public enum Type {
    MAIN_MENU(MainMenuUIClickEvent::new, null),
    ENHANCE(EnhanceUIClickEvent::new, EnhanceUICloseEvent::new),
    GSIT(GSitUIClickEvent::new, null),
    TPA(TpaUIClickEvent::new, null),
    HOME(HomeUIClickEvent::new, null);

    private Consumer<InventoryClickEvent> eventConsumer;
    private Consumer<InventoryCloseEvent> closeConsumer;

    Type(Consumer<InventoryClickEvent> eventConsumer, Consumer<InventoryCloseEvent> closeConsumer) {
        this.eventConsumer = eventConsumer;
        this.closeConsumer = closeConsumer;
    }

    public Consumer<InventoryClickEvent> getEventConsumer() {
        return eventConsumer;
    }

    public Consumer<InventoryCloseEvent> getCloseEventConsumer() {
        return closeConsumer;
    }
}
