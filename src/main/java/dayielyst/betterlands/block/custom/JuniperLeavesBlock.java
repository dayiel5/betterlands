package dayielyst.betterlands.block.custom;

import dayielyst.betterlands.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.UntintedParticleLeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class JuniperLeavesBlock extends UntintedParticleLeavesBlock implements BonemealableBlock {
    public JuniperLeavesBlock(float leafParticleChance, ParticleOptions options, BlockBehaviour.Properties properties)
    {
        super(leafParticleChance, options, properties);
    }

    @Override public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}
    @Override public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 60;}
    @Override public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 30;}
    @Override public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState)
    {
        return (!this.decaying(blockState) && levelReader.getBlockState(blockPos.below()).canBeReplaced() && !levelReader.getBlockState(blockPos.below()).is(ModBlocks.JUNIPER_BRANCH));
    }
    @Override public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {return true;}
    @Override public void performBonemeal(ServerLevel level, RandomSource randomSource, BlockPos blockPos, BlockState blockState)
    {
        boolean willGrowABranch = level.random.nextInt(3) == 0;
        if(willGrowABranch) level.setBlock(blockPos.below(), ModBlocks.JUNIPER_BRANCH.get().defaultBlockState(), 2);
    }
}