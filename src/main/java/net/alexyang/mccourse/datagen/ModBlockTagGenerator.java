package net.alexyang.mccourse.datagen;

import net.alexyang.mccourse.MCCourseMod;
import net.alexyang.mccourse.block.ModBlocks;
import net.alexyang.mccourse.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
  public ModBlockTagGenerator(
      PackOutput output,
      CompletableFuture<HolderLookup.Provider> lookupProvider,
      @Nullable ExistingFileHelper existingFileHelper) {
    super(output, lookupProvider, MCCourseMod.MOD_ID, existingFileHelper);
  }

  @Override
  protected void addTags(HolderLookup.@NotNull Provider provider) {
    this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES)
        .add(ModBlocks.ALEXANDRITE_ORE.get())
        .addTag(Tags.Blocks.ORES);

    this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .add(
            ModBlocks.ALEXANDRITE_BLOCK.get(),
            ModBlocks.ALEXANDRITE_STAIRS.get(),
            ModBlocks.ALEXANDRITE_SLAB.get(),
            ModBlocks.ALEXANDRITE_PRESSURE_PLATE.get(),
            ModBlocks.ALEXANDRITE_FENCE.get(),
            ModBlocks.ALEXANDRITE_FENCE_GATE.get(),
            ModBlocks.ALEXANDRITE_WALL.get(),
            ModBlocks.ALEXANDRITE_DOOR.get(),
            ModBlocks.ALEXANDRITE_TRAPDOOR.get(),
            ModBlocks.RAW_ALEXANDRITE_BLOCK.get(),
            ModBlocks.RAW_ALEXANDRITE_STAIRS.get(),
            ModBlocks.RAW_ALEXANDRITE_SLAB.get(),
            ModBlocks.RAW_ALEXANDRITE_FENCE.get(),
            ModBlocks.RAW_ALEXANDRITE_FENCE_GATE.get(),
            ModBlocks.RAW_ALEXANDRITE_WALL.get(),
            ModBlocks.ALEXANDRITE_ORE.get(),
            ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(),
            ModBlocks.END_STONE_ALEXANDRITE_ORE.get(),
            ModBlocks.NETHER_ALEXANDRITE_ORE.get(),
            ModBlocks.SOUND_BLOCK.get());

    this.tag(BlockTags.NEEDS_STONE_TOOL)
        .add(
            ModBlocks.NETHER_ALEXANDRITE_ORE.get(),
            ModBlocks.ALEXANDRITE_DOOR.get(),
            ModBlocks.ALEXANDRITE_TRAPDOOR.get());

    this.tag(BlockTags.NEEDS_IRON_TOOL)
        .add(
            ModBlocks.ALEXANDRITE_BLOCK.get(),
            ModBlocks.ALEXANDRITE_STAIRS.get(),
            ModBlocks.ALEXANDRITE_SLAB.get(),
            ModBlocks.RAW_ALEXANDRITE_BLOCK.get(),
            ModBlocks.RAW_ALEXANDRITE_STAIRS.get(),
            ModBlocks.RAW_ALEXANDRITE_SLAB.get(),
            ModBlocks.ALEXANDRITE_ORE.get(),
            ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(),
            ModBlocks.END_STONE_ALEXANDRITE_ORE.get(),
            ModBlocks.SOUND_BLOCK.get());

    this.tag(ModTags.Blocks.NEEDS_ALEXANDRITE_TOOL)
        .add(Blocks.OBSIDIAN)
        .addTag(BlockTags.NEEDS_IRON_TOOL);
    this.tag(ModTags.Blocks.INCORRECT_FOR_ALEXANDRITE_TOOL)
        .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL);

    this.tag(ModTags.Blocks.PAXEL_MINEABLE)
        .addTags(
            BlockTags.MINEABLE_WITH_PICKAXE,
            BlockTags.MINEABLE_WITH_AXE,
            BlockTags.MINEABLE_WITH_SHOVEL);

    this.tag(BlockTags.PRESSURE_PLATES).add(ModBlocks.ALEXANDRITE_PRESSURE_PLATE.get());

    this.tag(BlockTags.BUTTONS).add(ModBlocks.ALEXANDRITE_BUTTON.get());

    this.tag(BlockTags.FENCES)
        .add(ModBlocks.ALEXANDRITE_FENCE.get(), ModBlocks.RAW_ALEXANDRITE_FENCE.get());

    this.tag(BlockTags.FENCE_GATES)
        .add(ModBlocks.ALEXANDRITE_FENCE_GATE.get(), ModBlocks.RAW_ALEXANDRITE_FENCE_GATE.get());

    this.tag(BlockTags.WALLS)
        .add(ModBlocks.ALEXANDRITE_WALL.get(), ModBlocks.RAW_ALEXANDRITE_WALL.get());
  }
}
