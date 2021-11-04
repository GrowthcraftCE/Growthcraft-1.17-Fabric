package net.growthcraft.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.growthcraft.Growthcraft;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;

import java.util.ArrayList;
import java.util.List;

import static net.growthcraft.Growthcraft.MOD_ID;
import static net.growthcraft.items.GrowthcraftItems.registerItem;

public class GrowthcraftBlocks {

    public static List<CheeseDef> cheeseDefList = new ArrayList<>();
    public static class Cheeses{
        public static final CheeseDef CHEDDAR = addCheese("cheddar",DyeColor.RED);
        public static final CheeseDef GORGONZOLA = addCheese("gorgonzola", null);
        public static final CheeseDef EMMENTALER = addCheese("emmentaler", null);
        public static final CheeseDef MONTEREY = addCheese("monterey", DyeColor.BLACK);
    
        private static CheeseDef addCheese(String id, DyeColor wax) {
            Item slice = registerItem(new Item(new Item.Settings().group(Growthcraft.ITEMGROUP).food(new FoodComponent.Builder().hunger(6).saturationModifier(2).build())), id+"_slice");
            CheeseDef def = new CheeseDef(registerBlock(id, new CheeseBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)).waxedBy(wax)),slice);
            cheeseDefList.add(def);
            registerClientBlock(def);
            return def;
        }
    
        private static void registerClientBlock(CheeseDef def) {
            Growthcraft.registerPredicateProvider(def.get().asItem(),"cheese_state");
        }
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
