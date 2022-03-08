package net.growthcraft.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CrowbarItem extends MiningToolItem {
	public CrowbarItem(Settings settings) {
		// TODO: Change BlockTags.PICKAXE_MINEABLE to BlockTags.CROWBAR_MINEABLE when mod ported to 1.18
		super(1, -2.8F, ToolMaterials.IRON, BlockTags.PICKAXE_MINEABLE,settings);
	}
	
	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(new TranslatableText("tooltip.growthcraft.crowbar"));
		super.appendTooltip(stack, world, tooltip, context);
	}
	
	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		
		BlockState block = context.getWorld().getBlockState(context.getBlockPos());
		block.rotate(BlockRotation.CLOCKWISE_90);
		return super.useOnBlock(context);
	}
}
