package dayielyst.betterlands.datagen;

import dayielyst.betterlands.Betterlands;
import dayielyst.betterlands.block.ModBlocks;
import dayielyst.betterlands.block.custom.JuniperBranchBlock;
import dayielyst.betterlands.item.ModItems;
import dayielyst.betterlands.util.TerracottaColors;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplate;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder;

public class ModModelProvider extends ModelProvider
{
    public static final ExtendedModelTemplate PLANT_MODEL_TEMPLATE = ExtendedModelTemplateBuilder.builder().parent(ResourceLocation.fromNamespaceAndPath("minecraft", "block/cross")).requiredTextureSlot(TextureSlot.CROSS).renderType("minecraft:cutout").ambientOcclusion(false).build();
    public static final TexturedModel.Provider PLANT_MODEL_TEMPLATE_PROVIDER = TexturedModel.createDefault(
        block -> new TextureMapping()
            .put(TextureSlot.CROSS, TextureMapping.getBlockTexture(block)),
            PLANT_MODEL_TEMPLATE
    );

    public ModModelProvider(PackOutput output) {super(output, Betterlands.MOD_ID);}

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels)
    {
        for(TerracottaColors color : TerracottaColors.values())
        {
            blockModels.createTrivialCube(ModBlocks.STRETCHED_BRICKS.get(color));
            blockModels.createTrivialCube(ModBlocks.PAVEMENTS.get(color));
            blockModels.createTrivialCube(ModBlocks.TERRACOTTA_TILES.get(color));
        }
        blockModels.woodProvider(ModBlocks.JUNIPER_LOG.get()).logWithHorizontal(ModBlocks.JUNIPER_LOG.get()).wood(ModBlocks.JUNIPER_WOOD.get());
        blockModels.woodProvider(ModBlocks.STRIPPED_JUNIPER_LOG.get()).logWithHorizontal(ModBlocks.STRIPPED_JUNIPER_LOG.get()).wood(ModBlocks.STRIPPED_JUNIPER_WOOD.get());
        blockModels.createTintedLeaves(ModBlocks.JUNIPER_LEAVES.get(), TexturedModel.LEAVES, -12012264);
        blockModels.createTrivialCube(ModBlocks.JUNIPER_PLANKS.get());
        createPlantWithDefaultItem(ModBlocks.WILTING_BUSH.get(), blockModels);
        createPlantWithDefaultItem(ModBlocks.BRITTLEBUSH.get(), blockModels);
        createJuniperBranch(blockModels);
        itemModels.generateFlatItem(ModItems.JUNIPER_BERRIES.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.GIN.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.FOSSILIZED_SKULL.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.FOSSILIZED_RIBS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.FOSSILIZED_COXA.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.FOSSILIZED_CLAWS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LARGE_HORN.get(), ModelTemplates.FLAT_ITEM);
    }

    private static void createPlant(Block plant, BlockModelGenerators generator)
    {
        generator.createTrivialBlock(plant, PLANT_MODEL_TEMPLATE_PROVIDER);
    }

    private static void createPlantWithDefaultItem(Block plant, BlockModelGenerators generator)
    {
        createPlant(plant, generator);
        generator.registerSimpleFlatItemModel(plant);
    }

    private static void createJuniperBranch(BlockModelGenerators generator)
    {
        generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(ModBlocks.JUNIPER_BRANCH.get()).with(PropertyDispatch.initial(BlockStateProperties.AGE_4).generate((age) -> BlockModelGenerators.plainVariant(generator.createSuffixedVariant(ModBlocks.JUNIPER_BRANCH.get(), "_stage" + age, PLANT_MODEL_TEMPLATE, TextureMapping::cross)))));
    }
}