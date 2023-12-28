package dev.kbejj.mcdoplugin.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundUtil {

    public static void pling(Player player) {
        createSound(player, Sound.BLOCK_NOTE_BLOCK_PLING);
    }

    public static void exp(Player player) {
        createSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP);
    }

    public static void villagerYes(Player player) {
        createSound(player, Sound.ENTITY_VILLAGER_YES);
    }

    public static void villagerNo(Player player) {
        createSound(player, Sound.ENTITY_VILLAGER_NO);
    }

    private static void createSound(Player player, Sound sound) {
        player.getWorld().playSound(player, sound, (float) 1, (float) 1);
    }
}
