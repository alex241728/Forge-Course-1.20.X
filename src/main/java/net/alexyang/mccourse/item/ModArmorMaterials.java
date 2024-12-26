package net.alexyang.mccourse.item;

import net.alexyang.mccourse.MCCourseMod;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials extends ArmorMaterials {
  public static final Holder<ArmorMaterial> ALEXANDRITE =
      register(
          "alexandrite",
          Util.make(
              new EnumMap<>(ArmorItem.Type.class),
              p_327103_ -> {
                p_327103_.put(ArmorItem.Type.BOOTS, 4);
                p_327103_.put(ArmorItem.Type.LEGGINGS, 5);
                p_327103_.put(ArmorItem.Type.CHESTPLATE, 7);
                p_327103_.put(ArmorItem.Type.HELMET, 5);
                p_327103_.put(ArmorItem.Type.BODY, 11);
              }),
          15,
          SoundEvents.ARMOR_EQUIP_IRON,
          3.0F,
          0.1F,
          () -> Ingredient.of(ModItems.ALEXANDRITE.get()));

  private static Holder<ArmorMaterial> register(
      String pName,
      EnumMap<ArmorItem.Type, Integer> pDefense,
      int pEnchantmentValue,
      Holder<SoundEvent> pEquipSound,
      float pToughness,
      float pKnockbackResistance,
      Supplier<Ingredient> pRepairIngredient) {
    ResourceLocation location = ResourceLocation.fromNamespaceAndPath(MCCourseMod.MOD_ID, pName);
    List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));

    EnumMap<ArmorItem.Type, Integer> enummap = new EnumMap<>(ArmorItem.Type.class);
    for (ArmorItem.Type armoritem$type : ArmorItem.Type.values()) {
      enummap.put(armoritem$type, pDefense.get(armoritem$type));
    }

    return Registry.registerForHolder(
        BuiltInRegistries.ARMOR_MATERIAL,
        location,
        new ArmorMaterial(
            enummap,
            pEnchantmentValue,
            pEquipSound,
            pRepairIngredient,
            layers,
            pToughness,
            pKnockbackResistance));
  }
}
