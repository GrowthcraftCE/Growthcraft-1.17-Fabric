package net.growthcraft.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.loader.util.sat4j.core.ConstrGroup;
import net.growthcraft.Growthcraft;
import net.growthcraft.entity.GrowthcraftBlockEntities;
import net.growthcraft.machines.core.Machine;
import net.growthcraft.machines.roaster.RoasterMachine;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChorusPlantBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;

import static net.growthcraft.Growthcraft.MOD_ID;
import static net.growthcraft.items.GrowthcraftItems.registerItem;

public class GrowthcraftBlocks {
    public static List<CheeseDef> cheeseDefList = new ArrayList<>();
    public static List<Triple<Block,DyeColor,CopperType>> wax_blocks = new ArrayList<>();

    public static class Custom{
        public static final BlockDef GRAPE_LEAVES = registerBlock("grape_leaves",new FullConnectingBlock(FabricBlockSettings.copyOf(Blocks.AZALEA_LEAVES)));
        public static final BlockDef DIRT_SLAB = registerBlock("dirt_slab",new SlabBlock(FabricBlockSettings.copyOf(Blocks.DIRT)));
        public static final BlockDef RICE_PAD = registerBlock("rice_pad",new FarmlandSlab(FabricBlockSettings.copyOf(Blocks.FARMLAND)));

        public static final BlockDef ROASTER = registerBlock("roaster", new RoasterMachine());

        public static final BlockDef BREW_KETTLE = registerBlock("brew_kettle",new Block(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)));
    }

    public static class Singleton{
        public static final BlockDef COPPER_BRICKS = registerBlock("copper_bricks",new Block(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)));
        public static final BlockDef SALT_BLOCK = registerBlock("salt_block",new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
        public static final BlockDef SAND_SALT_ORE = registerBlock("sand_salt_ore",new Block(FabricBlockSettings.copyOf(Blocks.SAND)));
        public static final BlockDef GRAVEL_SALT_ORE = registerBlock("gravel_salt_ore",new Block(FabricBlockSettings.copyOf(Blocks.GRAVEL)));
        public static final BlockDef SALT_ORE = registerBlock("salt_ore",new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
        public static final BlockDef DEEPSLATE_SALT_ORE = registerBlock("deepslate_salt_ore",new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE)));
        public static final BlockDef RICE = registerBlock("rice_block",new RiceCropBlock(FabricBlockSettings.copyOf(Blocks.DIRT)));
    }

    public static class Slabs{
    }
    public static class Cheeses{
        // Add cheese here:
        public static final CheeseDef CHEDDAR = addCheese("cheddar",DyeColor.RED);
        public static final CheeseDef GORGONZOLA = addCheese("gorgonzola", null);
        public static final CheeseDef EMMENTALER = addCheese("emmentaler", null);
        public static final CheeseDef MONTEREY = addCheese("monterey", DyeColor.BLACK);
        public static final CheeseDef APPLEZELLER = addCheese("applezeller", null);
        public static final CheeseDef ASIAGO = addCheese("asiago", null);
        public static final CheeseDef GOUDA = addCheese("gouda", DyeColor.YELLOW);
        public static final CheeseDef PARMESAN = addCheese("parmesan", DyeColor.BROWN);
        public static final CheeseDef PROVOLONE = addCheese("provolone", DyeColor.WHITE);
        public static final CheeseDef CASU_MARZU = addCheese("casu_marzu", null);
        
        private static CheeseDef addCheese(String id, DyeColor wax) {
            Item slice = registerItem(new Item(new Item.Settings().group(Growthcraft.ITEMGROUP).food(new FoodComponent.Builder().hunger(6).saturationModifier(2).build())), id+"_slice");
            Item curds = registerItem(new Item(new Item.Settings().group(Growthcraft.ITEMGROUP)), id+"_curds");
            CheeseDef def = new CheeseDef(registerBlock(id, new CheeseBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)).waxedBy(wax)),slice, curds);
            Growthcraft.CREATIVE_TAB_ITEMS.add(def.item());
            cheeseDefList.add(def);
            registerClientBlock(def);
            return def;
        }
    
        private static void registerClientBlock(CheeseDef def) {
            Growthcraft.registerPredicateProvider(def.get().asItem(),"cheese_state");
        }
    }

    public static BlockDef registerBlock(String id, Machine machineBlock) {
        return registerBlock(id,machineBlock,true);
    }

    public static BlockDef registerBlock(String id, Machine machineBlock, boolean hasItem) {
        Block createdBlock = machineBlock.createBlock();
        if (hasItem) {
            Item item = new BlockItem(createdBlock, new Item.Settings().group(Growthcraft.ITEMGROUP));
            SimpleRegistry.register(Registry.ITEM, new Identifier(MOD_ID, id), item);
        }
        return new BlockDef(machineBlock, SimpleRegistry.register(Registry.BLOCK, new Identifier(MOD_ID,id), createdBlock));
    }

    public static BlockDef registerBlock(String id, Block block) {
        return registerBlock(id,block,true);
    }

    public static BlockDef registerBlock(String id, Block block, boolean hasItem) {
        if (hasItem) {
            Item item = new BlockItem(block, new Item.Settings().group(Growthcraft.ITEMGROUP));
            SimpleRegistry.register(Registry.ITEM, new Identifier(MOD_ID, id), item);
        }
        return new BlockDef(SimpleRegistry.register(Registry.BLOCK, new Identifier(MOD_ID,id), block));
    }

    public static void register(){
        // Initialize one block from inner class to initialize all of them.
        Singleton.RICE.init();
        Singleton.DEEPSLATE_SALT_ORE.init();
        Custom.GRAPE_LEAVES.init();
    }
}