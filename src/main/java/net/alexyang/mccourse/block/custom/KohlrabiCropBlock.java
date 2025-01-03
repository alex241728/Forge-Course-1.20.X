package net.alexyang.mccourse.block.custom;

import net.alexyang.mccourse.item.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;

public class KohlrabiCropBlock extends CropBlock {
  public static final int MAX_AGE = 6;
  public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);

  public KohlrabiCropBlock(Properties pProperties) {
    super(pProperties);
  }

  @Override
  protected @NotNull ItemLike getBaseSeedId() {
    return ModItems.KOHLRABI_SEEDS.get();
  }

  @Override
  public @NotNull IntegerProperty getAgeProperty() {
    return AGE;
  }

  @Override
  public int getMaxAge() {
    return MAX_AGE;
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
    pBuilder.add(AGE);
  }
}
