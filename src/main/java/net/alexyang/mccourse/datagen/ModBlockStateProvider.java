package net.alexyang.mccourse.datagen;

import net.alexyang.mccourse.MCCourseMod;
import net.alexyang.mccourse.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

public class ModBlockStateProvider extends BlockStateProvider {
  public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
    super(output, MCCourseMod.MOD_ID, exFileHelper);
  }

  @Override
  protected void registerStatesAndModels() {
    blockWithItem(ModBlocks.ALEXANDRITE_BLOCK);
    blockWithItem(ModBlocks.RAW_ALEXANDRITE_BLOCK);
    blockWithItem(ModBlocks.ALEXANDRITE_ORE);
    blockWithItem(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE);
    blockWithItem(ModBlocks.END_STONE_ALEXANDRITE_ORE);
    blockWithItem(ModBlocks.NETHER_ALEXANDRITE_ORE);
    blockWithItem(ModBlocks.SOUND_BLOCK);

    stairsBlock(
        (StairBlock) ModBlocks.ALEXANDRITE_STAIRS.get(),
        blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));
    stairsBlock(
        (StairBlock) ModBlocks.RAW_ALEXANDRITE_STAIRS.get(),
        blockTexture(ModBlocks.RAW_ALEXANDRITE_BLOCK.get()));

    slabBlock(
        (SlabBlock) ModBlocks.ALEXANDRITE_SLAB.get(),
        blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()),
        blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));
    slabBlock(
        (SlabBlock) ModBlocks.RAW_ALEXANDRITE_SLAB.get(),
        blockTexture(ModBlocks.RAW_ALEXANDRITE_BLOCK.get()),
        blockTexture(ModBlocks.RAW_ALEXANDRITE_BLOCK.get()));

    buttonBlock(
        (ButtonBlock) ModBlocks.ALEXANDRITE_BUTTON.get(),
        blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));

    pressurePlateBlock(
        (PressurePlateBlock) ModBlocks.ALEXANDRITE_PRESSURE_PLATE.get(),
        blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));

    fenceBlock(
        (FenceBlock) ModBlocks.ALEXANDRITE_FENCE.get(),
        blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));
    fenceBlock(
        (FenceBlock) ModBlocks.RAW_ALEXANDRITE_FENCE.get(),
        blockTexture(ModBlocks.RAW_ALEXANDRITE_BLOCK.get()));

    fenceGateBlock(
        (FenceGateBlock) ModBlocks.ALEXANDRITE_FENCE_GATE.get(),
        blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));
    fenceGateBlock(
        (FenceGateBlock) ModBlocks.RAW_ALEXANDRITE_FENCE_GATE.get(),
        blockTexture(ModBlocks.RAW_ALEXANDRITE_BLOCK.get()));

    wallBlock(
        (WallBlock) ModBlocks.ALEXANDRITE_WALL.get(),
        blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));
    wallBlock(
        (WallBlock) ModBlocks.RAW_ALEXANDRITE_WALL.get(),
        blockTexture(ModBlocks.RAW_ALEXANDRITE_BLOCK.get()));

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

    lampBlock(ModBlocks.ALEXANDRITE_LAMP, "alexandrite_lamp");

    blockItem(ModBlocks.ALEXANDRITE_STAIRS);
    blockItem(ModBlocks.ALEXANDRITE_SLAB);
    blockItem(ModBlocks.ALEXANDRITE_PRESSURE_PLATE);
    blockItem(ModBlocks.ALEXANDRITE_FENCE_GATE);
    blockItem(ModBlocks.ALEXANDRITE_TRAPDOOR, "_bottom");
    blockItem(ModBlocks.RAW_ALEXANDRITE_STAIRS);
    blockItem(ModBlocks.RAW_ALEXANDRITE_SLAB);
    blockItem(ModBlocks.RAW_ALEXANDRITE_FENCE_GATE);
  }

  private void lampBlock(RegistryObject<Block> lampRegistryObject, String lampName) {
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
