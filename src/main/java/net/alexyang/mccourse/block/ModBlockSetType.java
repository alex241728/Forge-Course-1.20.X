package net.alexyang.mccourse.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModBlockSetType {
    public static final BlockSetType ALEXANDRITE = BlockSetType.register(
            new BlockSetType("alexandrite", false, false,
                    false, BlockSetType.PressurePlateSensitivity.EVERYTHING, SoundType.METAL,
                    SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE,
                    SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
                    SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF,
                    SoundEvents.STONE_BUTTON_CLICK_ON
            )
    );

    public static final BlockSetType RAW_ALEXANDRITE = BlockSetType.register(
            new BlockSetType("raw_alexandrite", false, false,
                    false, BlockSetType.PressurePlateSensitivity.EVERYTHING, SoundType.METAL,
                    SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE,
                    SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
                    SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF,
                    SoundEvents.STONE_BUTTON_CLICK_ON
            )
    );
}
