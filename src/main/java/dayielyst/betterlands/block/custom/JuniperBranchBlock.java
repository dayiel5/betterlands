package dayielyst.betterlands.block.custom;

import com.mojang.serialization.MapCodec;
import dayielyst.betterlands.block.ModBlocks;
import dayielyst.betterlands.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class JuniperBranchBlock extends VegetationBlock implements BonemealableBlock
{
    public static final IntegerProperty AGE = BlockStateProperties.AGE_4;
    public static final MapCodec<JuniperBranchBlock> CODEC = simpleCodec(JuniperBranchBlock::new);

    public MapCodec<JuniperBranchBlock> codec() {return CODEC;}

    public JuniperBranchBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(getStateDefinition().any().setValue(AGE, 0));
    }

    @Override public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean flag)
    {
        return new ItemStack(ModItems.JUNIPER_BERRIES.get());
    }

    @Override public boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {return state.is(ModBlocks.JUNIPER_LEAVES.get());}

    @Override public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
    {
        BlockPos position = pos.above();
        BlockState blockStateAbove = level.getBlockState(position);
        return this.mayPlaceOn(blockStateAbove, level, position);
    }

    @Override public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos pos, BlockState state)
    {
        return state.getValue(AGE) < 4;
    }

    @Override public boolean isBonemealSuccess(Level level, RandomSource source, BlockPos pos, BlockState state)
    {
        return true;
    }

    @Override public void performBonemeal(ServerLevel level, RandomSource source, BlockPos pos, BlockState state)
    {
        int age = state.getValue(AGE);
        level.setBlock(pos, state.setValue(AGE, age + 1), 2);
    }

    @Override protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult)
    {
        int age = state.getValue(AGE);
        if(age == 4)
        {
            int dropAmount = 1 + level.random.nextInt(2);
            popResource(level, pos, new ItemStack(ModItems.JUNIPER_BERRIES.get(), dropAmount));
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            level.removeBlock(pos, false);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state));
            return InteractionResult.SUCCESS;
        } else return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(AGE);}
}
