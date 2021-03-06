package net.growthcraft.machines.core;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public abstract class Machine {
    public boolean hasContainer = false;
    public int propertiesSize;
    public int invSize;
    private BlockEntityType<?> blockEntityType;

    public Machine(boolean hasContainer){
        this.hasContainer = false;
        this.invSize = 0;
    }

    public Machine(boolean hasContainer, int invSize, int propertiesSize){
        if (hasContainer){
            this.hasContainer = true;
            this.invSize = invSize;
            this.propertiesSize = propertiesSize;
        }
    }

    public abstract BlockEntityType<?> getType();
    
    public void tick(World world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
    }

    public VoxelShape getVoxelShape() {
        return null;
    }

    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MachineBlockEntity(getType(), pos, state, this);
    }

    @Contract(" -> new")
    public final @NotNull Block createBlock(){
        return new MachineBlock(this);
    }
}