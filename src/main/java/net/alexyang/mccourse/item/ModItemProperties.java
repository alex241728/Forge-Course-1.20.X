package net.alexyang.mccourse.item;

import net.alexyang.mccourse.MCCourseMod;
import net.alexyang.mccourse.component.ModDataComponentTypes;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

public class ModItemProperties {
  public static void addCustomItemProperties() {
    ItemProperties.register(
        ModItems.DATA_TABLET.get(),
        new ResourceLocation(MCCourseMod.MOD_ID, "on"),
        ((pStack, pLevel, pEntity, pSeed) ->
            pStack.has(ModDataComponentTypes.COORDINATES) ? 1f : 0f));
  }
}
