package net.growthcraft.fluids;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.growthcraft.Growthcraft;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import util.growthcraft.fluids.*;

public class FluidDef {
    private ImplementedFluid stillFluid;
    private ImplementedFluid flowingFluid;

    private ImplementedFluidBlock block;
    private ImplementedBucketItem bucket;
    private final Identifier identifier;

    public FluidDef(String fluidName){
        this.identifier = new Identifier(Growthcraft.MOD_ID, fluidName);

        Identifier texture_still = new Identifier(Growthcraft.MOD_ID, "block/fluids/" + fluidName + "_still");
        Identifier texture_flowing = new Identifier(Growthcraft.MOD_ID, "block/fluids/" + fluidName + "_flowing");

        FluidSettings fluidSettings = FluidSettings.create();

        fluidSettings.setStillTexture(texture_still);
        fluidSettings.setFlowingTexture(texture_flowing);

        stillFluid = new ImplementedFluid(true, fluidSettings, () -> block, () -> bucket, () -> flowingFluid, () -> stillFluid) {
        };
        flowingFluid = new ImplementedFluid(false, fluidSettings, () -> block, () -> bucket, () -> flowingFluid, () -> stillFluid) {
        };

        block = new ImplementedFluidBlock(stillFluid, FabricBlockSettings.of(Material.WATER).noCollision().hardness(100.0F).dropsNothing());
        bucket = new ImplementedBucketItem(stillFluid, new Item.Settings().group(Growthcraft.ITEMGROUP).recipeRemainder(Items.BUCKET).maxCount(1));
    }

    public FluidDef register() {
        ImplementedFluidManager.register(stillFluid, identifier);
        ImplementedFluidManager.register(flowingFluid, new Identifier(Growthcraft.MOD_ID, identifier.getPath() + "_flowing"));

        Registry.register(Registry.BLOCK, identifier, block);
        Registry.register(Registry.ITEM, new Identifier(Growthcraft.MOD_ID, identifier.getPath() + "_bucket"), bucket);

        return this;
    }

    public ImplementedFluid getFluid() {
        return stillFluid;
    }

    public ImplementedFluid getFlowingFluid() {
        return flowingFluid;
    }

    public ImplementedFluidBlock getBlock() {
        return block;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public ImplementedBucketItem getBucket() {
        return bucket;
    }
}
