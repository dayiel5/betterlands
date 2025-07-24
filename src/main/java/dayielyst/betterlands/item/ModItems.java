package dayielyst.betterlands.item;

import dayielyst.betterlands.Betterlands;
import dayielyst.betterlands.block.ModBlocks;
import dayielyst.betterlands.block.custom.TerracottaBlockSet;
import dayielyst.betterlands.util.ModFoodProperties;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Betterlands.MOD_ID);

    public static final TerracottaBlockSet.BlockItems
            STRETCHED_BRICKS = ModBlocks.STRETCHED_BRICKS.registerBlockItems(ITEMS),
            PAVEMENTS = ModBlocks.PAVEMENTS.registerBlockItems(ITEMS),
            TERRACOTTA_TILES = ModBlocks.TERRACOTTA_TILES.registerBlockItems(ITEMS);
    public static final DeferredItem<Item>
            JUNIPER_BERRIES = ITEMS.register(
                "juniper_berries",
                () -> new Item(
                    new Item.Properties()
                    .food(ModFoodProperties.JUNIPER_BERRIES)
                    .useItemDescriptionPrefix()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse("betterlands:juniper_berries"))))),
            GIN = ITEMS.register(
                "gin",
                () -> new Item(
                    new Item.Properties()
                    .food(ModFoodProperties.GIN, Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.DRINK).sound(SoundEvents.GENERIC_DRINK).hasConsumeParticles(false).build())
                    .usingConvertsTo(Items.GLASS_BOTTLE)
                    .stacksTo(16)
                    .useItemDescriptionPrefix()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse("betterlands:gin"))))),
            WILTING_BUSH = ITEMS.register(
                "wilting_bush",
                rl -> new BlockItem(ModBlocks.WILTING_BUSH.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, rl)))),
            BRITTLEBUSH = ITEMS.register(
                "brittlebush",
                rl -> new BlockItem(ModBlocks.BRITTLEBUSH.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, rl)))),
            JUNIPER_LOG = ITEMS.register(
                "juniper_log",
                rl -> new BlockItem(ModBlocks.JUNIPER_LOG.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, rl)))),
            JUNIPER_WOOD = ITEMS.register(
                "juniper_wood",
                rl -> new BlockItem(ModBlocks.JUNIPER_WOOD.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, rl)))),
            STRIPPED_JUNIPER_LOG = ITEMS.register(
                "stripped_juniper_log",
                rl -> new BlockItem(ModBlocks.STRIPPED_JUNIPER_LOG.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, rl)))),
            STRIPPED_JUNIPER_WOOD = ITEMS.register(
                "stripped_juniper_wood",
                rl -> new BlockItem(ModBlocks.STRIPPED_JUNIPER_WOOD.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, rl)))),
            JUNIPER_PLANKS = ITEMS.register(
                "juniper_planks",
                rl -> new BlockItem(ModBlocks.JUNIPER_PLANKS.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, rl)))),
            JUNIPER_LEAVES = ITEMS.register(
                "juniper_leaves",
                rl -> new BlockItem(ModBlocks.JUNIPER_LEAVES.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, rl))));

    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}