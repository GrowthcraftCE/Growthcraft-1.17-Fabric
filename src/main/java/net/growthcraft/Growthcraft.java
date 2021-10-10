package net.growthcraft;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.fabricmc.fabric.mixin.object.builder.ModelPredicateProviderRegistryAccessor;
import net.growthcraft.blocks.CheeseBlock;
import net.growthcraft.blocks.GrowthcraftBlocks;
import net.growthcraft.items.GrowthcraftItems;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Growthcraft implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger GROWTHCRAFT = LogManager.getLogger("growthcraft");
	public static final String MOD_ID = "growthcraft";

	public static ItemGroup ITEMGROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "item_group"),
			() -> new ItemStack(GrowthcraftItems.RED_WAX));
	public static ItemGroup CHEESES = FabricItemGroupBuilder.create(new Identifier(MOD_ID, "cheeses"))
			.appendItems(Growthcraft::getNBTItems).icon(() -> new ItemStack(GrowthcraftBlocks.CHEDDAR)).build();
	
	public static List<ItemStack> getNBTItems(List<ItemStack> stacks){
		for (CheeseBlock.CheeseState state : CheeseBlock.CheeseState.values()) {
			if (state != CheeseBlock.CheeseState.NONE && state != CheeseBlock.CheeseState.SLICED_QH && state != CheeseBlock.CheeseState.SLICED_H && state != CheeseBlock.CheeseState.SLICED_Q){
				ItemStack stack = new ItemStack(GrowthcraftBlocks.CHEDDAR.asItem());
				stack.getOrCreateNbt().putInt("cheese_state",(state.ordinal()-1));
				stacks.add(stack);
			}
		}
		return stacks;
	}
	
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		FabricModelPredicateProviderRegistry.register(GrowthcraftBlocks.CHEDDAR.asItem(), new Identifier("cheese_state"), (itemStack, world, livingEntity, i) -> {
			return CheeseBlock.CheeseState.fromStackRaw(itemStack);
		});
		
		GrowthcraftItems.register();
		GrowthcraftBlocks.register();
		GROWTHCRAFT.info("Including Cheese™");
	}
}
