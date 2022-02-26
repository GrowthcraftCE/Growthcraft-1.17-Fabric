package net.growthcraft.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CrowbarItem extends Item {
	public CrowbarItem(Settings settings) {
		super(settings);
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
