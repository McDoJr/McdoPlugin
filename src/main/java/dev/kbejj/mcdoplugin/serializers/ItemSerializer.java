package dev.kbejj.mcdoplugin.serializers;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ItemSerializer {

    public static String serialize(List<ItemStack> itemStacks) {
        if(itemStacks == null || itemStacks.isEmpty()) {
            return "UNDEFINED";
        }
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            dataOutput.writeInt(itemStacks.size());
            for(ItemStack itemStack : itemStacks) {
                dataOutput.writeObject(itemStack);
            }
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        }catch (Exception e) {
            throw new IllegalStateException("Failed to serialize items!");
        }
    }

    public static List<ItemStack> deserialize(String string) {
        if(string == null || string.isEmpty()) {
             return new ArrayList<>();
        }
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(string));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            int size = dataInput.readInt();
            List<ItemStack> itemStacks = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                itemStacks.add((ItemStack) dataInput.readObject());
            }
            dataInput.close();
            return itemStacks;
        }catch (Exception e) {
            throw new IllegalStateException("Failed to deserialize items!");
        }
    }
}
