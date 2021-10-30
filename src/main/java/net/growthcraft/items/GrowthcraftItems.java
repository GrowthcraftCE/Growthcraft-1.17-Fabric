package net.growthcraft.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.growthcraft.Growthcraft;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;

import java.util.ArrayList;
import java.util.List;

import static net.growthcraft.Growthcraft.MOD_ID;

public class GrowthcraftItems {
    public static final Item RED_WAX = registerItem(new WaxItem(new Item.Settings().group(Growthcraft.ITEMGROUP)).color(DyeColor.RED), "red_wax");
    public static final Item CUTTING_KNIFE = registerItem(new KnifeItem(new Item.Settings().group(Growthcraft.ITEMGROUP)), "cutting_knife");
    public static final Item CHEDDAR_SLICE  = registerItem(new Item(new Item.Settings().group(Growthcraft.ITEMGROUP).food(new FoodComponent.Builder().hunger(1000).saturationModifier(1000).build())), "cheddar_slice");
    public static final Item GORGONZOLA_SLICE  = registerItem(new Item(new Item.Settings().group(Growthcraft.ITEMGROUP).food(new FoodComponent.Builder().hunger(1000).saturationModifier(1000).build())), "cheddar_slice");
    public static final List<Item> CREATIVE_TAB_ITEMS = new ArrayList<>();
    
    public static void register(){}

    public static Item registerItem(Item item,String id) {
        return SimpleRegistry.register(Registry.ITEM, new Identifier(MOD_ID,id), item);
    }
}
