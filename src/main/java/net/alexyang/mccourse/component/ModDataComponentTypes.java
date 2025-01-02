package net.alexyang.mccourse.component;

import net.alexyang.mccourse.MCCourseMod;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.component.CustomData;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
  public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
      DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, MCCourseMod.MOD_ID);

  public static final DataComponentType<CustomData> COORDINATES = DataComponents.CUSTOM_DATA;

  public static void register(IEventBus eventBus) {
    DATA_COMPONENT_TYPES.register(eventBus);
  }

  private static <T> RegistryObject<DataComponentType<T>> register(
      String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
    return DATA_COMPONENT_TYPES.register(
        name, () -> builderOperator.apply((DataComponentType.builder())).build());
  }
}
