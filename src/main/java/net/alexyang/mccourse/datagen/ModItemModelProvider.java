package net.alexyang.mccourse.datagen;

import net.alexyang.mccourse.MCCourseMod;
import net.alexyang.mccourse.block.ModBlocks;
import net.alexyang.mccourse.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Objects;

public class ModItemModelProvider extends ItemModelProvider {
  private static final List<RegistryObject<Item>> simpleItems =
      List.of(
          ModItems.ALEXANDRITE,
          ModItems.RAW_ALEXANDRITE,
          ModItems.KOHLRABI,
          ModItems.METAL_DETECTOR,
          ModItems.PEAT_BRICK);

  private static final List<RegistryObject<Item>> handheldItems =
      List.of(
          ModItems.ALEXANDRITE_SWORD,
          ModItems.ALEXANDRITE_PICKAXE,
          ModItems.ALEXANDRITE_SHOVEL,
          ModItems.ALEXANDRITE_HOE,
          ModItems.ALEXANDRITE_AXE,
          ModItems.ALEXANDRITE_PAXEL,
          ModItems.ALEXANDRITE_HAMMER);

  private static final List<RegistryObject<Block>> simpleBlockItems =
      List.of(ModBlocks.ALEXANDRITE_DOOR);

  public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
    super(output, MCCourseMod.MOD_ID, existingFileHelper);
  }

  @Override
  protected void registerModels() {
    // register all simple items
    for (RegistryObject<Item> item : simpleItems) {
      simpleItem(item);
    }

    // register all handheld items
    for (RegistryObject<Item> item : handheldItems) {
      handheldItem(item);
    }

    // register all button items
    buttonItem(ModBlocks.ALEXANDRITE_BUTTON, ModBlocks.ALEXANDRITE_BLOCK);

    // register all fence items
    fenceItem(ModBlocks.ALEXANDRITE_FENCE, ModBlocks.ALEXANDRITE_BLOCK);
    fenceItem(ModBlocks.RAW_ALEXANDRITE_FENCE, ModBlocks.RAW_ALEXANDRITE_BLOCK);

    // register all wall items
    wallItem(ModBlocks.ALEXANDRITE_WALL, ModBlocks.ALEXANDRITE_BLOCK);
    wallItem(ModBlocks.RAW_ALEXANDRITE_WALL, ModBlocks.RAW_ALEXANDRITE_BLOCK);

    // register all simple block items
    for (RegistryObject<Block> item : simpleBlockItems) {
      simpleBlockItem(item);
    }
  }

  private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
    return withExistingParent(item.getId().getPath(), new ResourceLocation("item/handheld"))
        .texture(
            "layer0", new ResourceLocation(MCCourseMod.MOD_ID, "item/" + item.getId().getPath()));
  }

  private void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
    this.withExistingParent(
            Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath(),
            mcLoc("block/fence_inventory"))
        .texture(
            "texture",
            new ResourceLocation(
                MCCourseMod.MOD_ID,
                "block/"
                    + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(baseBlock.get()))
                        .getPath()));
  }

  private void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
    this.withExistingParent(
            Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath(),
            mcLoc("block/wall_inventory"))
        .texture(
            "wall",
            new ResourceLocation(
                MCCourseMod.MOD_ID,
                "block/"
                    + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(baseBlock.get()))
                        .getPath()));
  }

  private void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
    this.withExistingParent(
            Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath(),
            mcLoc("block/button_inventory"))
        .texture(
            "texture",
            new ResourceLocation(
                MCCourseMod.MOD_ID,
                "block/"
                    + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(baseBlock.get()))
                        .getPath()));
  }

  private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
    return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated"))
        .texture(
            "layer0", new ResourceLocation(MCCourseMod.MOD_ID, "item/" + item.getId().getPath()));
  }

  private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
    return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated"))
        .texture(
            "layer0", new ResourceLocation(MCCourseMod.MOD_ID, "item/" + item.getId().getPath()));
  }
}
