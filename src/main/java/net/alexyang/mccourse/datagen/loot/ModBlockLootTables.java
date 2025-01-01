package net.alexyang.mccourse.datagen.loot;

import net.alexyang.mccourse.block.ModBlocks;
import net.alexyang.mccourse.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
  private static final List<RegistryObject<Block>> SELF_DROPPING_BLOCKS =
      List.of(
          ModBlocks.ALEXANDRITE_BLOCK,
          ModBlocks.ALEXANDRITE_STAIRS,
          ModBlocks.RAW_ALEXANDRITE_BLOCK,
          ModBlocks.RAW_ALEXANDRITE_STAIRS,
          ModBlocks.SOUND_BLOCK,
          ModBlocks.ALEXANDRITE_PRESSURE_PLATE,
          ModBlocks.ALEXANDRITE_BUTTON,
          ModBlocks.ALEXANDRITE_FENCE,
          ModBlocks.ALEXANDRITE_FENCE_GATE,
          ModBlocks.ALEXANDRITE_WALL,
          ModBlocks.RAW_ALEXANDRITE_FENCE,
          ModBlocks.RAW_ALEXANDRITE_FENCE_GATE,
          ModBlocks.RAW_ALEXANDRITE_WALL,
          ModBlocks.ALEXANDRITE_TRAPDOOR,
          ModBlocks.ALEXANDRITE_LAMP);

  private static final Map<RegistryObject<Block>, RegistryObject<Item>> ORES =
      Map.ofEntries(
          Map.entry(ModBlocks.ALEXANDRITE_ORE, ModItems.RAW_ALEXANDRITE),
          Map.entry(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE, ModItems.RAW_ALEXANDRITE),
          Map.entry(ModBlocks.NETHER_ALEXANDRITE_ORE, ModItems.RAW_ALEXANDRITE),
          Map.entry(ModBlocks.END_STONE_ALEXANDRITE_ORE, ModItems.RAW_ALEXANDRITE));

  private static final List<RegistryObject<Block>> SLABS =
      List.of(ModBlocks.ALEXANDRITE_SLAB, ModBlocks.RAW_ALEXANDRITE_SLAB);

  private static final List<RegistryObject<Block>> DOORS = List.of(ModBlocks.ALEXANDRITE_DOOR);

  public ModBlockLootTables() {
    super(Set.of(), FeatureFlags.REGISTRY.allFlags());
  }

  @Override
  protected void generate() {
    SELF_DROPPING_BLOCKS.forEach(block -> this.dropSelf(block.get()));

    ORES.forEach((block, item) -> this.add(block.get(), createOreDrop(block.get(), item.get())));

    SLABS.forEach(block -> this.add(block.get(), createSlabItemTable(block.get())));

    DOORS.forEach(block -> this.add(block.get(), createDoorTable(block.get())));
  }

  @Override
  protected @NotNull Iterable<Block> getKnownBlocks() {
    return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
  }
}
