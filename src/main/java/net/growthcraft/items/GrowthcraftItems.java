package net.growthcraft.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.growthcraft.GrowthCraftConstants;
import net.growthcraft.Growthcraft;
import net.minecraft.item.*;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;

import java.util.ArrayList;
import java.util.List;

import static net.growthcraft.Growthcraft.MOD_ID;

public class GrowthcraftItems {
    public static final Item RED_WAX = registerItem(new WaxItem(new Item.Settings().group(Growthcraft.ITEMGROUP)).color(DyeColor.RED), "red_wax");
    public static final Item WHITE_WAX = registerItem(new WaxItem(new Item.Settings().group(Growthcraft.ITEMGROUP)).color(DyeColor.WHITE), "white_wax");
    public static final Item BLACK_WAX = registerItem(new WaxItem(new Item.Settings().group(Growthcraft.ITEMGROUP)).color(DyeColor.BLACK), "black_wax");
    public static final Item YELLOW_WAX = registerItem(new WaxItem(new Item.Settings().group(Growthcraft.ITEMGROUP)).color(DyeColor.YELLOW), "yellow_wax");
    public static final Item CUTTING_KNIFE = registerItem(new KnifeItem(new Item.Settings().group(Growthcraft.ITEMGROUP)), "cutting_knife");
    public static final Item CHEDDAR_SLICE  = registerItem(new Item(new Item.Settings().group(Growthcraft.ITEMGROUP).food(new FoodComponent.Builder().hunger(6).saturationModifier(2).build())), "cheddar_slice");
    public static final Item GORGONZOLA_SLICE  = registerItem(new Item(new Item.Settings().group(Growthcraft.ITEMGROUP).food(new FoodComponent.Builder().hunger(6).saturationModifier(2).build())), "gorgonzola_slice");
    public static final List<Item> CREATIVE_TAB_ITEMS = new ArrayList<>();
    
    public static void register(){}

    public static Item registerItem(Item item,String id) {
        if (GrowthCraftConstants.isGameRunning)
            return SimpleRegistry.register(Registry.ITEM, new Identifier(MOD_ID,id), item);
        else return item;
    }

    public static ItemGroup defaultGroup(){
        if (GrowthCraftConstants.isGameRunning) return Growthcraft.ITEMGROUP; else return null;
    }
}
