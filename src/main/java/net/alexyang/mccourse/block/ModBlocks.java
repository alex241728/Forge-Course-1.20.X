package net.alexyang.mccourse.block;

import net.alexyang.mccourse.MCCourseMod;
import net.alexyang.mccourse.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MCCourseMod.MOD_ID);

    public static final RegistryObject<Block> ALEXANDRITE_BLOCK = registerBlock(
            "alexandrite_block",
            () -> new Block(BlockBehaviour.Properties.
                    of().
                    mapColor(MapColor.WARPED_WART_BLOCK).
                    instrument(NoteBlockInstrument.BASEDRUM).
                    requiresCorrectToolForDrops().
                    strength(5.0F, 6.0F)));

    public static final RegistryObject<Block> RAW_ALEXANDRITE_BLOCK = registerBlock(
            "raw_alexandrite_block",
            () -> new Block(BlockBehaviour.Properties.
                    of().
                    mapColor(MapColor.WARPED_STEM).
                    instrument(NoteBlockInstrument.BASEDRUM).
                    requiresCorrectToolForDrops().
                    strength(5.0F, 6.0F))
    );

    public static final RegistryObject<Block> ALEXANDRITE_ORE = registerBlock(
            "alexandrite_ore",
            () -> new Block(BlockBehaviour.Properties.
                    of().
                    mapColor(MapColor.STONE).
                    instrument(NoteBlockInstrument.BASEDRUM).
                    requiresCorrectToolForDrops().
                    strength(3.0F, 3.0F))
    );

    public static final RegistryObject<Block> DEEPSLATE_ALEXANDRITE_ORE = registerBlock(
            "deepslate_alexandrite_ore",
            () -> new Block(BlockBehaviour.Properties.
                    ofFullCopy(ALEXANDRITE_ORE.get()).
                    mapColor(MapColor.DEEPSLATE).
                    strength(4.5F, 3.0F).
                    sound(SoundType.DEEPSLATE))
    );

    public static final RegistryObject<Block> END_STONE_ALEXANDRITE_ORE = registerBlock(
            "end_stone_alexandrite_ore",
            () -> new Block(BlockBehaviour.Properties.
                    ofFullCopy(ALEXANDRITE_ORE.get()).
                    mapColor(MapColor.SAND).
                    strength(3.0F, 9.0F))
    );

    public static final RegistryObject<Block> NETHER_ALEXANDRITE_ORE = registerBlock(
            "nether_alexandrite_ore",
            () -> new Block(BlockBehaviour.Properties.
                    ofFullCopy(ALEXANDRITE_ORE.get())
                    .mapColor(MapColor.NETHER)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 3.0F)
                    .sound(SoundType.NETHER_ORE)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
