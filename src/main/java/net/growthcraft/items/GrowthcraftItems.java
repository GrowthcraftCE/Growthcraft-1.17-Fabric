package net.growthcraft.items;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.growthcraft.GrowthCraftConstants;
import net.growthcraft.Growthcraft;
import net.growthcraft.blocks.CopperType;
import net.growthcraft.blocks.GrowthcraftBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;
import org.apache.commons.lang3.tuple.Triple;

import java.util.HashMap;
import java.util.Locale;

import static net.growthcraft.Growthcraft.MOD_ID;

public class GrowthcraftItems {
    public static class Singleton{
        public static final Item CUTTING_KNIFE = registerItem(new KnifeItem(new Item.Settings().group(Growthcraft.ITEMGROUP)), "cutting_knife");
        public static final Item CROWBAR = registerItem(new CrowbarItem(new Item.Settings().group(Growthcraft.ITEMGROUP)), "crowbar");
        public static final Item RICE_SEEDS = registerItem(new Item(new Item.Settings().group(Growthcraft.ITEMGROUP)), "rice_seeds");
    }
    private enum Milks{
        // Here add milk
        CHOCOLATE_MILK
    }
    
    // Auto generated groups of items (Wax, Milk, etc.)
    
    private static final HashMap<DyeColor,WaxItem> waxes = registerWaxes();
    private static HashMap<DyeColor,WaxItem> registerWaxes() {
        HashMap<DyeColor,WaxItem> map = new HashMap<DyeColor, WaxItem>();
        for (DyeColor color : DyeColor.values()){
            map.put(color,(WaxItem) registerItem(new WaxItem(new Item.Settings().group(Growthcraft.ITEMGROUP)).color(color), color.asString().toLowerCase(Locale.ROOT)+"_wax"));
            GrowthcraftBlocks.registerBlock(color.asString().toLowerCase(Locale.ROOT)+"_wax_block",addWaxBlock(new Block(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)),color,CopperType.BLOCK));
            GrowthcraftBlocks.registerBlock(color.asString().toLowerCase(Locale.ROOT)+"_wax_bricks",addWaxBlock(new Block(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)),color,CopperType.BRICKS));
            GrowthcraftBlocks.registerBlock(color.asString().toLowerCase(Locale.ROOT)+"_wax_cut_block",addWaxBlock(new Block(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)),color,CopperType.CUT));
        }
        return map;
    }

    private static Block addWaxBlock(Block block, DyeColor color, CopperType copperType) {
        GrowthcraftBlocks.wax_blocks.add(Triple.of(block,color,copperType));
        return block;
    }

    public static Item getWaxItem(DyeColor color){
        return waxes.get(color);
    }
    private static final HashMap<Pair<Milks,FluidContainerType>,Item> milks = registerMilks();
    private static HashMap<Pair<Milks,FluidContainerType>,Item> registerMilks() {
        HashMap<Pair<Milks,FluidContainerType>,Item> map = new HashMap<Pair<Milks,FluidContainerType>,Item>();
        for (FluidContainerType type : FluidContainerType.values()) {
            for (Milks milk : Milks.values()) {
                map.put(new Pair<>(milk, type), registerItem(new MilkBucketItem(new Item.Settings().group(Growthcraft.ITEMGROUP)), (milk.name().toLowerCase(Locale.ROOT)) + "_" + type.name().toLowerCase(Locale.ROOT)));
            }
        }
        return map;
    }
    public static Item getMilkItem(FluidContainerType type, Milks milk){
        return milks.get(new Pair<Milks,FluidContainerType>(milk,type));
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
    public static HashMap<Pair<Milks,FluidContainerType>, Item> listOfMilks(){
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
