package net.growthcraft.items;

import net.growthcraft.GrowthCraftConstants;
import net.growthcraft.Growthcraft;
import net.minecraft.item.*;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;

import java.util.HashMap;
import java.util.Locale;

import static net.growthcraft.Growthcraft.MOD_ID;

public class GrowthcraftItems {
    public static class Singleton{
        public static final Item CUTTING_KNIFE = registerItem(new KnifeItem(new Item.Settings().group(Growthcraft.ITEMGROUP)), "cutting_knife");
    }
    private enum Milks{
        CHOCOLATE_MILK
    }
    
    // Auto generated groups of items (Wax, Milk, etc.)
    
    private static final HashMap<DyeColor,WaxItem> waxes = registerWaxes();
    private static HashMap<DyeColor,WaxItem> registerWaxes() {
        HashMap<DyeColor,WaxItem> map = new HashMap<DyeColor, WaxItem>();
        for (DyeColor color : DyeColor.values()){
            map.put(color,(WaxItem) registerItem(new WaxItem(new Item.Settings().group(Growthcraft.ITEMGROUP)).color(color), color.asString().toLowerCase(Locale.ROOT)+"_wax"));
        }
        return map;
    }
    public static Item getWaxItem(DyeColor color){
        return waxes.get(color);
    }
    private static final HashMap<Milks,Item> milks = registerMilks();
    private static HashMap<Milks,Item> registerMilks() {
        HashMap<Milks,Item> map = new HashMap<Milks, Item>();
        for (Milks milk : Milks.values()){
            map.put(milk,registerItem(new MilkBucketItem(new Item.Settings().group(Growthcraft.ITEMGROUP)), milk.name().toLowerCase(Locale.ROOT)));
        }
        return map;
    }
    public static Item getMilkItem(Milks milk){
        return milks.get(milk);
    }
    /**
     * This is only for data gen. DO NOT USE THIS!
     */
    @Deprecated
    public static HashMap<DyeColor, WaxItem> listOfWaxes(){
        return waxes;
    }
    /**
     * This is only for data gen. DO NOT USE THIS!
     */
    @Deprecated
    public static HashMap<Milks, Item> listOfMilks(){
        return milks;
    }
    
    public static void register(){}
    
    public static Item registerItem(Item item,String id) {
        if (GrowthCraftConstants.isGameRunning)
            return SimpleRegistry.register(Registry.ITEM, new Identifier(MOD_ID,id), item);
        else return item;
    }

    protected static ItemGroup defaultGroup(){
        if (GrowthCraftConstants.isGameRunning) return Growthcraft.ITEMGROUP; else return null;
    }
}
