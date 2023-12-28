package dev.kbejj.mcdoplugin.menu;

import dev.kbejj.mcdoplugin.managers.ButtonsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public abstract class PaginatedMenu extends Menu {

    protected int page = 0;
    protected Button next, previous;

    protected int nextButtonSlot = 53;
    protected int previousButtonSlot = 45;

    protected enum ButtonSlotPosition {
        OUTER, INNER, CUSTOM
    }

    public PaginatedMenu(JavaPlugin plugin, Player player, String title, int rows, ButtonsManager buttonsManager) {
        super(plugin, player, title, rows, buttonsManager);
        createDefaultButtons();
    }

    protected void useDefaultPagination(List<Icon> list) {
        useDefaultPagination(list, rows - 9);
    }

    protected void useDefaultPagination(List<Icon> list, int pageSize) {
        useDefaultPagination(list, pageSize, null);
    }

    protected void useDefaultPagination(List<Icon> list, int[] pattern) {
        useDefaultPagination(list, pattern.length, pattern);
    }

    protected void useDefaultPagination(List<Icon> list, int pageSize, int[] pattern) {
        int size = list.size();
        if(size > pageSize) {
            setIcon(previousButtonSlot, page > 0 ? previous : back);
            if(page < size / pageSize) {
                setIcon(nextButtonSlot, next);
            }
        }
        int index = 0;
        for(int i = page * pageSize; i < pageSize + (page * pageSize); i++) {
            if(i >= size) {
                break;
            }
            if(pattern == null) {
                setIcon(index, list.get(i));
            }else {
                setIcon(pattern[index], list.get(i));
            }
            index++;
        }
    }

    @Override
    public void handleClose(InventoryCloseEvent e) {
        this.icons.clear();
    }

    private void createDefaultButtons() {
        this.next = new Button(
                buttonsManager.getButton("next"),
                (player) -> {
                    page++;
                }
        );

        this.previous = new Button(
                buttonsManager.getButton("previous"),
                (player) -> {
                    page--;
                }
        );
    }

    protected void useOuterButtonSlots() {
        useDefaultButtonSlots(ButtonSlotsPosition.OUTER);
    }

    protected void useInnerButtonSlots() {
        useDefaultButtonSlots(ButtonSlotsPosition.INNER);
    }

    private void useDefaultButtonSlots(ButtonSlotsPosition buttonSlotsPosition) {
        boolean outerButtonSlots = buttonSlotsPosition == ButtonSlotsPosition.OUTER;
        this.nextButtonSlot = rows - (outerButtonSlots ? 1 : 4);
        this.previousButtonSlot = rows - (outerButtonSlots ? 9 : 6);
        this.backButtonSlot = previousButtonSlot;
        this.closeButtonSlot = rows - 5;
    }

    public enum ButtonSlotsPosition {
        OUTER, INNER, CUSTOM
    }
}
