package net.growthcraft.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.growthcraft.Growthcraft;
import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;

import static net.growthcraft.Growthcraft.MOD_ID;

public class GrowthcraftItems {
    public static final Item RED_WAX = registerItem(new WaxItem(new Item.Settings().group(Growthcraft.ITEMGROUP)).color(DyeColor.RED), "red_wax");

    public static void register(){}

    public static Item registerItem(Item item,String id) {
        return SimpleRegistry.register(Registry.ITEM, new Identifier(MOD_ID,id), item);
    }
}
