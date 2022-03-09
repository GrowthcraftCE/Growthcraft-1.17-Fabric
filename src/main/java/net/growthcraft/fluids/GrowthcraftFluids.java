/**
 * Made by TeamImplemented (Modified), licensed under the MIT License (MIT)
 * Source:
 * @see https://github.com/TechImplemented/ImplementedCore/blob/1.16/src/main/java/reborncore/common/fluid/ImplementedFluid.java
 */
package net.growthcraft.fluids;

import net.minecraft.util.DyeColor;
import net.minecraft.util.Pair;

import java.util.HashMap;
import java.util.Locale;

public class GrowthcraftFluids {
	public enum GrainTypes{
		PALE_GOLDEN,
		GOLDEN,
		AMBER,
		DEEP_AMBER,
		COPPER,
		DEEP_COPPER,
		BROWN,
		DARK
	}

	public static final HashMap<DyeColor,FluidDef> MOLTEN_WAXES = new HashMap<>();
	// Pair of WortTypes and isHopped
	public static final HashMap<Pair<GrainTypes,Boolean>,FluidDef> WORTS = new HashMap<>();

	public static void register(){
		for (DyeColor waxColor : DyeColor.values()){
			MOLTEN_WAXES.put(waxColor,new FluidDef("molten_"+waxColor.asString().toLowerCase(Locale.ROOT)+"_wax").register());
		}
		for (GrainTypes wortType : GrainTypes.values()){
			WORTS.put(new Pair<>(wortType,false),new FluidDef(wortType.name().toLowerCase(Locale.ROOT)+"_wort").register());
			WORTS.put(new Pair<>(wortType,true),new FluidDef("hopped_"+wortType.name().toLowerCase(Locale.ROOT)+"_wort").register());
		}
	}
}