package net.growthcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

public class BlockDef {
	protected Block block;
	
	public BlockDef(Block block){
		this.block = block;
	}
	
	public Block get(){
		return block;
	}
	
	public BlockState getDefault(){
		return block.getDefaultState();
	}
}
