package net.growthcraft.machines.brew_kettle;

import net.growthcraft.machines.core.Machine;
import net.growthcraft.machines.core.MachineBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class BrewKettleBlockEntity extends MachineBlockEntity {
	public BrewKettleBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, Machine machine) {
		super(type, pos, state, machine);
	}
}
