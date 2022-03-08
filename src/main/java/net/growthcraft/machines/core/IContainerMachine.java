package net.growthcraft.machines.core;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;

public interface IContainerMachine {
    ScreenHandler getMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player, Inventory inv);
}
