package net.growthcraft.machines.wort_cauldron;

import net.growthcraft.Growthcraft;
import net.growthcraft.items.GrowthcraftItems;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public class WortCauldronBehavior {
	public static final Map<Item, CauldronBehavior> CORE = registerBehaviors();

	private static Map<Item, CauldronBehavior> registerBehaviors() {
		HashMap<Item, CauldronBehavior> coreMap = new HashMap<>();
		coreMap.put(GrowthcraftItems.Singleton.BREW_KETTLE,CauldronBehavior.FILL_WITH_WATER);
		return coreMap;
	}
}
