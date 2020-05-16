package net.waterraid.scaffold;

import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.LinkedList;
import java.util.Queue;

public class Listeners implements Listener {
    private static final int maxBlocks = 64;
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event){
        if (event.getBlock().getType() == Material.HUGE_MUSHROOM_1) {
            Queue<Location> queue = new LinkedList<>();
            queue.add(event.getBlock().getLocation());
            event.setCancelled(true);
            new BukkitRunnable() {
                int blocksIterated = 0;
                @Override
                public void run() {
                    if (queue.isEmpty()){
                        this.cancel();
                        return;
                    }
                    Location currBlock = queue.remove();
                    for (BlockFace bf: BlockFace.values()){
                        Location loc = currBlock.getBlock().getRelative(bf).getLocation();
                        if (loc.getBlock().getType() == Material.HUGE_MUSHROOM_1 && !queue.contains(loc)) {
                            queue.add(loc);
                        }
                    }
                    if (currBlock.getBlock().getType() == Material.HUGE_MUSHROOM_1) {
                        currBlock.getBlock().setType(Material.AIR);
                        currBlock.getWorld().playEffect(currBlock, Effect.TILE_BREAK,1);
                        currBlock.getWorld().playSound(currBlock, Sound.BLOCK_WOOD_BREAK, 1, 1);
                        currBlock.getWorld().dropItem(currBlock, Main.scaffoldItem);
                    }
                    blocksIterated++;
                    if (blocksIterated >= maxBlocks){
                        this.cancel();
                        return;
                    }

                }
            }.runTaskTimer(Main.getInstance(), 0, 1);


        }
    }
}
