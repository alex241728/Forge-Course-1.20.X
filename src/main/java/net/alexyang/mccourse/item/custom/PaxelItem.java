package net.alexyang.mccourse.item.custom;

import net.alexyang.mccourse.util.ModTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;

public class PaxelItem extends DiggerItem {
  public PaxelItem(Tier pTier, Properties pProperties) {
    super(pTier, ModTags.Blocks.PAXEL_MINEABLE, pProperties);
  }
}
