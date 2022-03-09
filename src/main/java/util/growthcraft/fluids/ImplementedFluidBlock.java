/**
 * Made by TeamReborn (Modified), licensed under the MIT License (MIT)
 * Source:
 * @see https://github.com/TechReborn/RebornCore/blob/1.16/src/main/java/reborncore/common/fluid/RebornFluid.java
 */
package util.growthcraft.fluids;

import net.minecraft.block.Block;
import net.minecraft.block.FluidBlock;

public class ImplementedFluidBlock extends FluidBlock {
    private final ImplementedFluid fluid;

    public ImplementedFluidBlock(ImplementedFluid fluid, Block.Settings properties) {
        super(fluid, properties);
        this.fluid = fluid;
    }

    public ImplementedFluid getFluid() {
        return fluid;
    }
}
