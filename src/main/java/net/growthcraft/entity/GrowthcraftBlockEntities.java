package net.growthcraft.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.growthcraft.Growthcraft;
import net.growthcraft.blocks.GrowthcraftBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class GrowthcraftBlockEntities {

    public static BlockEntityType<CheeseBlockEntity> CHEESE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, Growthcraft.MOD_ID+":cheese", FabricBlockEntityTypeBuilder.create(CheeseBlockEntity::new,
            GrowthcraftBlocks.Cheeses.CHEDDAR.get(),
            GrowthcraftBlocks.Cheeses.GORGONZOLA.get(),
            GrowthcraftBlocks.Cheeses.EMMENTALER.get(),
            GrowthcraftBlocks.Cheeses.MONTEREY.get(),
            GrowthcraftBlocks.Cheeses.APPLEZELLER.get(),
            GrowthcraftBlocks.Cheeses.ASIAGO.get(),
            GrowthcraftBlocks.Cheeses.PROVOLONE.get(),
            GrowthcraftBlocks.Cheeses.PARMESAN.get(),
            GrowthcraftBlocks.Cheeses.GOUDA.get(),
            GrowthcraftBlocks.Cheeses.CASU_MARZU.get()
    ).build());

}
