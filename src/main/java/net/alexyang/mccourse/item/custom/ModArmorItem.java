package net.alexyang.mccourse.item.custom;

import com.google.common.collect.ImmutableMap;
import net.alexyang.mccourse.item.ModArmorMaterials;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Map;

public class ModArmorItem extends ArmorItem {
  private static final Map<Holder<ArmorMaterial>, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
      (new ImmutableMap.Builder<Holder<ArmorMaterial>, MobEffectInstance>())
          .put(ModArmorMaterials.ALEXANDRITE, new MobEffectInstance(MobEffects.JUMP, 200, 1))
          .build();

  public ModArmorItem(Holder<ArmorMaterial> pMaterial, Type pType, Properties pProperties) {
    super(pMaterial, pType, pProperties);
  }

  @Override
  public void onInventoryTick(
      ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
    if (!level.isClientSide() && hasFullSuitOfArmorOn(player)) {
      evaluateArmorEffects(player);
    }
  }

  private void evaluateArmorEffects(Player player) {
    for (Map.Entry<Holder<ArmorMaterial>, MobEffectInstance> entry :
        MATERIAL_TO_EFFECT_MAP.entrySet()) {
      Holder<ArmorMaterial> mapArmorMaterial = entry.getKey();
      MobEffectInstance mapEffect = entry.getValue();

      if (hasPlayerCorrectArmorOn(mapArmorMaterial, player)) {
        addEffectToPlayer(player, mapEffect);
      }
    }
  }

  private void addEffectToPlayer(Player player, MobEffectInstance mapEffect) {
    boolean hasPlayerEffect = player.hasEffect(mapEffect.getEffect());

    if (!hasPlayerEffect) {
      player.addEffect(
          new MobEffectInstance(
              mapEffect.getEffect(), mapEffect.getDuration(), mapEffect.getAmplifier()));
    }
  }

  private boolean hasPlayerCorrectArmorOn(Holder<ArmorMaterial> mapArmorMaterial, Player player) {
    for (ItemStack armorStack : player.getArmorSlots()) {
      if (!(armorStack.getItem() instanceof ArmorItem armorItem)) {
        return false;
      }

      if (!armorItem.getMaterial().equals(mapArmorMaterial)) {
        return false;
      }
    }
    return true;
  }

  private boolean hasFullSuitOfArmorOn(Player player) {
    for (ItemStack armorStack : player.getArmorSlots()) {
      if (armorStack.isEmpty()) {
        return false;
      }
    }
    return true;
  }
}
