package net.alexyang.mccourse.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class SlowingSwordItem extends SwordItem {
  public SlowingSwordItem(Tier pTier, Properties pProperties) {
    super(pTier, pProperties);
  }

  @Override
  public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
    if (entity instanceof LivingEntity livingEntity) {
      livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 400, 1), player);
    }

    return super.onLeftClickEntity(stack, player, entity);
  }
}
