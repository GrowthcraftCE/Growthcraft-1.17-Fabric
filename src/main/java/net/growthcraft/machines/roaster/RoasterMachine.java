package net.growthcraft.machines.roaster;

import net.growthcraft.entity.GrowthcraftBlockEntities;
import net.growthcraft.machines.core.IContainerMachine;
import net.growthcraft.machines.core.Machine;
import net.growthcraft.machines.core.MachineBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class RoasterMachine extends Machine implements IContainerMachine {
    protected int roastProgress, roastTotal;
    
    public RoasterMachine() {
        super(true,3);
    }

    @Override
    public BlockEntityType<?> getType() {
        return GrowthcraftBlockEntities.ROASTER_BLOCK_ENTITY;
    }
    
    public static void tick(World world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        MachineBlockEntity be = (MachineBlockEntity) blockEntity;
        //roastProgress++;
        if (be.machine.propertyDelegate.get(0)<0)
            be.machine.propertyDelegate.set(0,200);
        else be.machine.propertyDelegate.set(0,be.machine.propertyDelegate.get(0)-1);
        if (be.machine.propertyDelegate.get(1) == 0)
            be.machine.propertyDelegate.set(1,new Random().nextInt(10));
    }

    @Override
    public ScreenHandler getMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player, Inventory inv) {
        return new RoasterScreenHandler(syncId, playerInventory, inv, propertyDelegate);
    }
    
    @Override
    public PropertyDelegate initPropertyDelegate() {
        return new PropertyDelegate() {
            public int get(int index) {
                switch(index) {
                    case 0:
                        return RoasterMachine.this.roastProgress;
                    case 1:
                        return RoasterMachine.this.roastTotal;
                    default:
                        return 0;
                }
            }
    
            public void set(int index, int value) {
                switch(index) {
                    case 0:
                        RoasterMachine.this.roastProgress = value;
                        break;
                    case 1:
                        RoasterMachine.this.roastTotal = value;
                        break;
                }
            }
    
            public int size() {
                return 2;
            }
        };
    }
}
