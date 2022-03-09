/**
 * Made by TeamReborn (Modified), licensed under the MIT License (MIT)
 * Source:
 * @see https://github.com/TechReborn/RebornCore/blob/1.16/src/main/java/reborncore/common/fluid/RebornFluid.java
 */
package util.growthcraft.fluids;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.function.Supplier;

public abstract class ImplementedFluid extends FlowableFluid {
    private final boolean still;

    private final FluidSettings fluidSettings;
    private final Supplier<ImplementedFluidBlock> fluidBlockSupplier;
    private final Supplier<ImplementedBucketItem> bucketItemSuppler;
    private final Supplier<ImplementedFluid> flowingSuppler;
    private final Supplier<ImplementedFluid> stillSuppler;

    public ImplementedFluid(boolean still, FluidSettings fluidSettings,
                            Supplier<ImplementedFluidBlock> fluidBlockSupplier,
                            Supplier<ImplementedBucketItem> bucketItemSuppler,
                            Supplier<ImplementedFluid> flowingSuppler,
                            Supplier<ImplementedFluid> stillSuppler) {
        this.still = still;
        this.fluidSettings = fluidSettings;
        this.fluidBlockSupplier = fluidBlockSupplier;
        this.bucketItemSuppler = bucketItemSuppler;
        this.flowingSuppler = flowingSuppler;
        this.stillSuppler = stillSuppler;
    }

    public FluidSettings getFluidSettings() {
        return fluidSettings;
    }

    @Override
    public ImplementedFluid getFlowing() {
        return flowingSuppler.get();
    }

    @Override
    public ImplementedFluid getStill() {
        return stillSuppler.get();
    }

    @Override
    protected boolean isInfinite() {
        return false;
    }

    @Override
    public boolean isStill(FluidState fluidState) {
        return still;
    }

    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {

    }

    @Override
    protected int getFlowSpeed(WorldView world) {
        return 4;
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 1;
    }

    @Override
    public Item getBucketItem() {
        return bucketItemSuppler.get();
    }

    @Override
    protected boolean canBeReplacedWith(FluidState fluidState, BlockView blockView, BlockPos blockPos, Fluid fluid, Direction direction) {
        return false;
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return getFlowing() == fluid || getStill() == fluid;
    }

    @Override
    public int getTickRate(WorldView world) {
        return 10;
    }

    @Override
    protected float getBlastResistance() {
        return 100F;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState) {
        return fluidBlockSupplier.get().getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(fluidState));
    }

    @Override
    public int getLevel(FluidState fluidState) {
        return still ? 8 : fluidState.get(LEVEL);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Fluid, FluidState> stateBuilder) {
        super.appendProperties(stateBuilder);
        if (!still) {
            stateBuilder.add(LEVEL);
        }
    }

}
