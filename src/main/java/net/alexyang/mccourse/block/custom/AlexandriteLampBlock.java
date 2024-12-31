package net.alexyang.mccourse.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class AlexandriteLampBlock extends Block {
  public static final BooleanProperty LIT = BlockStateProperties.LIT;

  public AlexandriteLampBlock(Properties pProperties) {
    super(pProperties);
    this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
    pBuilder.add(LIT);
  }

  @Override
  protected @NotNull ItemInteractionResult useItemOn(
      @NotNull ItemStack pStack,
      @NotNull BlockState pState,
      Level pLevel,
      @NotNull BlockPos pPos,
      @NotNull Player pPlayer,
      @NotNull InteractionHand pHand,
      @NotNull BlockHitResult pHitResult) {
    if (!pLevel.isClientSide() && pHand.equals(InteractionHand.MAIN_HAND)) {
      boolean currentState = pState.getValue(LIT);
      pLevel.setBlock(pPos, pState.setValue(LIT, !currentState), 3);
    }

    return ItemInteractionResult.SUCCESS;
  }
}
