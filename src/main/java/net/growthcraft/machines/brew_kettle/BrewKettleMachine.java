package net.growthcraft.machines.brew_kettle;

import net.growthcraft.entity.GrowthcraftBlockEntities;
import net.growthcraft.machines.core.Machine;
import net.growthcraft.machines.core.MachineBlockEntity;
import net.growthcraft.machines.roaster.RoasterScreenHandler;
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

public class BrewKettleMachine extends Machine {
	public BrewKettleMachine() {
		super(false);
	}

	@Override
	public BlockEntityType<?> getType() {
		return GrowthcraftBlockEntities.BREW_KETTLE_BLOCK_ENTITY;
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new BrewKettleBlockEntity(getType(), pos, state, this);
	}

	@Override
	public void tick(World world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
		BrewKettleBlockEntity be = (BrewKettleBlockEntity) blockEntity;
	}
}
