package net.alexyang.mccourse.event;

import net.alexyang.mccourse.MCCourseMod;
import net.alexyang.mccourse.command.ReturnHomeCommand;
import net.alexyang.mccourse.command.SetHomeCommand;
import net.alexyang.mccourse.item.custom.HammerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = MCCourseMod.MOD_ID)
public class ModEvents {
  private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

  @SubscribeEvent
  public static void onHammerUsage(BlockEvent.BreakEvent event) {
    Player player = event.getPlayer();
    ItemStack mainHandItem = player.getMainHandItem();

    if (mainHandItem.getItem() instanceof HammerItem hammer
        && player instanceof ServerPlayer serverPlayer) {
      BlockPos initialBlockPos = event.getPos();
      if (HARVESTED_BLOCKS.contains(initialBlockPos)) {
        return;
      }

      for (BlockPos pos : HammerItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
        if (pos == initialBlockPos
            || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
          continue;
        }

        HARVESTED_BLOCKS.add(pos);
        serverPlayer.gameMode.destroyBlock(pos);
        HARVESTED_BLOCKS.remove(pos);
      }
    }
  }

  @SubscribeEvent
  public static void onCommandRegister(RegisterCommandsEvent event) {
    new SetHomeCommand(event.getDispatcher());
    new ReturnHomeCommand(event.getDispatcher());

    ConfigCommand.register(event.getDispatcher());
  }

  @SubscribeEvent
  public static void onPlayerCloned(PlayerEvent.Clone event) {
    event
        .getEntity()
        .getPersistentData()
        .putIntArray(
            "mccourse.homepos",
            event.getOriginal().getPersistentData().getIntArray("mccourse.homepos"));
  }
}
