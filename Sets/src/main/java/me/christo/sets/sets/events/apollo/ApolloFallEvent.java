package me.christo.sets.sets.events.apollo;

import me.christo.sets.sets.Sets;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class ApolloFallEvent implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {

        if(e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            if(Sets.apolloList.contains((Player) e.getEntity())) {
                e.setCancelled(true);
                e.setDamage(0);
            }


        }

    }

}
