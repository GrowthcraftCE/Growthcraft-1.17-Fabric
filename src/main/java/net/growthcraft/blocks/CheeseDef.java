package net.growthcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;

import java.util.NoSuchElementException;

public class CheeseDef extends BlockDef{
	private final Item slice;
	
	public static CheeseDef getCheese(Block block) {
		for (CheeseDef def : GrowthcraftBlocks.cheeseDefList) {
			if (def.get() == block) return def;
		}
		throw new NoSuchElementException();
	}
	
	public Item slice(){
		return slice;
	}
	
	public CheeseDef(Block block, Item slice){
		super(block);
		this.slice = slice;
	}
}