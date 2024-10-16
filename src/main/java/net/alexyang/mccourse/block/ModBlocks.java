package net.alexyang.mccourse.block;

import net.alexyang.mccourse.MCCourseMod;
import net.alexyang.mccourse.block.custom.SoundBlock;
import net.alexyang.mccourse.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
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
                    strength(5.0F, 6.0F)
            )
    );

    public static final RegistryObject<Block> ALEXANDRITE_STAIRS = registerBlock("alexandrite_stairs",
            () -> new StairBlock(
                    ModBlocks.ALEXANDRITE_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(ModBlocks.ALEXANDRITE_BLOCK.get())
            )
    );

    public static final RegistryObject<Block> ALEXANDRITE_SLAB = registerBlock("alexandrite_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.ALEXANDRITE_BLOCK.get()))
    );

    public static final RegistryObject<Block> ALEXANDRITE_PRESSURE_PLATE = registerBlock(
            "alexandrite_pressure_plate", () -> new PressurePlateBlock(ModBlockSetType.ALEXANDRITE,
                    BlockBehaviour.Properties.of().mapColor(MapColor.WARPED_WART_BLOCK).forceSolidOn().
                            requiresCorrectToolForDrops().noCollission().strength(0.5F).
                            pushReaction(PushReaction.DESTROY).sound(SoundType.METAL)
            )
    );

    public static final RegistryObject<Block> ALEXANDRITE_BUTTON = registerBlock("alexandrite_button",
            () -> new ButtonBlock(ModBlockSetType.ALEXANDRITE, 10,
                    BlockBehaviour.Properties.of().mapColor(MapColor.WARPED_WART_BLOCK).forceSolidOn().noCollission()
                            .strength(0.5F).pushReaction(PushReaction.DESTROY).sound(SoundType.METAL)
            )
    );

    public static final RegistryObject<Block> ALEXANDRITE_FENCE = registerBlock("alexandrite_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WARPED_WART_BLOCK)
                    .forceSolidOn()
                    .requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)
            )
    );

    public static final RegistryObject<Block> ALEXANDRITE_FENCE_GATE = registerBlock("alexandrite_fence_gate",
            () -> new FenceGateBlock(ModWoodType.ALEXANDRITE, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WARPED_WART_BLOCK)
                    .forceSolidOn()
                    .requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)
            )
    );

    public static final RegistryObject<Block> ALEXANDRITE_WALL = registerBlock("alexandrite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.ALEXANDRITE_BLOCK.get()).forceSolidOn()
                    .requiresCorrectToolForDrops()
            )
    );

    public static final RegistryObject<Block> ALEXANDRITE_DOOR = registerBlock("alexandrite_door",
            () -> new DoorBlock(ModBlockSetType.ALEXANDRITE,
                    BlockBehaviour.Properties.of().mapColor(MapColor.WARPED_WART_BLOCK).requiresCorrectToolForDrops()
                            .strength(5.0F).noOcclusion().pushReaction(PushReaction.DESTROY)
            )
    );

    public static final RegistryObject<Block> ALEXANDRITE_TRAPDOOR = registerBlock("alexandrite_trapdoor",
            () -> new TrapDoorBlock(ModBlockSetType.ALEXANDRITE,
                    BlockBehaviour.Properties.of().mapColor(MapColor.WARPED_WART_BLOCK).requiresCorrectToolForDrops()
                            .strength(5.0F).noOcclusion()
                            .isValidSpawn((blockState, blockGetter, blockPos, entityType) -> false)
            )
    );

    public static final RegistryObject<Block> RAW_ALEXANDRITE_BLOCK = registerBlock(
            "raw_alexandrite_block",
            () -> new Block(BlockBehaviour.Properties.
                    of().
                    mapColor(MapColor.WARPED_STEM).
                    instrument(NoteBlockInstrument.BASEDRUM).
                    requiresCorrectToolForDrops().
                    strength(5.0F, 6.0F)
            )
    );

    public static final RegistryObject<Block> RAW_ALEXANDRITE_STAIRS = registerBlock("raw_alexandrite_stairs",
            () -> new StairBlock(
                    ModBlocks.RAW_ALEXANDRITE_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(ModBlocks.RAW_ALEXANDRITE_BLOCK.get())
            )
    );

    public static final RegistryObject<Block> RAW_ALEXANDRITE_SLAB = registerBlock("raw_alexandrite_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.RAW_ALEXANDRITE_BLOCK.get()))
    );

    public static final RegistryObject<Block> RAW_ALEXANDRITE_FENCE = registerBlock("raw_alexandrite_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WARPED_STEM)
                    .forceSolidOn()
                    .requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)
            )
    );

    public static final RegistryObject<Block> RAW_ALEXANDRITE_FENCE_GATE = registerBlock(
            "raw_alexandrite_fence_gate", () -> new FenceGateBlock(ModWoodType.RAW_ALEXANDRITE,
                    BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WARPED_STEM)
                    .forceSolidOn()
                    .requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)
            )
    );

    public static final RegistryObject<Block> RAW_ALEXANDRITE_WALL = registerBlock("raw_alexandrite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.RAW_ALEXANDRITE_BLOCK.get()).forceSolidOn()
                    .requiresCorrectToolForDrops()
            )
    );

    public static final RegistryObject<Block> ALEXANDRITE_ORE = registerBlock(
            "alexandrite_ore",
            () -> new Block(BlockBehaviour.Properties.
                    of().
                    mapColor(MapColor.STONE).
                    instrument(NoteBlockInstrument.BASEDRUM).
                    requiresCorrectToolForDrops().
                    strength(3.0F, 3.0F)
            )
    );

    public static final RegistryObject<Block> DEEPSLATE_ALEXANDRITE_ORE = registerBlock(
            "deepslate_alexandrite_ore",
            () -> new Block(BlockBehaviour.Properties.
                    ofFullCopy(ALEXANDRITE_ORE.get()).
                    mapColor(MapColor.DEEPSLATE).
                    strength(4.5F, 3.0F
                    ).
                    sound(SoundType.DEEPSLATE))
    );

    public static final RegistryObject<Block> END_STONE_ALEXANDRITE_ORE = registerBlock(
            "end_stone_alexandrite_ore",
            () -> new Block(BlockBehaviour.Properties.
                    ofFullCopy(ALEXANDRITE_ORE.get()).
                    mapColor(MapColor.SAND).
                    strength(3.0F, 9.0F)
            )
    );

    public static final RegistryObject<Block> NETHER_ALEXANDRITE_ORE = registerBlock(
            "nether_alexandrite_ore",
            () -> new Block(BlockBehaviour.Properties.
                    ofFullCopy(ALEXANDRITE_ORE.get())
                    .mapColor(MapColor.NETHER)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 3.0F)
                    .sound(SoundType.NETHER_ORE)
            )
    );

    public static final RegistryObject<Block> SOUND_BLOCK = registerBlock("sound_block",
            () -> new SoundBlock(BlockBehaviour.Properties.
                    of().
                    mapColor(MapColor.METAL).
                    instrument(NoteBlockInstrument.BASEDRUM).
                    requiresCorrectToolForDrops().
                    strength(5.0F, 6.0F)
            )
    );

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
