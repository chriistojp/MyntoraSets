package me.christo.sets.sets.events.zeus;

import me.christo.cooldown.api.API;
import me.christo.sets.Sets;
import me.christo.sets.utils.Util;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class StrikeLightningEvent implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        assert p.getItemInHand() != null;
        ItemStack i = p.getItemInHand();

        if (p.getItemInHand().getType() == Material.GOLD_SWORD) {
            if (i.hasItemMeta()) {
                if (i.getItemMeta().hasDisplayName()) {
                    if (i.getItemMeta().getDisplayName().equals(Util.color(Sets.getInstance().getConfig().getString("zeus.swordName")))) {

                        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                            if (p.isSneaking()) {

                                API api = new API(p.getUniqueId());
                                if (api.hasCooldown("zeusSword")) {
                                    p.sendMessage(Util.color("&d[Cooldown] &7You have &d" + api.getTimeLeft("zeusSword") + "s &7remaining"));
                                    return;
                                }

                                //api.hasExistingCooldown
                                //api.getTimeLeft
                                //api.deleteCooldown
                                //api.setCooldown
                                //api.createCooldown

                                if (!api.hasCooldown("zeusSword")) {
                                    api.createCooldown("zeusSword", 10);


                                    for (Entity entity : p.getNearbyEntities(5, 5, 5)) {
                                        if (entity instanceof Player) {
                                            if (entity == p) {
                                                continue;
                                            }
                                            entity.getWorld().strikeLightning(entity.getLocation());
                                        }
                                    }

                                }
                            }

                        }
                    }
                }
            }
        }

    }


}
