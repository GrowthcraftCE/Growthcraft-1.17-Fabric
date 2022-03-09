/**
 * Made by TeamReborn (Modified), licensed under the MIT License (MIT)
 * Source:
 * @see https://github.com/TechReborn/RebornCore/blob/1.16/src/main/java/reborncore/common/fluid/RebornFluid.java
 */
package util.growthcraft.fluids;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;

public interface ItemFluidInfo {

    ItemStack getEmpty();

    ItemStack getFull(Fluid fluid);

    Fluid getFluid(ItemStack itemStack);

}