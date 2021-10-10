package net.growthcraft.entity;

import net.growthcraft.blocks.CheeseBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.growthcraft.blocks.CheeseBlock.CHEESE_STATE_BOTTOM;
import static net.growthcraft.blocks.CheeseBlock.CHEESE_STATE_TOP;

public class CheeseBlockEntity extends BlockEntity {
    protected int cureTime = 0;
    protected int maxCureTime = 80;
    
    public CheeseBlockEntity(BlockPos pos, BlockState state) {
        super(GrowthcraftBlockEntities.CHEESE_BLOCK_ENTITY, pos, state);
        setCureTime(16);
    }
    
    public void setCureTime(int seconds) {
        maxCureTime = seconds * 20;
    }
    
    public static <Be extends CheeseBlockEntity> void tick(World world, BlockPos blockPos, BlockState blockState, Be be) {
        CheeseBlock.CheeseState bottom = blockState.get(CHEESE_STATE_BOTTOM);
        CheeseBlock.CheeseState top = blockState.get(CHEESE_STATE_TOP);
        
        if (be.cureTime >= be.maxCureTime){
            if (bottom == CheeseBlock.CheeseState.WAXED || (bottom == CheeseBlock.CheeseState.UNAGED && ((CheeseBlock)blockState.getBlock()).wax == null)){
                world.setBlockState(blockPos,blockState.with(CHEESE_STATE_BOTTOM, CheeseBlock.CheeseState.AGED));
            }
            if (top == CheeseBlock.CheeseState.WAXED || (top == CheeseBlock.CheeseState.UNAGED && ((CheeseBlock)blockState.getBlock()).wax == null)){
                world.setBlockState(blockPos,blockState.with(CHEESE_STATE_TOP, CheeseBlock.CheeseState.AGED));
            }
        } else be.cureTime++;
    }
    
    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putInt("cureTime",cureTime);
        return super.writeNbt(nbt);
    }
    
    @Override
    public void readNbt(NbtCompound nbt) {
        cureTime = nbt.getInt("cureTime");
        super.readNbt(nbt);
    }
}
