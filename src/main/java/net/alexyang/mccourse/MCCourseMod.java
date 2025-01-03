package net.alexyang.mccourse;

import com.mojang.logging.LogUtils;
import net.alexyang.mccourse.block.ModBlocks;
import net.alexyang.mccourse.component.ModDataComponentTypes;
import net.alexyang.mccourse.enchantment.ModEnchantments;
import net.alexyang.mccourse.item.ModCreativeModeTabs;
import net.alexyang.mccourse.item.ModItemProperties;
import net.alexyang.mccourse.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MCCourseMod.MOD_ID)
public class MCCourseMod {
  // Define mod id in a common place for everything to reference
  public static final String MOD_ID = "mccourse";
  // Directly reference a slf4j logger
  private static final Logger LOGGER = LogUtils.getLogger();

  private static final List<RegistryObject<Item>> MOD_INGREDIENTS =
      List.of(ModItems.ALEXANDRITE, ModItems.RAW_ALEXANDRITE);

  private static final List<RegistryObject<Block>> MOD_BUILDING_BLOCKS =
      List.of(
          ModBlocks.ALEXANDRITE_BLOCK,
          ModBlocks.ALEXANDRITE_SLAB,
          ModBlocks.ALEXANDRITE_STAIRS,
          ModBlocks.ALEXANDRITE_FENCE,
          ModBlocks.ALEXANDRITE_FENCE_GATE,
          ModBlocks.ALEXANDRITE_WALL,
          ModBlocks.ALEXANDRITE_DOOR,
          ModBlocks.ALEXANDRITE_TRAPDOOR,
          ModBlocks.RAW_ALEXANDRITE_BLOCK,
          ModBlocks.RAW_ALEXANDRITE_SLAB,
          ModBlocks.RAW_ALEXANDRITE_STAIRS,
          ModBlocks.RAW_ALEXANDRITE_FENCE,
          ModBlocks.RAW_ALEXANDRITE_FENCE_GATE,
          ModBlocks.RAW_ALEXANDRITE_WALL,
          ModBlocks.ALEXANDRITE_ORE,
          ModBlocks.DEEPSLATE_ALEXANDRITE_ORE,
          ModBlocks.END_STONE_ALEXANDRITE_ORE,
          ModBlocks.NETHER_ALEXANDRITE_ORE);

  private static final List<RegistryObject<Block>> MOD_REDSTONE_BLOCKS =
      List.of(ModBlocks.ALEXANDRITE_PRESSURE_PLATE, ModBlocks.ALEXANDRITE_BUTTON);

  private static final List<RegistryObject<Item>> MOD_TOOLS_AND_UTILITIES =
      List.of(ModItems.METAL_DETECTOR);

  private static final List<RegistryObject<Block>> MOD_FUNCTIONAL_BLOCKS =
      List.of(ModBlocks.SOUND_BLOCK, ModBlocks.ALEXANDRITE_LAMP);

  private static final List<RegistryObject<Item>> MOD_FOOD_AND_DRINKS = List.of(ModItems.KOHLRABI);

  public MCCourseMod(FMLJavaModLoadingContext context) {
    IEventBus modEventBus = context.getModEventBus();

    ModItems.register(modEventBus);
    ModBlocks.register(modEventBus);
    ModCreativeModeTabs.register(modEventBus);
    ModEnchantments.register(modEventBus);
    ModDataComponentTypes.register(modEventBus);

    // Register the commonSetup method for modloading
    modEventBus.addListener(this::commonSetup);

    // Register ourselves for server and other game events we are interested in
    MinecraftForge.EVENT_BUS.register(this);

    // Register the item to a creative tab
    modEventBus.addListener(this::addCreative);
  }

  private void commonSetup(final FMLCommonSetupEvent event) {
    ComposterBlock.COMPOSTABLES.put(ModItems.KOHLRABI.get(), 0.35f);
    ComposterBlock.COMPOSTABLES.put(ModItems.KOHLRABI_SEEDS.get(), 0.20f);
  }

  // Add the example block item to the building blocks tab
  private void addCreative(BuildCreativeModeTabContentsEvent event) {
    if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
      for (RegistryObject<Item> ingredient : MOD_INGREDIENTS) {
        event.accept(ingredient);
      }
    }

    if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
      for (RegistryObject<Block> block : MOD_BUILDING_BLOCKS) {
        event.accept(block);
      }
    }

    if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
      for (RegistryObject<Block> block : MOD_REDSTONE_BLOCKS) {
        event.accept(block);
      }
    }

    if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
      for (RegistryObject<Item> item : MOD_TOOLS_AND_UTILITIES) {
        event.accept(item);
      }
    }

    if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
      for (RegistryObject<Block> block : MOD_FUNCTIONAL_BLOCKS) {
        event.accept(block);
      }
    }

    if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
      for (RegistryObject<Item> item : MOD_FOOD_AND_DRINKS) {
        event.accept(item);
      }
    }
  }

  // You can use SubscribeEvent and let the Event Bus discover methods to call
  @SubscribeEvent
  public void onServerStarting(ServerStartingEvent event) {}

  // You can use EventBusSubscriber to automatically register all static methods in the class
  // annotated with @SubscribeEvent
  @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
  public static class ClientModEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
      ModItemProperties.addCustomItemProperties();
    }
  }
}
