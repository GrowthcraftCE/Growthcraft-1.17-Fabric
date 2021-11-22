package net.growthcraft.items;

import net.growthcraft.blocks.CheeseBlock;
import net.growthcraft.blocks.CopperType;
import net.growthcraft.blocks.GrowthcraftBlocks;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoneycombItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.Triple;

import static net.growthcraft.blocks.CheeseBlock.CHEESE_STATE_BOTTOM;
import static net.growthcraft.blocks.CheeseBlock.CHEESE_STATE_TOP;

public class WaxItem extends HoneycombItem {
	public DyeColor wax;
	public WaxItem(Settings settings) {
		super(settings);
	}
	
	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		super.useOnBlock(context);
		World world = context.getWorld();
		BlockPos blockPos = context.getBlockPos();
		BlockState state = world.getBlockState(blockPos);
		ItemStack itemStack = context.getStack();
		
		if (!world.isClient)
			if (state.getBlock() instanceof CheeseBlock) {
				if (((CheeseBlock) state.getBlock()).wax == this.wax) {
					CheeseBlock.CheeseState cheeseStateBottom = state.get(CHEESE_STATE_BOTTOM);
					CheeseBlock.CheeseState cheeseStateTop = state.get(CHEESE_STATE_TOP);
					
					if (cheeseStateBottom == CheeseBlock.CheeseState.UNAGED) {
						waxCheese(world, blockPos, state, SlabType.BOTTOM);
					}
					if (cheeseStateTop == CheeseBlock.CheeseState.UNAGED) {
						waxCheese(world, blockPos, state, SlabType.TOP);
					}
				}
			} else {
				for (Triple<Block,DyeColor, CopperType> t : GrowthcraftBlocks.wax_blocks){
					if (wax == t.getMiddle())
						if (t.getRight()==CopperType.BLOCK)
							world.setBlockState(blockPos,t.getLeft().getDefaultState());
				}
			}
		
		itemStack.decrement(1);
		return ActionResult.success(world.isClient);
	}
	
	private void waxCheese(World world, BlockPos pos, BlockState preWaxedState, SlabType slabType) {
		world.setBlockState(pos,preWaxedState.with(slabType == SlabType.BOTTOM ? CHEESE_STATE_BOTTOM : CHEESE_STATE_TOP, CheeseBlock.CheeseState.WAXED));
	}
	
	public WaxItem color(DyeColor color){
		this.wax = color;
		return this;
	}
}