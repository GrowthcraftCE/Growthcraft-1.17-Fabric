package net.growthcraft.machines.core;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.growthcraft.machines.roaster.RoasterMachine;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MachineBlock extends BlockWithEntity {
    private final Machine machine;

    public MachineBlock(Machine machine) {
        super(createBlockSettings());
        this.machine = machine;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (machine.getVoxelShape() != null) return machine.getVoxelShape();
        return super.getCollisionShape(state, world, pos, context);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (machine.getVoxelShape() != null) return machine.getVoxelShape();
        return super.getOutlineShape(state, world, pos, context);
    }

    private static FabricBlockSettings createBlockSettings(){
        FabricBlockSettings settings = FabricBlockSettings.copyOf(Blocks.IRON_BLOCK);
        return settings.requiresTool();
    }

    @Nullable
    @Override
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return super.createScreenHandlerFactory(state, world, pos);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return machine.createBlockEntity(pos,state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type,machine.getType(), machine::tick);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (machine.hasContainer){
            if (!world.isClient) {
                //This will call the createScreenHandlerFactory method from BlockWithEntity, which will return our blockEntity casted to
                //a namedScreenHandlerFactory. If your block class does not extend BlockWithEntity, it needs to implement createScreenHandlerFactory.
                NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

                if (screenHandlerFactory != null) {
                    //With this call the server will request the client to open the appropriate Screenhandler
                    player.openHandledScreen(screenHandlerFactory);
                }
            }
            return ActionResult.SUCCESS;
        } return super.onUse(state, world, pos, player, hand, hit);
    }
}
