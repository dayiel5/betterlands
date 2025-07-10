package dayielyst.betterlands.block;

import dayielyst.betterlands.Betterlands;
import dayielyst.betterlands.block.custom.ModLeavesBlock;
import dayielyst.betterlands.block.custom.ModLoglikeBlock;
import dayielyst.betterlands.block.custom.TerracottaBlockSet;
import dayielyst.betterlands.block.custom.WiltingBushBlock;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks
{
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Betterlands.MOD_ID);

    public static final TerracottaBlockSet
            STRETCHED_BRICKS = new TerracottaBlockSet("stretched_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.TERRACOTTA), BLOCKS),
            PAVEMENTS = new TerracottaBlockSet("pavement", BlockBehaviour.Properties.ofFullCopy(Blocks.TERRACOTTA).speedFactor(1.15f), BLOCKS);
    public static final DeferredBlock<Block>
            JUNIPER_LOG = BLOCKS.register(
                "juniper_log",
                rl -> new ModLoglikeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LOG).setId(ResourceKey.create(Registries.BLOCK, rl)))),
            JUNIPER_WOOD = BLOCKS.register(
                "juniper_wood",
                rl -> new ModLoglikeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WOOD).setId(ResourceKey.create(Registries.BLOCK, rl)))),
            STRIPPED_JUNIPER_LOG = BLOCKS.register(
                "stripped_juniper_log",
                rl -> new ModLoglikeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_LOG).setId(ResourceKey.create(Registries.BLOCK, rl)))),
            STRIPPED_JUNIPER_WOOD = BLOCKS.register(
                "stripped_juniper_wood",
                rl -> new ModLoglikeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_WOOD).setId(ResourceKey.create(Registries.BLOCK, rl)))),
            JUNIPER_PLANKS = BLOCKS.register(
                "juniper_planks",
                rl -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS).mapColor(MapColor.SAND).setId(ResourceKey.create(Registries.BLOCK, rl)))),
            JUNIPER_LEAVES = BLOCKS.register(
                "juniper_leaves",
                rl -> new ModLeavesBlock(0F, ParticleTypes.CHERRY_LEAVES, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES).mapColor(MapColor.PLANT).randomTicks().sound(SoundType.AZALEA_LEAVES).noOcclusion().ignitedByLava().isValidSpawn(Blocks::ocelotOrParrot).pushReaction(PushReaction.DESTROY).setId(ResourceKey.create(Registries.BLOCK, rl)))),
            WILTING_BUSH = BLOCKS.register(
                "wilting_bush",
                rl -> new WiltingBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEAD_BUSH).mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.CROP).noOcclusion().pushReaction(PushReaction.DESTROY).setId(ResourceKey.create(Registries.BLOCK, rl))));

    public static void register(IEventBus eventBus) {BLOCKS.register(eventBus);}
}