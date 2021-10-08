package net.growthcraft;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.growthcraft.blocks.GrowthcraftBlocks;
import net.growthcraft.items.GrowthcraftItems;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Growthcraft implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger GROWTHCRAFT = LogManager.getLogger("growthcraft");
	public static final String MOD_ID = "growthcraft";

	public static ItemGroup ITEMGROUP = FabricItemGroupBuilder.build(
			new Identifier(MOD_ID, "item_group"),
			() -> new ItemStack(Blocks.COAL_BLOCK));


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		GrowthcraftItems.register();
		GrowthcraftBlocks.register();
		GROWTHCRAFT.info("Hello Fabric world!");
	}
}
