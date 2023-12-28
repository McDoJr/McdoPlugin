package dev.kbejj.mcdoplugin.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Button extends Icon {

    public Button(ItemStack itemStack) {
        super(itemStack);
    }

    public Button(ItemStack itemStack, Predicate<Player> action) {
        super(itemStack, action);
    }

    public Button(ItemStack itemStack, Consumer<Player> action) {
        super(itemStack, action);
    }
}
