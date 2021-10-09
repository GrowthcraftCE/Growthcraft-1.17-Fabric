package net.growthcraft.blocks;

import net.growthcraft.entity.CheeseBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
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
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class CheeseBlock extends SlabBlockWithEntity {
    public DyeColor wax;
    public static final EnumProperty<CheeseState> CHEESE_STATE = EnumProperty.of("cheese_state",CheeseState.class);
    public CheeseBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(CHEESE_STATE,CheeseState.UNAGED));
    }

    public CheeseBlock waxedBy(DyeColor color){
        this.wax = color;
        return this;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CheeseBlockEntity(pos, state);
    }
    
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        if (ctx.getStack().hasNbt() && ctx.getStack().getNbt().contains("cheese_state")){
            int cheeseState = ctx.getStack().getNbt().getInt("cheese_state");
            return cheeseState == 0 ?
                    super.getPlacementState(ctx).with(CHEESE_STATE,CheeseState.UNAGED) : cheeseState == 1 ?
                    super.getPlacementState(ctx).with(CHEESE_STATE,CheeseState.WAXED) : super.getPlacementState(ctx).with(CHEESE_STATE,CheeseState.AGED);
        } else {
            super.getPlacementState(ctx).with(CHEESE_STATE,CheeseState.UNAGED);
        }
        return super.getPlacementState(ctx);
    }
    
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CHEESE_STATE);
        super.appendProperties(builder);
    }
    
    public enum CheeseState implements StringIdentifiable {
        UNAGED,
        WAXED,
        AGED;
    
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
}
