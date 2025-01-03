package net.alexyang.mccourse.datagen;

import net.alexyang.mccourse.MCCourseMod;
import net.alexyang.mccourse.block.ModBlocks;
import net.alexyang.mccourse.block.custom.KohlrabiCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
  private static final List<RegistryObject<Block>> BLOCKS_WITH_ITEM =
      List.of(
          ModBlocks.ALEXANDRITE_BLOCK,
          ModBlocks.RAW_ALEXANDRITE_BLOCK,
          ModBlocks.ALEXANDRITE_ORE,
          ModBlocks.DEEPSLATE_ALEXANDRITE_ORE,
          ModBlocks.END_STONE_ALEXANDRITE_ORE,
          ModBlocks.NETHER_ALEXANDRITE_ORE,
          ModBlocks.SOUND_BLOCK);

  private static final Map<RegistryObject<Block>, RegistryObject<Block>> STAIRS_TO_TEXTURES =
      Map.ofEntries(
          Map.entry(ModBlocks.ALEXANDRITE_STAIRS, ModBlocks.ALEXANDRITE_BLOCK),
          Map.entry(ModBlocks.RAW_ALEXANDRITE_STAIRS, ModBlocks.RAW_ALEXANDRITE_BLOCK));

  private static final Map<RegistryObject<Block>, RegistryObject<Block>> SLABS_TO_TEXTURES =
      Map.ofEntries(
          Map.entry(ModBlocks.ALEXANDRITE_SLAB, ModBlocks.ALEXANDRITE_BLOCK),
          Map.entry(ModBlocks.RAW_ALEXANDRITE_SLAB, ModBlocks.RAW_ALEXANDRITE_BLOCK));

  private static final Map<RegistryObject<Block>, RegistryObject<Block>> BUTTONS_TO_TEXTURES =
      Map.ofEntries(Map.entry(ModBlocks.ALEXANDRITE_BUTTON, ModBlocks.ALEXANDRITE_BLOCK));

  private static final Map<RegistryObject<Block>, RegistryObject<Block>>
      PRESSURE_PLATES_TO_TEXTURES =
          Map.ofEntries(
              Map.entry(ModBlocks.ALEXANDRITE_PRESSURE_PLATE, ModBlocks.ALEXANDRITE_BLOCK));

  private static final Map<RegistryObject<Block>, RegistryObject<Block>> FENCES_TO_TEXTURES =
      Map.ofEntries(
          Map.entry(ModBlocks.ALEXANDRITE_FENCE, ModBlocks.ALEXANDRITE_BLOCK),
          Map.entry(ModBlocks.RAW_ALEXANDRITE_FENCE, ModBlocks.RAW_ALEXANDRITE_BLOCK));

  private static final Map<RegistryObject<Block>, RegistryObject<Block>> FENCE_GATES_TO_TEXTURES =
      Map.ofEntries(
          Map.entry(ModBlocks.ALEXANDRITE_FENCE_GATE, ModBlocks.ALEXANDRITE_BLOCK),
          Map.entry(ModBlocks.RAW_ALEXANDRITE_FENCE_GATE, ModBlocks.RAW_ALEXANDRITE_BLOCK));

  private static final Map<RegistryObject<Block>, RegistryObject<Block>> WALL_TO_TEXTURES =
      Map.ofEntries(
          Map.entry(ModBlocks.ALEXANDRITE_WALL, ModBlocks.ALEXANDRITE_BLOCK),
          Map.entry(ModBlocks.RAW_ALEXANDRITE_WALL, ModBlocks.RAW_ALEXANDRITE_BLOCK));

  private static final List<RegistryObject<Block>> LAMPS = List.of(ModBlocks.ALEXANDRITE_LAMP);

  public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
    super(output, MCCourseMod.MOD_ID, exFileHelper);
  }

  @Override
  protected void registerStatesAndModels() {
    BLOCKS_WITH_ITEM.forEach(block -> blockWithItem(block));

    STAIRS_TO_TEXTURES.forEach(
        (stairsBlock, textureBlock) -> {
          stairsBlock((StairBlock) stairsBlock.get(), blockTexture(textureBlock.get()));
          blockItem(stairsBlock);
        });

    SLABS_TO_TEXTURES.forEach(
        (slabBlock, textureBlock) -> {
          slabBlock(
              (SlabBlock) slabBlock.get(),
              blockTexture(textureBlock.get()),
              blockTexture(textureBlock.get()));
          blockItem(slabBlock);
        });

    BUTTONS_TO_TEXTURES.forEach(
        (buttonBlock, textureBlock) ->
            buttonBlock((ButtonBlock) buttonBlock.get(), blockTexture(textureBlock.get())));

    PRESSURE_PLATES_TO_TEXTURES.forEach(
        (pressurePlateBlock, textureBlock) -> {
          pressurePlateBlock(
              (PressurePlateBlock) pressurePlateBlock.get(), blockTexture(textureBlock.get()));
          blockItem(pressurePlateBlock);
        });

    FENCES_TO_TEXTURES.forEach(
        (fenceBlock, textureBlock) ->
            fenceBlock((FenceBlock) fenceBlock.get(), blockTexture(textureBlock.get())));

    FENCE_GATES_TO_TEXTURES.forEach(
        (fenceGateBlock, textureBlock) -> {
          fenceGateBlock((FenceGateBlock) fenceGateBlock.get(), blockTexture(textureBlock.get()));
          blockItem(fenceGateBlock);
        });

    WALL_TO_TEXTURES.forEach(
        (wallBlock, textureBlock) ->
            wallBlock((WallBlock) wallBlock.get(), blockTexture(textureBlock.get())));

    LAMPS.forEach((lamp) -> lampBlock(lamp));

    customCrop((CropBlock) ModBlocks.KOHLRABI_CROP.get(), "kohlrabi_stage", "kohlrabi_stage");

    doorBlockWithRenderType(
        (DoorBlock) ModBlocks.ALEXANDRITE_DOOR.get(),
        modLoc("block/alexandrite_door_bottom"),
        modLoc("block/alexandrite_door_top"),
        "cutout");

    trapdoorBlockWithRenderType(
        (TrapDoorBlock) ModBlocks.ALEXANDRITE_TRAPDOOR.get(),
        modLoc("block/alexandrite_trapdoor"),
        true,
        "cutout");
    blockItem(ModBlocks.ALEXANDRITE_TRAPDOOR, "_bottom");
  }

  private void customCrop(CropBlock block, String modelName, String textureName) {
    Function<BlockState, ConfiguredModel[]> function =
        state -> states(state, block, modelName, textureName);
    getVariantBuilder(block).forAllStates(function);
  }

  private ConfiguredModel[] states(
      BlockState state, CropBlock block, String modelName, String textureName) {
    ConfiguredModel[] models = new ConfiguredModel[1];
    models[0] =
        new ConfiguredModel(
            models()
                .crop(
                    modelName + state.getValue(((KohlrabiCropBlock) block).getAgeProperty()),
                    new ResourceLocation(
                        MCCourseMod.MOD_ID,
                        "block/"
                            + textureName
                            + state.getValue(((KohlrabiCropBlock) block).getAgeProperty())))
                .renderType("cutout"));
    return models;
  }

  private void lampBlock(RegistryObject<Block> lampRegistryObject) {
    String lampName = lampRegistryObject.getId().getPath();

    getVariantBuilder(lampRegistryObject.get())
        .forAllStates(
            state -> {
              boolean isLit = state.getValue(BlockStateProperties.LIT);
              String suffix = isLit ? "_on" : "";
              return new ConfiguredModel[] {
                new ConfiguredModel(
                    models()
                        .cubeAll(
                            lampName + suffix,
                            new ResourceLocation(MCCourseMod.MOD_ID, "block/" + lampName + suffix)))
              };
            });
    simpleBlockItem(
        lampRegistryObject.get(),
        models().cubeAll(lampName, new ResourceLocation(MCCourseMod.MOD_ID, "block/" + lampName)));
  }

  private void blockItem(RegistryObject<Block> blockRegistryObject, String appendix) {
    simpleBlockItem(
        blockRegistryObject.get(),
        new ModelFile.UncheckedModelFile(
            "mccourse:block/"
                + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()))
                    .getPath()
                + appendix));
  }

  private void blockItem(RegistryObject<Block> blockRegistryObject) {
    simpleBlockItem(
        blockRegistryObject.get(),
        new ModelFile.UncheckedModelFile(
            "mccourse:block/"
                + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()))
                    .getPath()));
  }

  private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
    simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
  }
}
