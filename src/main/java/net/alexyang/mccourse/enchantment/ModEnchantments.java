package net.alexyang.mccourse.enchantment;

import net.alexyang.mccourse.MCCourseMod;
import net.alexyang.mccourse.enchantment.custom.LightningStrikerEnchantment;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
  public static final DeferredRegister<Enchantment> ENCHANTMENTS =
      DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MCCourseMod.MOD_ID);

  public static final RegistryObject<Enchantment> LIGHTNING_STRIKER =
      ENCHANTMENTS.register(
          "lightning_striker",
          () ->
              new LightningStrikerEnchantment(
                  Enchantment.definition(
                      ItemTags.WEAPON_ENCHANTABLE,
                      ItemTags.SWORD_ENCHANTABLE,
                      5,
                      2,
                      Enchantment.dynamicCost(5, 8),
                      Enchantment.dynamicCost(25, 8),
                      2,
                      EquipmentSlot.MAINHAND)));

  public static void register(IEventBus eventBus) {
    ENCHANTMENTS.register(eventBus);
  }
}
