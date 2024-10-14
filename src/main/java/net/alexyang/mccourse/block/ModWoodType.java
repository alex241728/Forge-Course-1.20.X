package net.alexyang.mccourse.block;

import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodType {
    public static final WoodType ALEXANDRITE = WoodType.register(new WoodType("alexandrite",
            ModBlockSetType.ALEXANDRITE)
    );

    public static final WoodType RAW_ALEXANDRITE = WoodType.register(new WoodType("raw_alexandrite",
            ModBlockSetType.RAW_ALEXANDRITE)
    );
}
