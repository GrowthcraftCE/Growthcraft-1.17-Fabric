package net.growthcraft.items;

import net.growthcraft.blocks.CheeseBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

import static net.growthcraft.blocks.CheeseBlock.CHEESE_STATE_BOTTOM;
import static net.growthcraft.blocks.CheeseBlock.CHEESE_STATE_TOP;

public class KnifeItem extends SwordItem {
	public KnifeItem(Settings settings) {
		super(ToolMaterials.IRON, 1, 4, settings.maxDamage(ToolMaterials.IRON.getDurability()));
	}
	
	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		super.useOnBlock(context);
		World world = context.getWorld();
		BlockPos blockPos = context.getBlockPos();
		BlockState state = world.getBlockState(blockPos);
		ItemStack itemStack = context.getStack();
		PlayerEntity player = Objects.requireNonNull(context.getPlayer());
		
		if (!world.isClient) if (state.getBlock() instanceof CheeseBlock) {
			CheeseBlock.CheeseState cheeseStateBottom = state.get(CHEESE_STATE_BOTTOM);
			CheeseBlock.CheeseState cheeseStateTop = state.get(CHEESE_STATE_TOP);
			
			int readyToCut = CheeseBlock.CheeseState.AGED.ordinal();
			if ((cheeseStateBottom.ordinal() >= CheeseBlock.CheeseState.values().length || cheeseStateBottom == CheeseBlock.CheeseState.NONE)
							&& (cheeseStateTop.ordinal() >= CheeseBlock.CheeseState.values().length || cheeseStateBottom == CheeseBlock.CheeseState.NONE))
				world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
			if (cheeseStateBottom.ordinal() >= readyToCut){
				cutCheese(world,blockPos,state, SlabType.BOTTOM, player,cheeseStateBottom.ordinal()+1);
			}
			else if (cheeseStateTop.ordinal() >= readyToCut){
				cutCheese(world,blockPos,state,SlabType.TOP, player,cheeseStateTop.ordinal()+1);
			}
		}
		
		itemStack.damage(1, player, __->{});
		return ActionResult.success(world.isClient);
	}
	
	private void cutCheese(World world, BlockPos blockPos, BlockState preWaxedState, SlabType slabType, PlayerEntity entity, int nextCutState) {
		world.setBlockState(blockPos,preWaxedState.with(slabType == SlabType.BOTTOM ? CHEESE_STATE_BOTTOM : CHEESE_STATE_TOP, nextCutState >= CheeseBlock.CheeseState.values().length ? CheeseBlock.CheeseState.NONE : CheeseBlock.CheeseState.values()[nextCutState]));
		entity.giveItemStack(new ItemStack(GrowthcraftItems.CHEDDAR_SLICE,4));
	}
}
