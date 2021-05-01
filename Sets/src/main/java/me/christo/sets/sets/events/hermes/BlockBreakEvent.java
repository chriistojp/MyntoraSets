package me.christo.sets.sets.events.hermes;

import me.christo.sets.Sets;
import me.christo.sets.utils.Util;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class BlockBreakEvent implements Listener {

    @EventHandler
    public void onBreak(org.bukkit.event.block.BlockBreakEvent e) {

        Player p = (Player) e.getPlayer();
        ItemStack i = p.getItemInHand();
        assert i.getType() != null;
        if (i.getType() == Material.GOLD_PICKAXE) {
            if (i.hasItemMeta()) {
                if (i.getItemMeta().hasDisplayName()) {
                    if (i.getItemMeta().getDisplayName().equals(Util.color(Sets.getInstance().getConfig().getString("hermes.pickaxeName")))) {
                        if(e.getBlock().getType() == Material.IRON_ORE) {
                            e.setCancelled(true);
                            e.getBlock().setType(Material.AIR);
                            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_ORE));
                        }


                        if(e.getBlock().getType() == Material.GOLD_ORE) {
                            e.setCancelled(true);
                            e.getBlock().setType(Material.AIR);
                            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_ORE));
                        }
                    }
                }
            }

        }
    }

}
