package net.growthcraft.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.growthcraft.Growthcraft;
import net.growthcraft.blocks.GrowthcraftBlocks;
import net.growthcraft.machines.core.MachineBlockEntity;
import net.growthcraft.machines.roaster.RoasterScreenHandler;
import net.growthcraft.machines.wort_cauldron.WortCauldronBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
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
    public static BlockEntityType<MachineBlockEntity> ROASTER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, Growthcraft.MOD_ID+":roaster", FabricBlockEntityTypeBuilder.create(
            (p,b)->new MachineBlockEntity(GrowthcraftBlockEntities.ROASTER_BLOCK_ENTITY,p,b,GrowthcraftBlocks.Custom.ROASTER.getMachine().get()),
            GrowthcraftBlocks.Custom.ROASTER.get()
    ).build());
    public static BlockEntityType<WortCauldronBlockEntity> WORT_CAULDRON_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, Growthcraft.MOD_ID+":wort_cauldron", FabricBlockEntityTypeBuilder.create(
            WortCauldronBlockEntity::new,
            GrowthcraftBlocks.Custom.WORT_CAULDRON.get()
    ).build());

    public static final ScreenHandlerType<RoasterScreenHandler> ROASTER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(Growthcraft.MOD_ID+":roaster"), RoasterScreenHandler::new);
}
