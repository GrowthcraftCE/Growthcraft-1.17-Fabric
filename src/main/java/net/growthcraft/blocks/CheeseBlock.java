package net.growthcraft.blocks;

import net.minecraft.block.SlabBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.DyeColor;

public class CheeseBlock extends SlabBlock {
    public DyeColor wax;
    public static final EnumProperty CHEESE_STATE = null;
    public CheeseBlock(Settings settings) {
        super(settings);
    }


    public CheeseBlock waxedBy(DyeColor color){
        this.wax = color;
        return this;
    }

}
