package net.growthcraft.items;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.growthcraft.GrowthcraftConstants;
import net.growthcraft.Growthcraft;
import net.growthcraft.blocks.CopperType;
import net.growthcraft.blocks.GrowthcraftBlocks;
import net.growthcraft.fluids.GrowthcraftFluids;
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
    public static final HashMap<GrowthcraftFluids.GrainTypes,Item> grains = new HashMap<>();

    public static class Singleton{
        public static final Item CUTTING_KNIFE = registerItem(new KnifeItem(new Item.Settings().group(Growthcraft.ITEMGROUP)), "cutting_knife");
        public static final Item CROWBAR = registerItem(new CrowbarItem(new Item.Settings().group(Growthcraft.ITEMGROUP)), "crowbar");
        public static final Item RICE_SEEDS = registerItem(new Item(new Item.Settings().group(Growthcraft.ITEMGROUP)), "rice_seeds");
        public static final Item CLOTH = registerItem(new Item(new Item.Settings().group(Growthcraft.ITEMGROUP)), "cheese_cloth");
        public static final Item BUTTER = registerItem(new Item(new Item.Settings().group(Growthcraft.ITEMGROUP)), "butter");
        public static final Item ROPE = registerItem(new Item(new Item.Settings().group(Growthcraft.ITEMGROUP)), "rope");
        public static final Item SALT_CRYSTAL = registerItem(new Item(new Item.Settings().group(Growthcraft.ITEMGROUP)), "salt_crystal");
        public static final Item RICE = registerItem(new Item(new Item.Settings().group(Growthcraft.ITEMGROUP)), "rice");
        public static final Item RICE_FLOUR = registerItem(new Item(new Item.Settings().group(Growthcraft.ITEMGROUP)), "rice_flour");
        public static final Item GRAIN = registerItem(new Item(new Item.Settings().group(Growthcraft.ITEMGROUP)), "grain");

        public static final Item PALE_GOLDEN_ROASTED_GRAIN = registerItem(new GrainItem(GrowthcraftFluids.GrainTypes.PALE_GOLDEN,new Item.Settings().group(Growthcraft.ITEMGROUP)), "pale_golden_roasted_grain");
        public static final Item GOLDEN_ROASTED_GRAIN = registerItem(new GrainItem(GrowthcraftFluids.GrainTypes.GOLDEN,new Item.Settings().group(Growthcraft.ITEMGROUP)), "golden_roasted_grain");
        public static final Item AMBER_ROASTED_GRAIN = registerItem(new GrainItem(GrowthcraftFluids.GrainTypes.AMBER,new Item.Settings().group(Growthcraft.ITEMGROUP)), "amber_roasted_grain");
        public static final Item DEEP_AMBER_ROASTED_GRAIN = registerItem(new GrainItem(GrowthcraftFluids.GrainTypes.DEEP_AMBER,new Item.Settings().group(Growthcraft.ITEMGROUP)), "deep_amber_roasted_grain");
        public static final Item COPPER_ROASTED_GRAIN = registerItem(new GrainItem(GrowthcraftFluids.GrainTypes.COPPER,new Item.Settings().group(Growthcraft.ITEMGROUP)), "copper_roasted_grain");
        public static final Item DEEP_COPPER_ROASTED_GRAIN = registerItem(new GrainItem(GrowthcraftFluids.GrainTypes.DEEP_COPPER,new Item.Settings().group(Growthcraft.ITEMGROUP)), "deep_copper_roasted_grain");
        public static final Item BROWN_ROASTED_GRAIN = registerItem(new GrainItem(GrowthcraftFluids.GrainTypes.BROWN,new Item.Settings().group(Growthcraft.ITEMGROUP)), "brown_roasted_grain");
        public static final Item DARK_ROASTED_GRAIN = registerItem(new GrainItem(GrowthcraftFluids.GrainTypes.DARK,new Item.Settings().group(Growthcraft.ITEMGROUP)), "dark_roasted_grain");

    }
    private enum Milks{
        // Here add milk
        RENNET,
        WHEY,
        BUTTER_MILK,
        CREAM,
        MILK_CURDS,
        SKIM_MILK,
        CONDENSED_MILK
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
        if (GrowthcraftConstants.isGameRunning)
            return SimpleRegistry.register(Registry.ITEM, new Identifier(MOD_ID,id), item);
        else return item;
    }

    protected static ItemGroup defaultGroup(){
        if (GrowthcraftConstants.isGameRunning) return Growthcraft.ITEMGROUP; else return null;
    }
}
