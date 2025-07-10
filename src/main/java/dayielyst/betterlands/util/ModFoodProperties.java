package dayielyst.betterlands.util;

import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties
{
    public static final FoodProperties
            JUNIPER_BERRIES = new FoodProperties.Builder().nutrition(2).saturationModifier(0.15f).build(),
            GIN = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).alwaysEdible().build();
}