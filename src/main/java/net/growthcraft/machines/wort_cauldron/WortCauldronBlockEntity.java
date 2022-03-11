package net.growthcraft.machines.wort_cauldron;

import net.growthcraft.entity.GrowthcraftBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class WortCauldronBlockEntity extends BlockEntity {
	public int progress;

	public WortCauldronBlockEntity(BlockPos pos, BlockState state) {
		super(GrowthcraftBlockEntities.WORT_CAULDRON_ENTITY, pos, state);
	}
}
