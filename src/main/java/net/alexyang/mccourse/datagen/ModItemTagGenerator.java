package net.alexyang.mccourse.datagen;

import net.alexyang.mccourse.MCCourseMod;
import net.alexyang.mccourse.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
  public ModItemTagGenerator(
      PackOutput packOutput,
      CompletableFuture<HolderLookup.Provider> future,
      CompletableFuture<TagLookup<Block>> completableFuture,
      @Nullable ExistingFileHelper existingFileHelper) {
    super(packOutput, future, completableFuture, MCCourseMod.MOD_ID, existingFileHelper);
  }

  @Override
  protected void addTags(HolderLookup.@NotNull Provider provider) {
    this.tag(ItemTags.TRIMMABLE_ARMOR)
        .add(
            ModItems.ALEXANDRITE_HELMET.get(),
            ModItems.ALEXANDRITE_CHESTPLATE.get(),
            ModItems.ALEXANDRITE_LEGGING.get(),
            ModItems.ALEXANDRITE_BOOTS.get());
  }

  @Override
  public @NotNull String getName() {
    return "Item Tags";
  }
}
