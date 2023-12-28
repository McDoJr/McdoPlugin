package dev.kbejj.mcdoplugin.utils;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.block.data.Directional;
import org.bukkit.inventory.DoubleChestInventory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChestUtil {
    public static boolean canBePlaced(Block block, Block relative){
        return getFacing(block) == getFacing(relative) && blockNotFrontOrBehind(block, relative);
    }

    public static BlockFace getFacing(Block block){
        return toDirectional(block).getFacing();
    }

    public static Directional toDirectional(Block block){
        return (Directional) block.getBlockData();
    }

    public static Block getBehindBlock(Block block){
        return block.getRelative(toDirectional(block).getFacing().getOppositeFace());
    }

    public static List<Block> getSideBlocks(Block block){
        return Stream.of(getBlockFaces()).filter(blockFace -> blockFace != getFacing(block) && blockFace != getFacing(block).getOppositeFace())
                .map(block::getRelative).collect(Collectors.toList());
    }

    public static Block getFrontBlock(Block block){
        return block.getRelative(toDirectional(block).getFacing());
    }

    public static boolean blockNotFrontOrBehind(Block block, Block relative){
        return differentLocation(getFrontBlock(block), relative) && differentLocation(getBehindBlock(block), relative);
    }

    public static boolean differentLocation(Block block, Block relative){
        return !block.getLocation().equals(relative.getLocation());
    }

    public static boolean isDoubleChest(Chest chest){
        return chest.getInventory() instanceof DoubleChestInventory;
    }

    public static Chest getDoubleChestOtherHalf(Chest chest){
        DoubleChest doubleChest = (DoubleChest) chest.getInventory().getHolder();
        Chest rightSide = (Chest) doubleChest.getRightSide();
        Chest leftSide = (Chest) doubleChest.getLeftSide();
        if(equals(chest, rightSide)){
            return leftSide;
        }else{
            return rightSide;
        }
    }

    private static boolean equals(Chest current, Chest other){
        return current.getLocation().equals(other.getLocation());
    }

    public static BlockFace[] getBlockFaces(){
        return new BlockFace[] {BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST};
    }
}
