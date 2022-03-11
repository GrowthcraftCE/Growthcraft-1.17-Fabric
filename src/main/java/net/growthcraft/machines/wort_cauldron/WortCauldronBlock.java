package net.growthcraft.machines.wort_cauldron;

import net.growthcraft.machines.core.IBlockEntityProvider;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import static net.minecraft.block.Blocks.CAULDRON;

public class WortCauldronBlock extends AbstractCauldronBlock implements IBlockEntityProvider {
	public WortCauldronBlock() {
		super(AbstractBlock.Settings.copy(CAULDRON), WortCauldronBehavior.CORE);
	}

	protected double getFluidHeight(BlockState state) {
		return 0.9375D;
	}

	public boolean isFull(BlockState state) {
		return true;
	}

	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (this.isEntityTouchingFluid(state, pos, entity)) {
			if (entity instanceof LivingEntity){
				//((LivingEntity)entity).addStatusEffect() flammableEffect
			}
		}

	}

	public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
		return 3;
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new WortCauldronBlockEntity(pos,state);
	}
}
