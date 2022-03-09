package net.growthcraft.machines.wort_cauldron;

import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public class WortCauldronBehavior {
	public static final Map<Item, CauldronBehavior> CORE = registerBehaviors();

	private static Map<Item, CauldronBehavior> registerBehaviors() {
		return new HashMap<>();
	}
}
