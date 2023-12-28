package dev.kbejj.mcdoplugin.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class BasicMenu extends MenuX {
    public BasicMenu(Player player) {
        super(player);
    }

    public BasicMenu(JavaPlugin plugin, Player player) {
        super(plugin, player);
    }

    protected void useDefaultButtonSlots(){
        this.backButtonSlot = size() - 9;
        this.closeButtonSlot = size() - 5;
    }

    @Override
    public void handleOpen(InventoryOpenEvent e) {}

    @Override
    public void handleClose(InventoryCloseEvent e) {}
}
