package dayielyst.betterlands.block.custom;

import dayielyst.betterlands.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.neoforged.neoforge.common.ItemAbility;

import javax.annotation.Nullable;

public class JuniperLoglikeBlock extends RotatedPillarBlock
{
    public static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

    public JuniperLoglikeBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(getStateDefinition().any().setValue(FACING, Direction.UP));
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate)
    {
        if(context.getItemInHand().getItem() instanceof AxeItem)
        {
            if(state.is(ModBlocks.JUNIPER_LOG)) return ModBlocks.STRIPPED_JUNIPER_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            if(state.is(ModBlocks.JUNIPER_WOOD)) return ModBlocks.STRIPPED_JUNIPER_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
        }
        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(FACING, AXIS);}
}
