package dev.kbejj.mcdoplugin.menu;

import dev.kbejj.mcdoplugin.managers.ButtonsManager;
import dev.kbejj.mcdoplugin.utils.ItemBuilder;
import dev.kbejj.mcdoplugin.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public abstract class Menu implements InventoryHolder {

    protected Inventory inventory;
    protected Player player;
    protected Map<Integer, Icon> icons;
    protected ButtonsManager buttonsManager;
    protected ItemBuilder itemBuilder;

    private final String title;
    protected final int rows;

    protected Button back, close;
    protected int closeButtonSlot = 49;
    protected int backButtonSlot = 45;

    public Menu(JavaPlugin plugin, Player player, String title, int rows, ButtonsManager buttonsManager) {
        this.player = player;
        this.title = StringUtil.translate(title);
        this.rows = rows * 9;
        this.icons = new HashMap<>();
        this.buttonsManager = buttonsManager;
        this.itemBuilder = new ItemBuilder(plugin);
    }

    public abstract void setContents();
    // Handle actions if player clicks on his own inventory
    public abstract void handlePlayerInventoryClick(InventoryClickEvent e);
    public abstract void handleClose(InventoryCloseEvent e);

    public void open() {
        this.inventory = Bukkit.createInventory(this, rows, title);
        createDefaultButtons();
        setContents();
        renderIcons();
        this.player.openInventory(getInventory());
    }

    private void renderIcons() {
        for(Map.Entry<Integer, Icon> entry : this.icons.entrySet()) {
            this.inventory.setItem(entry.getKey(), entry.getValue().itemStack);
        }
    }

    public void click(int slot) {
        final Icon icon = this.icons.get(slot);

        if(icon != null && icon.click(player)) {
            open();
        }
    }

    private void createDefaultButtons() {
        this.back = new Button(buttonsManager.getButton("back"));
        this.close = new Button(
                buttonsManager.getButton("close"),
                HumanEntity::closeInventory
        );
        setIcon(closeButtonSlot, close);
    }

    protected void setIcon(int slot, Icon icon) {
        this.icons.put(slot, icon);
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }
}
