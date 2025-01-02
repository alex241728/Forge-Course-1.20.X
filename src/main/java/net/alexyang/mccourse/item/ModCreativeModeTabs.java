package net.alexyang.mccourse.item;

import net.alexyang.mccourse.MCCourseMod;
import net.alexyang.mccourse.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModCreativeModeTabs {
  public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
      DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MCCourseMod.MOD_ID);

  private static final List<RegistryObject<Item>> MOD_ITEMS =
      List.of(
          ModItems.ALEXANDRITE,
          ModItems.RAW_ALEXANDRITE,
          ModItems.ALEXANDRITE_SWORD,
          ModItems.ALEXANDRITE_PICKAXE,
          ModItems.ALEXANDRITE_SHOVEL,
          ModItems.ALEXANDRITE_AXE,
          ModItems.ALEXANDRITE_HOE,
          ModItems.ALEXANDRITE_PAXEL,
          ModItems.ALEXANDRITE_HAMMER,
          ModItems.METAL_DETECTOR,
          ModItems.KOHLRABI,
          ModItems.ALEXANDRITE_HELMET,
          ModItems.ALEXANDRITE_CHESTPLATE,
          ModItems.ALEXANDRITE_LEGGING,
          ModItems.ALEXANDRITE_BOOTS,
          ModItems.ALEXANDRITE_HORSE_ARMOR,
          ModItems.DATA_TABLET);

  private static final List<RegistryObject<Block>> MOD_BLOCKS =
      List.of(
          ModBlocks.ALEXANDRITE_BLOCK,
          ModBlocks.ALEXANDRITE_STAIRS,
          ModBlocks.ALEXANDRITE_SLAB,
          ModBlocks.ALEXANDRITE_PRESSURE_PLATE,
          ModBlocks.ALEXANDRITE_BUTTON,
          ModBlocks.ALEXANDRITE_FENCE,
          ModBlocks.ALEXANDRITE_FENCE_GATE,
          ModBlocks.ALEXANDRITE_WALL,
          ModBlocks.ALEXANDRITE_DOOR,
          ModBlocks.ALEXANDRITE_TRAPDOOR,
          ModBlocks.RAW_ALEXANDRITE_BLOCK,
          ModBlocks.RAW_ALEXANDRITE_STAIRS,
          ModBlocks.RAW_ALEXANDRITE_SLAB,
          ModBlocks.RAW_ALEXANDRITE_FENCE,
          ModBlocks.RAW_ALEXANDRITE_FENCE_GATE,
          ModBlocks.RAW_ALEXANDRITE_WALL,
          ModBlocks.ALEXANDRITE_ORE,
          ModBlocks.DEEPSLATE_ALEXANDRITE_ORE,
          ModBlocks.END_STONE_ALEXANDRITE_ORE,
          ModBlocks.NETHER_ALEXANDRITE_ORE,
          ModBlocks.SOUND_BLOCK,
          ModBlocks.ALEXANDRITE_LAMP);

  public static final RegistryObject<CreativeModeTab> COURSE_TAB =
      CREATIVE_MODE_TABS.register(
          "course_tab",
          () ->
              CreativeModeTab.builder()
                  .icon(() -> new ItemStack(ModItems.ALEXANDRITE.get()))
                  .title(Component.translatable("creativetab.course_tab"))
                  .displayItems(
                      (displayParameters, output) -> {
                        for (RegistryObject<Item> item : MOD_ITEMS) {
                          output.accept(item.get());
                        }

                        for (RegistryObject<Block> block : MOD_BLOCKS) {
                          output.accept(block.get());
                        }
                      })
                  .build());

  public static void register(IEventBus eventBus) {
    CREATIVE_MODE_TABS.register(eventBus);
  }
}
