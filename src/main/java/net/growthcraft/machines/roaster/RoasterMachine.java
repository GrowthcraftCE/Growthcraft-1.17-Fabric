package net.growthcraft.machines.roaster;

import net.growthcraft.entity.GrowthcraftBlockEntities;
import net.growthcraft.machines.core.IContainerMachine;
import net.growthcraft.machines.core.Machine;
import net.growthcraft.machines.core.MachineBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;

import java.util.Random;

public class RoasterMachine extends Machine implements IContainerMachine {
    public RoasterMachine() {
        super(true,3,2);
    }

    @Override
    public BlockEntityType<?> getType() {
        return GrowthcraftBlockEntities.ROASTER_BLOCK_ENTITY;
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        MachineBlockEntity be = (MachineBlockEntity) blockEntity;
        //roastProgress++;
        if (be.propertyDelegate.get(0)<0)
            be.propertyDelegate.set(0,200);
        else be.propertyDelegate.set(0,be.propertyDelegate.get(0)-1);
        if (be.propertyDelegate.get(1) == 0)
            be.propertyDelegate.set(1,new Random().nextInt(10));
    }

    @Override
    public ScreenHandler getMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player, Inventory inv, PropertyDelegate propertyDelegate) {
        return new RoasterScreenHandler(syncId, playerInventory, inv, propertyDelegate);
    }

    @Override
    public VoxelShape getVoxelShape() {
        return Block.createCuboidShape(0f,0f,0f,16f,14f,16f);
    }
}
