package net.growthcraft.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.growthcraft.Growthcraft;
import net.growthcraft.blocks.GrowthcraftBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class GrowthcraftBlockEntities {

    public static BlockEntityType<CheeseBlockEntity> CHEESE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, Growthcraft.MOD_ID+":cheese", FabricBlockEntityTypeBuilder.create(CheeseBlockEntity::new, GrowthcraftBlocks.Cheeses.CHEDDAR, GrowthcraftBlocks.Cheeses.GORGONZOLA).build());

}
