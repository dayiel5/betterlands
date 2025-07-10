// A helper class for simplifying the creation of a set of terracotta blocks that comes in different colors.

package dayielyst.betterlands.block.custom;

import dayielyst.betterlands.util.TerracottaColors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;

public class TerracottaBlockSet {
    // An ArrayList that keeps the associated registered blocks of a specific block set for future reference
    private ArrayList<DeferredBlock<Block>> deferredBlocks;

    // To create a complete set of terracotta blocks (e.g. terracotta tiles), use this constructor instead of creating many individual DeferredBlock<Block>s.
    public TerracottaBlockSet(String blockType, BlockBehaviour.Properties properties, DeferredRegister.Blocks blockRegistry)
    {
        deferredBlocks = new ArrayList<>();

        // Associated blocks are registered in bulk upon class instantiation.
        for(TerracottaColors color : TerracottaColors.values())
        {
            String registryName = color.getColorValue() == null?
                (blockType):(color.getColorValue() + "_" + blockType);
            DeferredBlock<Block> deferredBlock = blockRegistry.register(
                registryName,
                resourceLocation -> new Block(properties.setId(ResourceKey.create(Registries.BLOCK, resourceLocation)))
            );
            deferredBlocks.add(deferredBlock); // Adds the block for future reference
        }
    }
    // Call this method for a TerracottaBlockSet object registered in ModBlocks.
    public BlockItems registerBlockItems(DeferredRegister.Items registry) {return new BlockItems(registry);}
    public Block get(TerracottaColors color) {return deferredBlocks.get(color.getIndex()).get();}
    public ArrayList<DeferredBlock<Block>> getEntries() {return deferredBlocks;}



    public class BlockItems
    {
        private ArrayList<DeferredItem<Item>> deferredItems;

        public BlockItems(DeferredRegister.Items itemRegistry)
        {
            deferredItems = new ArrayList<>();

            for(DeferredBlock<Block> block : deferredBlocks)
            {
                DeferredItem<Item> blockItem = itemRegistry.register(
                    block.getId().getPath(),
                    resourceLocation -> new BlockItem(block.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, resourceLocation)))
                );
                deferredItems.add(blockItem); // Adds the block item for future reference
            }
        }
        public Item get(TerracottaColors color) {return deferredItems.get(color.getIndex()).get();}
        public ArrayList<DeferredItem<Item>> getEntries() {return deferredItems;}
    }
}