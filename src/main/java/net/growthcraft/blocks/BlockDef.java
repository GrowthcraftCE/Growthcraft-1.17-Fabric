package net.growthcraft.blocks;

import net.growthcraft.machines.core.Machine;
import net.growthcraft.machines.core.MachineBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.Optional;

public class BlockDef {
	protected Block block;
	protected Optional<Machine> machine;
	
	public BlockDef(Block block){
		this.block = block;
		this.machine = Optional.empty();
	}

	public BlockDef(Machine machine, Block registeredBlock){
		this.block = registeredBlock;
		this.machine = Optional.of(machine);
	}

	public Block get(){
		return block;
	}
	
	public BlockState getDefault(){
		return block.getDefaultState();
	}
	
	public Item item() {
		return block.asItem();
	}

    public void init() {
		get();
    }

	/**
	 * A shortcut to always return {@code true} a context predicate, used as
	 * {@code settings.solidBlock(Blocks::always)}.
	 */
	public static boolean always(BlockState state, BlockView world, BlockPos pos) {
		return true;
	}

	/**
	 * A shortcut to always return {@code false} a context predicate, used as
	 * {@code settings.solidBlock(Blocks::never)}.
	 */
	public static boolean never(BlockState state, BlockView world, BlockPos pos) {
		return false;
	}

	public Optional<Machine> getMachine() {
		return machine;
	}
}
