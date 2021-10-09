package net.growthcraft.blocks;

import net.growthcraft.entity.CheeseBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class CheeseBlock extends SlabBlockWithEntity {
    public DyeColor wax;
    public static final EnumProperty CHEESE_STATE = null;
    public CheeseBlock(Settings settings) {
        super(settings);
    }


    public CheeseBlock waxedBy(DyeColor color){
        this.wax = color;
        return this;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CheeseBlockEntity(pos, state);
    }
}
