package net.growthcraft;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.impl.blockrenderlayer.BlockRenderLayerMapImpl;
import net.growthcraft.blocks.GrowthcraftBlocks;
import net.growthcraft.entity.GrowthcraftBlockEntities;
import net.growthcraft.machines.roaster.RoasterScreen;
import net.minecraft.client.render.RenderLayer;

public class GrowthcraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(GrowthcraftBlockEntities.BOX_SCREEN_HANDLER, RoasterScreen::new);

        BlockRenderLayerMapImpl.INSTANCE.putBlock(GrowthcraftBlocks.Custom.GRAPE_LEAVES.get(), RenderLayer.getCutout());
    }
}
