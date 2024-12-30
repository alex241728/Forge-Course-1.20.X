package net.alexyang.mccourse.datagen;

import net.alexyang.mccourse.MCCourseMod;
import net.alexyang.mccourse.block.ModBlocks;
import net.alexyang.mccourse.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public class ModItemModelProvider extends ItemModelProvider {
  private static final LinkedHashMap<ResourceKey<TrimMaterial>, Float> TRIM_MATERIALS =
      new LinkedHashMap<>();

  static {
    TRIM_MATERIALS.put(TrimMaterials.QUARTZ, 0.1F);
    TRIM_MATERIALS.put(TrimMaterials.IRON, 0.2F);
    TRIM_MATERIALS.put(TrimMaterials.NETHERITE, 0.3F);
    TRIM_MATERIALS.put(TrimMaterials.REDSTONE, 0.4F);
    TRIM_MATERIALS.put(TrimMaterials.COPPER, 0.5F);
    TRIM_MATERIALS.put(TrimMaterials.GOLD, 0.6F);
    TRIM_MATERIALS.put(TrimMaterials.EMERALD, 0.7F);
    TRIM_MATERIALS.put(TrimMaterials.DIAMOND, 0.8F);
    TRIM_MATERIALS.put(TrimMaterials.LAPIS, 0.9F);
    TRIM_MATERIALS.put(TrimMaterials.AMETHYST, 1.0F);
  }

  private static final List<RegistryObject<Item>> SIMPLE_ITEMS =
      List.of(
          ModItems.ALEXANDRITE,
          ModItems.RAW_ALEXANDRITE,
          ModItems.KOHLRABI,
          ModItems.METAL_DETECTOR,
          ModItems.PEAT_BRICK);

  private static final List<RegistryObject<Item>> HANDHELD_ITEMS =
      List.of(
          ModItems.ALEXANDRITE_SWORD,
          ModItems.ALEXANDRITE_PICKAXE,
          ModItems.ALEXANDRITE_SHOVEL,
          ModItems.ALEXANDRITE_HOE,
          ModItems.ALEXANDRITE_AXE,
          ModItems.ALEXANDRITE_PAXEL,
          ModItems.ALEXANDRITE_HAMMER);

  private static final List<RegistryObject<Block>> SIMPLE_BLOCK_ITEMS =
      List.of(ModBlocks.ALEXANDRITE_DOOR);

  private static final List<RegistryObject<Item>> TRIMMED_ARMOR_ITEMS =
      List.of(
          ModItems.ALEXANDRITE_HELMET,
          ModItems.ALEXANDRITE_CHESTPLATE,
          ModItems.ALEXANDRITE_LEGGING,
          ModItems.ALEXANDRITE_BOOTS);

  public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
    super(output, MCCourseMod.MOD_ID, existingFileHelper);
  }

  @Override
  protected void registerModels() {
    // register all simple items
    for (RegistryObject<Item> item : SIMPLE_ITEMS) {
      simpleItem(item);
    }

    // register all handheld items
    for (RegistryObject<Item> item : HANDHELD_ITEMS) {
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
    for (RegistryObject<Block> item : SIMPLE_BLOCK_ITEMS) {
      simpleBlockItem(item);
    }

    // register all trimmed armor items
    for (RegistryObject<Item> item : TRIMMED_ARMOR_ITEMS) {
      trimmedArmorItem(item);
      //      simpleItem(item);
    }
  }

  private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
    final String MOD_ID = MCCourseMod.MOD_ID;

    if (itemRegistryObject.get() instanceof ArmorItem armorItem) {
      TRIM_MATERIALS.forEach(
          (trimMaterial, value) -> {
            float trimValue = value;

            String armorType =
                switch (armorItem.getEquipmentSlot()) {
                  case HEAD -> "helmet";
                  case CHEST -> "chestplate";
                  case LEGS -> "leggings";
                  case FEET -> "boots";
                  default -> "";
                };

            String armorItemPath = armorItem.toString();
            ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);

            String trimPath =
                "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
            ResourceLocation trimResLoc = ResourceLocation.parse(trimPath);

            String currentTrimName =
                armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
            ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

            // This is used for making the ExistingFileHelper acknowledge that this texture exist,
            // so this will
            // avoid an IllegalArgumentException
            existingFileHelper.trackGenerated(
                trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

            // Trimmed armorItem files
            getBuilder(currentTrimName)
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture(
                    "layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                .texture("layer1", trimResLoc);

            // Non-trimmed armorItem file (normal variant)
            this.withExistingParent(itemRegistryObject.getId().getPath(), mcLoc("item/generated"))
                .override()
                .model(
                    new ModelFile.UncheckedModelFile(
                        trimNameResLoc.getNamespace() + ":item/" + trimNameResLoc.getPath()))
                .predicate(mcLoc("trim_type"), trimValue)
                .end()
                .texture(
                    "layer0",
                    ResourceLocation.fromNamespaceAndPath(
                        MOD_ID, "item/" + itemRegistryObject.getId().getPath()));
          });
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
