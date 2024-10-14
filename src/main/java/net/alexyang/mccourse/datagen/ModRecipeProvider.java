package net.alexyang.mccourse.datagen;

import net.alexyang.mccourse.MCCourseMod;
import net.alexyang.mccourse.block.ModBlocks;
import net.alexyang.mccourse.item.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> ALEXANDRITE_SMELTABLES = List.of(
            ModItems.RAW_ALEXANDRITE.get(), ModBlocks.ALEXANDRITE_ORE.get(), ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(),
            ModBlocks.NETHER_ALEXANDRITE_ORE.get(), ModBlocks.END_STONE_ALEXANDRITE_ORE.get()
    );

    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, ModItems.ALEXANDRITE.get(),
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.ALEXANDRITE_BLOCK.get(), "alexandrite_block",
                "alexandrite", "alexandrite", "alexandrite"
        );

        slabBuilder(ModBlocks.ALEXANDRITE_SLAB.get(), ModBlocks.ALEXANDRITE_BLOCK.get(), recipeOutput);

        stairBuilder(ModBlocks.ALEXANDRITE_STAIRS.get(), ModBlocks.ALEXANDRITE_BLOCK.get(), recipeOutput);

        nineBlockStorageRecipes(
                recipeOutput, RecipeCategory.MISC, ModItems.RAW_ALEXANDRITE.get(), RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.RAW_ALEXANDRITE_BLOCK.get(), "raw_alexandrite_block", "alexandrite",
                "raw_alexandrite", "alexandrite"
        );

        slabBuilder(ModBlocks.RAW_ALEXANDRITE_SLAB.get(), ModBlocks.RAW_ALEXANDRITE_BLOCK.get(), recipeOutput);

        stairBuilder(ModBlocks.RAW_ALEXANDRITE_STAIRS.get(), ModBlocks.RAW_ALEXANDRITE_BLOCK.get(), recipeOutput);

        pressurePlateBuilder(ModBlocks.ALEXANDRITE_PRESSURE_PLATE.get(), ModBlocks.ALEXANDRITE_BLOCK.get(),
                recipeOutput
        );

        buttonBuilder(ModBlocks.ALEXANDRITE_BUTTON.get(), ModBlocks.ALEXANDRITE_BLOCK.get(), recipeOutput);

        nonWoodenFenceBuilder(ModBlocks.ALEXANDRITE_FENCE.get(), ModBlocks.ALEXANDRITE_BLOCK.get(),
                ModItems.ALEXANDRITE.get(), recipeOutput
        );

        nonWoodenFenceBuilder(ModBlocks.RAW_ALEXANDRITE_FENCE.get(), ModBlocks.RAW_ALEXANDRITE_BLOCK.get(),
                ModItems.RAW_ALEXANDRITE.get(), recipeOutput
        );

        nonWoodenFenceGateBuilder(ModBlocks.ALEXANDRITE_FENCE_GATE.get(), ModBlocks.ALEXANDRITE_BLOCK.get(),
                ModItems.ALEXANDRITE.get(), recipeOutput
        );

        nonWoodenFenceGateBuilder(ModBlocks.RAW_ALEXANDRITE_FENCE_GATE.get(), ModBlocks.RAW_ALEXANDRITE_BLOCK.get(),
                ModItems.RAW_ALEXANDRITE.get(), recipeOutput
        );

        wallBuilder(ModBlocks.ALEXANDRITE_WALL.get(), ModBlocks.ALEXANDRITE_BLOCK.get(), recipeOutput);

        wallBuilder(ModBlocks.RAW_ALEXANDRITE_WALL.get(), ModBlocks.RAW_ALEXANDRITE_BLOCK.get(), recipeOutput);

        oreSmelting(
                recipeOutput, ALEXANDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.ALEXANDRITE.get(),
                0.25f, 200, "alexandrite"
        );

        oreBlasting(
                recipeOutput, ALEXANDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.ALEXANDRITE.get(),
                0.25f, 100, "alexandrite"
        );
    }

    private static void wallBuilder(ItemLike pWall, ItemLike pMaterial, @NotNull RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, pWall, 6)
                .define('#', pMaterial)
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_" + getItemName(pMaterial),
                        InventoryChangeTrigger.TriggerInstance.hasItems(pMaterial)
                ).save(recipeOutput,
                        MCCourseMod.MOD_ID + ":" + getItemName(pWall) + "_from_" + getItemName(pMaterial)
                );
    }

    private static void nonWoodenFenceGateBuilder(ItemLike pFenceGate, ItemLike pMaterial, ItemLike pItem,
                                                  @NotNull RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, pFenceGate, 2)
                .define('#', pItem)
                .define('W', pMaterial)
                .pattern("#W#")
                .pattern("#W#")
                .unlockedBy("has_" + getItemName(pMaterial) + "_and_" + getItemName(pItem),
                        InventoryChangeTrigger.TriggerInstance.hasItems(pMaterial, pItem)
                ).save(recipeOutput,
                        MCCourseMod.MOD_ID + ":" + getItemName(pFenceGate) + "_from_" + getItemName(pMaterial)
                                + "_and_" + getItemName(pItem)
                );
    }

    private static void nonWoodenFenceBuilder(ItemLike pFence, ItemLike pMaterial, ItemLike pItem,
                                              @NotNull RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, pFence, 6)
                .define('W', pMaterial)
                .define('#', pItem)
                .pattern("W#W")
                .pattern("W#W")
                .unlockedBy("has_" + getItemName(pMaterial) + "_and_" + getItemName(pItem),
                        InventoryChangeTrigger.TriggerInstance.hasItems(pMaterial, pItem)
                ).save(recipeOutput,
                        MCCourseMod.MOD_ID + ":" + getItemName(pFence) + "_from_" + getItemName(pMaterial)
                                + "_and_" + getItemName(pItem)
                );
    }

    private static void buttonBuilder(ItemLike pButton, ItemLike pItem, @NotNull RecipeOutput recipeOutput) {
        RecipeProvider.buttonBuilder(pButton, Ingredient.of(pItem)).
                unlockedBy(getHasName(pItem), InventoryChangeTrigger.TriggerInstance.hasItems(pItem)).
                save(recipeOutput,
                        MCCourseMod.MOD_ID + ":" + getItemName(pButton) + "_from_" + getItemName(pItem)
                );
    }

    private static void pressurePlateBuilder(ItemLike pPressurePlate, ItemLike pItem,
                                             @NotNull RecipeOutput recipeOutput) {
        RecipeProvider.pressurePlateBuilder(RecipeCategory.REDSTONE, pPressurePlate, Ingredient.of(pItem)).
                unlockedBy(getHasName(pItem), InventoryChangeTrigger.TriggerInstance.hasItems(pItem)).
                save(recipeOutput,
                        MCCourseMod.MOD_ID + ":" + getItemName(pPressurePlate) + "_from_" + getItemName(pItem)
                );
    }

    private static void stairBuilder(ItemLike pStairs, ItemLike pItem, @NotNull RecipeOutput recipeOutput) {
        RecipeProvider.stairBuilder(pStairs, Ingredient.of(pItem)).unlockedBy(getHasName(pItem),
                        InventoryChangeTrigger.TriggerInstance.hasItems(pItem)).save(recipeOutput,
                        MCCourseMod.MOD_ID + ":" + getItemName(pStairs) + "_from_" + getItemName(pItem)
                );
    }

    private static void slabBuilder(ItemLike pSlab, ItemLike pItem,
                                    @NotNull RecipeOutput recipeOutput) {
        RecipeProvider.slabBuilder(RecipeCategory.BUILDING_BLOCKS, pSlab, Ingredient.of(pItem)).unlockedBy(getHasName(pItem),
                InventoryChangeTrigger.TriggerInstance.hasItems(pItem)
        ).save(recipeOutput, MCCourseMod.MOD_ID + ":" + getItemName(pSlab) + "_from_" + getItemName(pItem));
    }

    private static void nineBlockStorageRecipes(RecipeOutput pRecipeOutput, RecipeCategory pUnpackedCategory,
                                                ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked,
                                                String pPackedName, @Nullable String pPackedGroup, String pUnpackedName,
                                                @Nullable String pUnpackedGroup) {
        ShapelessRecipeBuilder.shapeless(pUnpackedCategory, pUnpacked, 9)
                .requires(pPacked)
                .group(pUnpackedGroup)
                .unlockedBy(getHasName(pPacked), has(pPacked))
                .save(pRecipeOutput, MCCourseMod.MOD_ID + ":" + pUnpackedName + "_from_" + pPackedName);
        ShapedRecipeBuilder.shaped(pPackedCategory, pPacked)
                .define('#', pUnpacked)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .group(pPackedGroup)
                .unlockedBy(getHasName(pUnpacked), has(pUnpacked))
                .save(pRecipeOutput, new ResourceLocation(MCCourseMod.MOD_ID + ":" + pPackedName + "_from_" +
                        pUnpackedName));
    }

    protected static void oreSmelting(@NotNull RecipeOutput pRecipeOutput, List<ItemLike> pIngredients,
                                      @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult, float pExperience,
                                      int pCookingTime, @NotNull String pGroup) {
        oreCooking(
                pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory,
                pResult, pExperience, pCookingTime, pGroup, "_from_smelting"
        );
    }

    protected static void oreBlasting(@NotNull RecipeOutput pRecipeOutput, List<ItemLike> pIngredients,
                                      @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult, float pExperience,
                                      int pCookingTime, @NotNull String pGroup) {
        oreCooking(
                pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory,
                pResult, pExperience, pCookingTime, pGroup, "_from_blasting"
        );
    }

    private static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pRecipeOutput,
                                                                     RecipeSerializer<T> pSerializer,
                                                                     AbstractCookingRecipe.Factory<T> pRecipeFactory,
                                                                     List<ItemLike> pIngredients,
                                                                     RecipeCategory pCategory, ItemLike pResult,
                                                                     float pExperience, int pCookingTime,
                                                                     String pGroup, String pSuffix) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(
                            Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pSerializer,
                            pRecipeFactory
                    ).group(pGroup).unlockedBy(
                            getHasName(itemlike), has(itemlike)
                    ).save(
                            pRecipeOutput,
                        MCCourseMod.MOD_ID + ":" + getItemName(pResult) + pSuffix + "_" + getItemName(itemlike)
                    );
        }
    }
}
