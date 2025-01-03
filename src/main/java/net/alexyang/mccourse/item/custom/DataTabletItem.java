package net.alexyang.mccourse.item.custom;

import net.alexyang.mccourse.component.ModDataComponentTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class DataTabletItem extends Item {
  public DataTabletItem(Properties pProperties) {
    super(pProperties);
  }

  @Override
  public @NotNull InteractionResultHolder<ItemStack> use(
      @NotNull Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
    ItemStack stack = pPlayer.getItemInHand(pUsedHand);
    if (!stack.has(ModDataComponentTypes.COORDINATES)) {
      stack.set(ModDataComponentTypes.COORDINATES, CustomData.EMPTY);
    }
    return super.use(pLevel, pPlayer, pUsedHand);
  }

  @Override
  public boolean isFoil(@NotNull ItemStack pStack) {
    return pStack.has(ModDataComponentTypes.COORDINATES);
  }

  @Override
  public void appendHoverText(
      ItemStack pStack,
      @NotNull TooltipContext pContext,
      @NotNull List<Component> pTooltipComponents,
      @NotNull TooltipFlag pTooltipFlag) {
    if (pStack.has(ModDataComponentTypes.COORDINATES)) {
      String currentFoundOre =
          Objects.requireNonNull(pStack.get(ModDataComponentTypes.COORDINATES))
              .copyTag()
              .getString("mccourse.found_ore");
      pTooltipComponents.add(Component.literal(currentFoundOre));
    }
    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
  }
}
