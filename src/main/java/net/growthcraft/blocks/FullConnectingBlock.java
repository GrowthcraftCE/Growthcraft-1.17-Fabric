package net.growthcraft.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChorusPlantBlock;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.Iterator;

public class FullConnectingBlock extends ChorusPlantBlock {
	protected FullConnectingBlock(Settings settings) {
		super(settings);
	}

	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (!state.canPlaceAt(world, pos)) {
			world.getBlockTickScheduler().schedule(pos, this, 1);
			return state;
		} else {
			boolean bl = neighborState.isOf(this);
			return (BlockState)state.with((Property)FACING_PROPERTIES.get(direction), bl);
		}
	}

	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		return true;
	}
}
