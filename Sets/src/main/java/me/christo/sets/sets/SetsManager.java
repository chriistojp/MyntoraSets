package me.christo.sets.sets;

import me.arcaniax.hdb.api.HeadDatabaseAPI;

import me.christo.sets.Sets;
import me.christo.sets.utils.Util;
import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SetsManager {


    private static File setsFile;
    private static FileConfiguration sets;

    public static List<String> getConfig(List<String> path) {

        path = getFile().getStringList(path.toString());
        return path;

    }

    public static FileConfiguration getFile() {

        return sets;

    }


    public static void loadFile() {
        setsFile = new File(Sets.getInstance().getDataFolder(), "sets.yml");
        if (!setsFile.exists()) {
            Sets.getInstance().saveResource("sets.yml", false);
        }
        sets = YamlConfiguration.loadConfiguration(setsFile);

        try {
            sets.save(setsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            sets.save(setsFile);
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }




    public static boolean isSetPiece(ItemStack armorPiece) {

        if (armorPiece.getType().equals(Material.LEATHER_CHESTPLATE) || armorPiece.getType().equals(Material.LEATHER_LEGGINGS)
                || armorPiece.getType().equals(Material.LEATHER_BOOTS)) {
            net.minecraft.server.v1_8_R3.ItemStack itemNMS = CraftItemStack.asNMSCopy(armorPiece);
            net.minecraft.server.v1_8_R3.NBTTagCompound itemC = (itemNMS.hasTag()) ? itemNMS.getTag()
                    : new net.minecraft.server.v1_8_R3.NBTTagCompound();
            if (!itemC.isEmpty()) {
                if (itemC.getBoolean("armor")) {

                    return true;
                    //      String type = itemC.getString("type");
                }
            }


        }

        return false;

    }

    public static boolean isWearingFullSet(Player p) {

        if (p.getInventory().getChestplate() != null && p.getInventory().getLeggings() != null &&
                p.getInventory().getBoots() != null) {
            NBTTagCompound chestplateCompound = convertToNMS(p.getInventory().getChestplate());
            NBTTagCompound leggingsCompound = convertToNMS(p.getInventory().getLeggings());
            NBTTagCompound bootsCompound = convertToNMS(p.getInventory().getBoots());

            if (chestplateCompound.getBoolean("armor") && leggingsCompound.getBoolean("armor") &&
                    bootsCompound.getBoolean("armor")) {

                String type = chestplateCompound.getString("type");
                if (leggingsCompound.getString("type").equals(type) && bootsCompound.getString("type").equals(type)) {
                    return true;
                }
            }
        }


        return false;
    }

    public static NBTTagCompound convertToNMS(ItemStack item) {

        net.minecraft.server.v1_8_R3.ItemStack itemNMS = CraftItemStack.asNMSCopy(item);

        return (itemNMS.hasTag()) ? itemNMS.getTag()
                : new NBTTagCompound();

    }

    public static List<ItemStack> getItems(me.christo.sets.sets.Sets type) {

        Color color = type.getColor(type);
        List<ItemStack> newItems = new ArrayList<>();

        HeadDatabaseAPI api = new HeadDatabaseAPI();
        Bukkit.broadcastMessage((type).toString().toLowerCase() + ".helmet.id");
        Bukkit.broadcastMessage(Sets.getInstance().getConfig().getString(type.toString().toLowerCase() + ".helmet.id"));
        ItemStack head = api.getItemHead(Sets.getInstance().getConfig().getString(type.toString().toLowerCase() + ".helmet.id"));
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        headMeta.setDisplayName(Util.color(Sets.getInstance().getConfig().getString(type.toString().toLowerCase() + ".helmet.name")));
        head.setItemMeta(headMeta);

        net.minecraft.server.v1_8_R3.ItemStack headNMS = CraftItemStack.asNMSCopy(head);
        net.minecraft.server.v1_8_R3.NBTTagCompound headC = (headNMS.hasTag()) ? headNMS.getTag()
                : new net.minecraft.server.v1_8_R3.NBTTagCompound();

        headC.setBoolean("armorMeta", true);
        headC.setString("type", type.toString().toLowerCase());
        ItemStack newHead = CraftItemStack.asBukkitCopy(headNMS);
        newItems.add(newHead);


        ItemStack[] items = {new ItemStack(Material.LEATHER_CHESTPLATE),
                new ItemStack(Material.LEATHER_LEGGINGS), new ItemStack(Material.LEATHER_BOOTS)};

        if (type == me.christo.sets.sets.Sets.ATHENA) {

            ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta meta = item.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add(Util.color("&7Hitting an opponent will &cwither &7them for &c3 seconds&7. "));
            meta.setLore(lore);
            meta.addEnchant(Enchantment.DAMAGE_ALL, 7, true);
            meta.addEnchant(Enchantment.KNOCKBACK, 1, true);
            meta.setDisplayName(Util.color(Sets.getInstance().getConfig().getString("athena.swordName")));
            item.setItemMeta(meta);

            newItems.add(item);

        }

        if (type == me.christo.sets.sets.Sets.POSEIDON) {

            ItemStack item = new ItemStack(Material.FISHING_ROD);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(Util.color(Sets.getInstance().getConfig().getString("poseidon.rodName")));
            meta.spigot().setUnbreakable(true);
            item.setItemMeta(meta);


            newItems.add(item);

        }

        if (type == me.christo.sets.sets.Sets.HERMES) {
            //Hermes’ Caduceus (Golden Pickaxe) auto smelts ores, efficiency 5, unbreakable, fortune 3
            ItemStack item = new ItemStack(Material.GOLD_PICKAXE);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(Util.color(Sets.getInstance().getConfig().getString("hermes.pickaxeName")));
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add(Util.color("&7This pickaxe will auto-smelt ores."));
            meta.spigot().setUnbreakable(true);
            meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
            meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3, true);
            meta.setLore(lore);
            item.setItemMeta(meta);
            newItems.add(item);

        }

        if (type == me.christo.sets.sets.Sets.ZEUS) {

            ItemStack item = new ItemStack(Material.GOLD_SWORD);
            ItemMeta meta = item.getItemMeta();
            meta.spigot().setUnbreakable(true);
            meta.setDisplayName(Util.color(Sets.getInstance().getConfig().getString("zeus.swordName")));
            meta.addEnchant(Enchantment.FIRE_ASPECT, 3, true);
            meta.addEnchant(Enchantment.DAMAGE_ALL, 10, true);
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add(Util.color("&7Shift right click to smite nearby enemies. A 10 second"));
            lore.add(Util.color("&7cooldown will be applied."));
            meta.setLore(lore);
            item.setItemMeta(meta);

            newItems.add(item);

        }

        for (ItemStack i : items) {

            String replaced = i.getType().toString().toLowerCase().replace("leather", "").replace(" ", "").replace("_", "");


            LeatherArmorMeta armorMeta = (LeatherArmorMeta) i.getItemMeta();
            armorMeta.setColor(color);
            Bukkit.broadcastMessage(type.toString().toLowerCase() + "." + replaced + ".name");
            armorMeta.setDisplayName(Util.color(Sets.getInstance().getConfig().getString(type.toString().toLowerCase() + "." + replaced + ".name")));
            armorMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
            List<String> armorLore = new ArrayList<>();
            if (Sets.getInstance().getConfig().isSet(type.toString().toLowerCase() + "." + replaced + ".lore")) {
                for (String s : Sets.getInstance().getConfig().getStringList(type.toString().toLowerCase() + "." + replaced + ".lore")) {
                    armorLore.add(Util.color(s));
                }
            }
            if (type == me.christo.sets.sets.Sets.POSEIDON && i.getType() == Material.LEATHER_BOOTS) {
                armorMeta.addEnchant(Enchantment.DEPTH_STRIDER, 3, true);
            }
            armorMeta.setLore(armorLore);
            i.setItemMeta(armorMeta);

            net.minecraft.server.v1_8_R3.ItemStack armorNMS = CraftItemStack.asNMSCopy(i);
            net.minecraft.server.v1_8_R3.NBTTagCompound armorC = (armorNMS.hasTag()) ? armorNMS.getTag()
                    : new net.minecraft.server.v1_8_R3.NBTTagCompound();

            armorC.setBoolean("armorMeta", true);
            armorC.setString("type", type.toString().toLowerCase());
            ItemStack newArmor = CraftItemStack.asBukkitCopy(armorNMS);

            newItems.add(newArmor);
        }


        return newItems;


    }


}
