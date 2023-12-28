package dev.kbejj.mcdoplugin.serializers;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationSerializer {

    public static String serialize(Location location) {
        if(location == null) {
            return "UNDEFINED";
        }
        return location.getWorld().getName() + ";"
                + (int) location.getX() + ";"
                + (int) location.getY() + ";"
                + (int) location.getZ();
    }

    public static Location deserialize(String string) {
        if(string == null || string.isEmpty()) {
            return null;
        }
        if(!string.contains(";")) {
            return null;
        }
        String[] data = string.split(";");
        return new Location(Bukkit.getWorld(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]));
    }
}
