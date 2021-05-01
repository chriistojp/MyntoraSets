package me.christo.sets.sets.events.athena;

import me.christo.sets.Sets;
import me.christo.sets.utils.Util;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.management.ListenerNotFoundException;

public class EntityDamageEntityEvent implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {



        if(e.getEntity() instanceof Player) {
            if(e.getDamager() instanceof Player) {

                Player damager = (Player) e.getDamager();
                Player toWither = (Player) e.getEntity();
                assert damager.getItemInHand() != null;
                ItemStack i = damager.getItemInHand();
                if(i.getType() == Material.DIAMOND_SWORD) {
                    if(i.hasItemMeta()) {
                        if(i.getItemMeta().hasDisplayName()) {
                            if(i.getItemMeta().getDisplayName().equals(Util.color(Sets.getInstance().getConfig().getString("athena.swordName")))) {

                                toWither.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 3, 0));


                            }
                        }
                    }
                }



            }
        }

    }

}
