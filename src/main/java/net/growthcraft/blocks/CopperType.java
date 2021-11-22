package net.growthcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public enum CopperType {
    ITEM,
    BLOCK,
    BRICKS,
    CUT;

    public static CopperType fromBlock(Block block){
        return block == Blocks.COPPER_BLOCK ? BLOCK : block == Blocks.CUT_COPPER ? CUT : BRICKS;
    }
}
