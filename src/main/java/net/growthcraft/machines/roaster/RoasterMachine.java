package net.growthcraft.machines.roaster;

import net.growthcraft.entity.GrowthcraftBlockEntities;
import net.growthcraft.machines.core.IContainerMachine;
import net.growthcraft.machines.core.Machine;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RoasterMachine extends Machine implements IContainerMachine {
    public RoasterMachine() {
        super(true,3);
    }

    @Override
    public BlockEntityType<?> getType() {
        return GrowthcraftBlockEntities.ROASTER_BLOCK_ENTITY;
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        //world.setBlockState(pos, Blocks.AMETHYST_CLUSTER.getDefaultState());
    }

    @Override
    public ScreenHandler getMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player, Inventory inv) {
        return new BoxScreenHandler(syncId, playerInventory, inv);
    }
}
