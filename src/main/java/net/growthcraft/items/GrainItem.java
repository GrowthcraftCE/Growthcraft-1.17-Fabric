package net.growthcraft.items;

import net.growthcraft.fluids.GrowthcraftFluids;
import net.minecraft.item.Item;

public class GrainItem extends Item {
	private final GrowthcraftFluids.GrainTypes type;

	public GrainItem(GrowthcraftFluids.GrainTypes type, Settings settings) {
		super(settings);
		this.type = type;
		GrowthcraftItems.grains.put(type,this);
	}
}
