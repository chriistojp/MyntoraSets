package me.christo.sets.sets.commands;

import me.christo.sets.sets.Sets;
import me.christo.sets.sets.SetsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class GiveSetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player) sender;



        if(args.length == 1) {
            List<ItemStack> items = SetsManager.getItems(Sets.getFromString(args[0]));

            for(ItemStack i : items) {
                p.getInventory().addItem(i);
            }

        }

        return false;
    }
}
