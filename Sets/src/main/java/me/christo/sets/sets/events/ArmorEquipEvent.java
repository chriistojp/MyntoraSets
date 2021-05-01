package me.christo.sets.sets.events;

import me.christo.sets.sets.Sets;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ArmorEquipEvent implements Listener {



    @EventHandler
    public void onEquip(com.codingforcookies.armorequip.ArmorEquipEvent e) {

        Player p = e.getPlayer();
        Bukkit.broadcastMessage("t");


        if (e.getOldArmorPiece() != null) {
            if (e.getOldArmorPiece().hasItemMeta()) {
                if (e.getOldArmorPiece().getItemMeta().hasDisplayName()) {
                    String toMatch = ChatColor.stripColor(e.getOldArmorPiece().getItemMeta().getDisplayName()).toLowerCase();
                    String toSet = toMatch.replace("leggings", "").replace("boots", "")
                            .replace("helmet", "").replace("chestplate", "").replace(" ", "");
                    Sets set = Sets.getFromString(toSet);
                    assert set != null;
                    set.deactivateAbility(e.getPlayer(), e.getOldArmorPiece(), toSet);
                }
            }
        }

        if (e.getNewArmorPiece() != null) {
            if (e.getNewArmorPiece().hasItemMeta()) {
                if (e.getNewArmorPiece().getItemMeta().hasDisplayName()) {
                    String toMatch = ChatColor.stripColor(e.getNewArmorPiece().getItemMeta().getDisplayName()).toLowerCase();
                    String toSet = toMatch.replace("leggings", "").replace("boots", "")
                            .replace("helmet", "").replace("chestplate", "").replace(" ", "");
                    Sets set = Sets.getFromString(toSet);


                    assert set != null;
                    set.activateAbility(e.getPlayer(), e.getNewArmorPiece(), toSet);


                }

            }
        }
    }
}
