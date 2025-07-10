package dayielyst.betterlands;

import dayielyst.betterlands.block.ModBlocks;
import dayielyst.betterlands.item.ModCreativeModeTabs;
import dayielyst.betterlands.item.ModItems;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(Betterlands.MOD_ID)
public class Betterlands
{
    public static final String MOD_ID = "betterlands";

    public Betterlands(IEventBus modEventBus, ModContainer container)
    {
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
    }
}