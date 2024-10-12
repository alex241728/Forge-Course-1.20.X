package net.alexyang.mccourse.item.custom;

import net.alexyang.mccourse.util.ModTags;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        Player player = pContext.getPlayer();

        if (player == null) {
            return InteractionResult.FAIL;
        }

        ItemStack itemStack = player.getItemInHand(player.getUsedItemHand());

        if(!pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            boolean foundBlock = false;

            for(int i = 0; i <= positionClicked.getY() + 64; i++) {
                BlockState blockState = pContext.getLevel().getBlockState(positionClicked.below(i));

                if(isValuableBlock(blockState)) {
                    outputValuableCoordinates(positionClicked.below(i), player, blockState.getBlock());
                    foundBlock = true;
                }
            }

            if(!foundBlock) {
                outputNoValuableFound(player);
            }

            player.sendSystemMessage(Component.literal(
                    "=====================================================")
            );
        }

        if (itemStack.isEmpty()) {
            ForgeEventFactory.onPlayerDestroyItem(player, itemStack, LivingEntity.getSlotForHand(player.getUsedItemHand()));
        }
        else {
            itemStack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(player.getUsedItemHand()));
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @NotNull TooltipContext pLevel,
                                @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.mccourse.metal_detector.tooltip.shift"));
        }
        else {
            pTooltipComponents.add(Component.translatable("tooltip.mccourse.metal_detector.tooltip"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private void outputNoValuableFound(Player player) {
        player.sendSystemMessage(Component.translatable("item.mccourse.metal_detector.no_valuables"));
    }

    private void outputValuableCoordinates(BlockPos below, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Valuable Found: " + I18n.get(block.getDescriptionId()) +
                " at (" + below.getX() + ", " + below.getY() + ", " + below.getZ() + ")"));
    }

    private boolean isValuableBlock(BlockState blockState) {
        return blockState.is(ModTags.Blocks.METAL_DETECTOR_VALUABLES);
    }
}
