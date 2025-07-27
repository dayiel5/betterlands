package dayielyst.betterlands.item;

import dayielyst.betterlands.Betterlands;
import dayielyst.betterlands.block.custom.TerracottaBlockSet;
import dayielyst.betterlands.util.TerracottaColors;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Betterlands.MOD_ID);

    public static final Supplier<CreativeModeTab>
            BETTERLANDS_ITEMS_TAB = CREATIVE_MODE_TAB.register(
                "items_tab",
                () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.JUNIPER_BERRIES.get()))
                    .title(Component.translatable("creativetab.betterlands.items"))
                    .displayItems((p, o) -> {
                        o.accept(ModItems.JUNIPER_BERRIES);
                        o.accept(ModItems.GIN);
                        o.accept(ModItems.FOSSILIZED_SKULL);
                        o.accept(ModItems.FOSSILIZED_RIBS);
                        o.accept(ModItems.FOSSILIZED_COXA);
                        o.accept(ModItems.FOSSILIZED_CLAWS);
                        o.accept(ModItems.LARGE_HORN);
                    }).build()),
            BETTERLANDS_BLOCKS_TAB = CREATIVE_MODE_TAB.register(
                "blocks_tab",
                () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.STRETCHED_BRICKS.get(TerracottaColors.PLAIN)))
                    .title(Component.translatable("creativetab.betterlands.blocks"))
                    .displayItems((p, o) -> {
                        o.accept(ModItems.WILTING_BUSH);
                        o.accept(ModItems.BRITTLEBUSH);
                        acceptBlockItems(ModItems.STRETCHED_BRICKS, o);
                        acceptBlockItems(ModItems.PAVEMENTS, o);
                        acceptBlockItems(ModItems.TERRACOTTA_TILES, o);
//                        for(DeferredItem<Item> item : ModItems.STRETCHED_BRICKS.getEntries())
//                        {
//                            o.accept(item);
//                        }
//                        for(DeferredItem<Item> item : ModItems.PAVEMENTS.getEntries())
//                        {
//                            o.accept(item);
//                        }
                        o.accept(ModItems.JUNIPER_LOG);
                        o.accept(ModItems.JUNIPER_WOOD);
                        o.accept(ModItems.STRIPPED_JUNIPER_LOG);
                        o.accept(ModItems.STRIPPED_JUNIPER_WOOD);
                        o.accept(ModItems.JUNIPER_PLANKS);
                        o.accept(ModItems.JUNIPER_LEAVES);
                    }).build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TAB.register(eventBus);
    }

    public static void acceptBlockItems(TerracottaBlockSet.BlockItems items, CreativeModeTab.Output output)
    {
        for(DeferredItem<Item> item : items.getEntries()) output.accept(item);
    }
}