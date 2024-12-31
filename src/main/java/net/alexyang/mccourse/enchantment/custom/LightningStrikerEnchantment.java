package net.alexyang.mccourse.enchantment.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;

public class LightningStrikerEnchantment extends Enchantment {
  public LightningStrikerEnchantment(EnchantmentDefinition pDefinition) {
    super(pDefinition);
  }

  @Override
  public void doPostAttack(LivingEntity pAttacker, @NotNull Entity pTarget, int pLevel) {
    if (!pAttacker.level().isClientSide()) {
      ServerLevel level = (ServerLevel) pAttacker.level();
      BlockPos position = pTarget.blockPosition();

      if (pLevel == 1) {
        EntityType.LIGHTNING_BOLT.spawn(
            level, null, null, position, MobSpawnType.TRIGGERED, true, true);
      }

      if (pLevel == 2) {
        EntityType.LIGHTNING_BOLT.spawn(
            level, null, null, position, MobSpawnType.TRIGGERED, true, true);
        EntityType.LIGHTNING_BOLT.spawn(
            level, null, null, position, MobSpawnType.TRIGGERED, true, true);
      }
    }

    super.doPostAttack(pAttacker, pTarget, pLevel);
  }
}
