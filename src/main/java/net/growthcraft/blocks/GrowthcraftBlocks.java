package net.growthcraft.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.growthcraft.Growthcraft;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;

import static net.growthcraft.Growthcraft.MOD_ID;

public class GrowthcraftBlocks {

    public static class Cheeses{
        public static final Block CHEDDAR = registerBlock("cheddar", new CheeseBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)).waxedBy(DyeColor.RED));
        public static final Block GORGONZOLA = registerBlock("gorgonzola", new CheeseBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)));
    }

    public static Block registerBlock(String id, Block block) {
        return registerBlock(id,block,true);
    }

    public static Block registerBlock(String id, Block block, boolean hasItem) {
        if (hasItem) {
            Item item = new BlockItem(block, new Item.Settings().group(Growthcraft.ITEMGROUP));
            SimpleRegistry.register(Registry.ITEM, new Identifier(MOD_ID, id), item);
            Growthcraft.CREATIVE_TAB_ITEMS.add(item);
        }
        return SimpleRegistry.register(Registry.BLOCK, new Identifier(MOD_ID,id), block);
    }

    public static void register(){}
}
