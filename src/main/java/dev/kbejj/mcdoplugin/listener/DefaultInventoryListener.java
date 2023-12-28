package dev.kbejj.mcdoplugin.listener;

import dev.kbejj.mcdoplugin.menu.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;

public class DefaultInventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(! (e.getView().getTopInventory().getHolder() instanceof Menu)) return;
        if(e.getClickedInventory() == null) return;
        if(e.getCurrentItem() == null) return;

        InventoryHolder holder = e.getClickedInventory().getHolder();
        if(holder instanceof Menu) {
            ((Menu) holder).click(e.getSlot());
        }else {
            ((Menu) holder).handlePlayerInventoryClick(e);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        InventoryHolder holder = e.getInventory().getHolder();
        if(holder instanceof Menu) {
            ((Menu) holder).handleClose(e);
        }
    }
}
