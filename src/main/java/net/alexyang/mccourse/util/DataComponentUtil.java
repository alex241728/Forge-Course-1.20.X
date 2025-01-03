package net.alexyang.mccourse.util;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

public class DataComponentUtil {
  public static boolean hasTag(ItemStack stack, DataComponentType<CustomData> dataComponentType) {
    CustomData data = stack.getComponents().get(dataComponentType);
    return data != null && !data.copyTag().isEmpty();
  }
}
