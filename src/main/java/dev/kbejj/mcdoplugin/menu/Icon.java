package dev.kbejj.mcdoplugin.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class Icon {

    protected ItemStack itemStack;
    protected Predicate<Player> predicate;
    protected Consumer<Player> consumer;

    public Icon(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public Icon(ItemStack itemStack, Predicate<Player> action) {
        this.itemStack = itemStack;
        this.predicate = action;
    }

    public Icon(ItemStack itemStack, Consumer<Player> action) {
        this.itemStack = itemStack;
        this.consumer = action;
    }

    public void setAction(Consumer<Player> action) {
        this.consumer = action;
    }

    public void setAction(Predicate<Player> action) {
        this.predicate = action;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    // Re-renders the menu if it returns true
    public boolean click(Player player) {
        if(predicate != null) {
            return predicate.test(player);
        }
        consumer.accept(player);
        return false;
    }
}
