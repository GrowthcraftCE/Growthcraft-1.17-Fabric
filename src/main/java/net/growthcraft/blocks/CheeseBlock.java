package net.growthcraft.blocks;

import net.growthcraft.entity.CheeseBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtElement;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.DyeColor;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class CheeseBlock extends BlockWithEntity {
    public DyeColor wax;
    public static final EnumProperty<CheeseState> CHEESE_STATE_BOTTOM = EnumProperty.of("cheese_state_bottom",CheeseState.class);
    public static final EnumProperty<CheeseState> CHEESE_STATE_TOP = EnumProperty.of("cheese_state_top",CheeseState.class);
    protected static final VoxelShape BOTTOM_SHAPE;
    protected static final VoxelShape TOP_SHAPE;
    
    public CheeseBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(CHEESE_STATE_TOP,CheeseState.NONE).with(CHEESE_STATE_BOTTOM,CheeseState.NONE));
    }

    public CheeseBlock waxedBy(DyeColor color){
        this.wax = color;
        return this;
    }
    
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
    
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CheeseBlockEntity(pos, state);
    }
    
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState = ctx.getWorld().getBlockState(blockPos);
        if (blockState.isOf(this)) {
            return (BlockState)((BlockState)blockState.with(CHEESE_STATE_TOP, getPlacementCheeseState(ctx)).with(CHEESE_STATE_BOTTOM, getPlacementCheeseState(ctx)));
        } else {
            BlockState blockState2 = (BlockState)((BlockState)this.getDefaultState().with(CHEESE_STATE_BOTTOM, getPlacementCheeseState(ctx)));
            Direction direction = ctx.getSide();
            return direction != Direction.DOWN && (direction == Direction.UP || !(ctx.getHitPos().y - (double)blockPos.getY() > 0.5D)) ? blockState2 : (BlockState)blockState2.with(CHEESE_STATE_TOP, getPlacementCheeseState(ctx));
        }
    }
    
    public CheeseState getPlacementCheeseState(ItemPlacementContext ctx){
        if (ctx.getStack().hasNbt() && ctx.getStack().getNbt().contains("cheese_state")){
            int cheeseState = ctx.getStack().getNbt().getInt("cheese_state");
            return cheeseState == 0 ?
                    CheeseState.UNAGED : cheeseState == 1 ?
                    CheeseState.WAXED : cheeseState == 2 ?
                    CheeseState.AGED : CheeseState.NONE;
        } else {
            return CheeseState.UNAGED;
        }
    }
    
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        CheeseState slabTypeTop = (CheeseState)state.get(CHEESE_STATE_TOP);
        CheeseState slabTypeBottom = (CheeseState)state.get(CHEESE_STATE_BOTTOM);
        if (slabTypeTop == CheeseState.NONE){
            return BOTTOM_SHAPE;
        } else if (slabTypeBottom == CheeseState.NONE){
            return TOP_SHAPE;
        } else {
            return VoxelShapes.fullCube();
        }
    }

    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        ItemStack itemStack = context.getStack();
        CheeseState slabTypeTop = (CheeseState)state.get(CHEESE_STATE_TOP);
        CheeseState slabTypeBottom = (CheeseState)state.get(CHEESE_STATE_TOP);
        if ((slabTypeTop != CheeseState.NONE || slabTypeBottom != CheeseState.NONE) && itemStack.isOf(this.asItem())) {
            if (context.canReplaceExisting()) {
                boolean bl = context.getHitPos().y - (double)context.getBlockPos().getY() > 0.5D;
                Direction direction = context.getSide();
                if (slabTypeTop == CheeseState.NONE) {
                    return direction == Direction.UP || bl && direction.getAxis().isHorizontal();
                } else {
                    return direction == Direction.DOWN || !bl && direction.getAxis().isHorizontal();
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CHEESE_STATE_BOTTOM);
        builder.add(CHEESE_STATE_TOP);
        super.appendProperties(builder);
    }
    
    public enum CheeseState implements StringIdentifiable {
        NONE,
        UNAGED,
        WAXED,
        AGED,
        SLICED_QH,
        SLICED_H,
        SLICED_Q;
    
        @Override
        public String asString() {
            return this.name().toLowerCase(Locale.ROOT);
        }
        
        public static CheeseState fromStack(ItemStack stack){
            if (stack.hasNbt() && stack.getNbt().contains("cheese_state")) {
                return stack.getNbt().getInt("cheese_state") == 0 ? UNAGED : stack.getNbt().getInt("cheese_state") == 1 ? WAXED : AGED;
            }
            return UNAGED;
        }
    
        public static int fromStackRaw(ItemStack stack){
            if (stack.hasNbt() && stack.getNbt().contains("cheese_state")) {
                return stack.getNbt().getInt("cheese_state");
            }
            return 0;
        }
    }
    
    static {
        BOTTOM_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
        TOP_SHAPE = Block.createCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    }
}
