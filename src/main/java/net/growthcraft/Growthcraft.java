package net.growthcraft;

import datagen.growthcraft.DataGenerator;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.fabricmc.fabric.mixin.object.builder.ModelPredicateProviderRegistryAccessor;
import net.growthcraft.blocks.CheeseBlock;
import net.growthcraft.blocks.GrowthcraftBlocks;
import net.growthcraft.items.GrowthcraftItems;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.item.UnclampedModelPredicateProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Growthcraft implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger GROWTHCRAFT = LogManager.getLogger("growthcraft");
	public static final String MOD_ID = "growthcraft";
	
	public static List<Item> CREATIVE_TAB_ITEMS = new ArrayList<>();

	public static ItemGroup ITEMGROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "item_group"),
			() -> new ItemStack(GrowthcraftItems.RED_WAX));
	public static ItemGroup CHEESES = FabricItemGroupBuilder.create(new Identifier(MOD_ID, "cheeses"))
			.appendItems(Growthcraft::getNBTItems).icon(() -> new ItemStack(GrowthcraftBlocks.Cheeses.CHEDDAR)).build();
	
	public static List<ItemStack> getNBTItems(List<ItemStack> stacks){
		for (CheeseBlock.CheeseState state : CheeseBlock.CheeseState.values()) {
			if (state != CheeseBlock.CheeseState.NONE){
				CREATIVE_TAB_ITEMS.forEach(item -> {
					ItemStack stack = new ItemStack(item);
					stack.getOrCreateNbt().putInt("cheese_state",(state.ordinal()-1));
					stacks.add(stack);
				});
			}
		}
		return stacks;
	}
	
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		GrowthCraftConstants.isGameRunning = true;
		
		GrowthcraftItems.register();
		GrowthcraftBlocks.register();
		
		new DataGenerator().generate();
		
		FabricModelPredicateProviderRegistry.register(GrowthcraftBlocks.Cheeses.CHEDDAR.asItem(), new Identifier("cheese_state"), new UnclampedModelPredicateProvider() {
			@Override
			public float call(ItemStack itemStack, @Nullable ClientWorld clientWorld, @Nullable LivingEntity livingEntity, int i) {
				return CheeseBlock.CheeseState.fromStackRaw(itemStack);
			}
			
			@Override
			public float unclampedCall(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity entity, int seed) {
				return call(stack, world, entity, seed);
			}
		});
		FabricModelPredicateProviderRegistry.register(GrowthcraftBlocks.Cheeses.GORGONZOLA.asItem(), new Identifier("cheese_state"), new UnclampedModelPredicateProvider() {
			@Override
			public float call(ItemStack itemStack, @Nullable ClientWorld clientWorld, @Nullable LivingEntity livingEntity, int i) {
				return CheeseBlock.CheeseState.fromStackRaw(itemStack);
			}
			
			@Override
			public float unclampedCall(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity entity, int seed) {
				return call(stack, world, entity, seed);
			}
		});
		FabricModelPredicateProviderRegistry.register(GrowthcraftBlocks.Cheeses.EMMENTALER.asItem(), new Identifier("cheese_state"), (itemStack, world, livingEntity, i) -> {
			return CheeseBlock.CheeseState.fromStackRaw(itemStack);
		});
		FabricModelPredicateProviderRegistry.register(GrowthcraftBlocks.Cheeses.MONTEREY.asItem(), new Identifier("cheese_state"), (itemStack, world, livingEntity, i) -> {
			return CheeseBlock.CheeseState.fromStackRaw(itemStack);
		});
		
		GROWTHCRAFT.info("Including Cheese™");
	}
}
