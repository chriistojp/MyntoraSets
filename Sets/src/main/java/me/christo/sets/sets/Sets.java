package me.christo.sets.sets;

import me.christo.sets.utils.Util;
import org.apache.logging.log4j.core.appender.rewrite.MapRewritePolicy;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public enum Sets {




    APOLLO("apollo", Color.fromRGB(153, 153, 153)),
    ARTERMIS("artermis", Color.fromRGB(102, 153, 216)),
    ATHENA("athena", Color.fromRGB(42, 127, 165)),
    DEMETER("demeter", Color.fromRGB(76, 76, 76)),
    GUARD("guard", Color.fromRGB(216, 128, 51)),
    HADES("hades", Color.fromRGB(153, 153, 153)),
    HERMES("hermes", Color.fromRGB(229, 229, 51)),
    POSEIDON("poseidon", Color.fromRGB(51, 76, 178)),
    STARTER("starter", Color.fromRGB(153, 153, 153)),
    ZEUS("zeus", Color.fromRGB(255, 255, 255));

    private String displayName;
    private List<String> lore;
    private String type;
    private Color color;

    public static List<Player> apolloList = new ArrayList<>();
    public static List<Player> apolloPoison = new ArrayList<>();

    Sets(String type, Color color) {
        this.color = color;
        this.type = type;
    }


    public Color getColor(Sets type) {
        return Color.fromRGB(color.asRGB());
    }

    public void deactivateAbility(Player p, ItemStack item, String type) {

        if(type.equals("athena") && item.getType() == Material.LEATHER_CHESTPLATE) {
            p.removePotionEffect(PotionEffectType.REGENERATION);
            lostMsg(p, "Regeneration Effect");
        }
        if(type.equals("zeus") && item.getType() == Material.LEATHER_BOOTS) {
            p.setFlying(false);
            p.setAllowFlight(false);
            lostMsg(p, "Flying Ability");
        }
        if(type.equals("hermes") && item.getType() == Material.LEATHER_CHESTPLATE) {
            p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            lostMsg(p, "Strength Effect");
        }
        if(type.equals("hermes") && item.getType() == Material.SKULL_ITEM) {
            lostMsg(p, "15% luck in the casino.");
        }
        if(type.equalsIgnoreCase("poseidon") && item.getType() == Material.LEATHER_HELMET) {
            lostMsg(p, "Water Breathing Effect");
            p.removePotionEffect(PotionEffectType.WATER_BREATHING);
        }
        if(type.equals("poseidon") && item.getType() == Material.LEATHER_BOOTS) {
            lostMsg(p, "Depth Strider Effect");
        }
        if(type.equals("apollo") && item.getType() == Material.LEATHER_CHESTPLATE) {
            lostMsg(p, "Immunity to fall damage.");
            apolloList.remove(p);
        }
        if(type.equals("apollo") && item.getType() == Material.LEATHER_LEGGINGS) {
            lostMsg(p, "a 25% to poison enemies when they hit you.");
            apolloPoison.remove(p);
        }
    }

    public void activateAbility(Player p, ItemStack item, String type) {

        if(type.equals("athena") && item.getType() == Material.LEATHER_CHESTPLATE) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100000, 0));
            msg(p, "Regeneration Effect");
        }
        if(type.equals("zeus") && item.getType() == Material.LEATHER_BOOTS) {
            p.setAllowFlight(true);
            p.setFlying(true);
            msg(p, "Flying Ability");
        }
        if(type.equals("hermes") && item.getType() == Material.LEATHER_CHESTPLATE) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100000, 1));
            msg(p, "Strength Effect");
        }
        if(type.equals("hermes") && item.getType() == Material.SKULL_ITEM) {
            msg(p, "15% increased luck in the casino.");
        }
        if(type.equalsIgnoreCase("poseidon") && item.getType() == Material.LEATHER_HELMET) {
            msg(p, "Water Breathing Effect");
            p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 100000, 0));
        }
        if(type.equals("poseidon") && item.getType() == Material.LEATHER_BOOTS) {
            msg(p, "Depth Strider Effect");
        }
        if(type.equals("apollo") && item.getType() == Material.LEATHER_CHESTPLATE) {
            msg(p, "Immunity to fall damage.");
            apolloList.add(p);
        }
        if(type.equals("apollo") && item.getType() == Material.LEATHER_LEGGINGS) {
            msg(p, "a 25% to poison enemies when they hit you.");
            apolloPoison.add(p);
        }
    }

    public static Sets getFromString(String type) {

        for(Sets list : Sets.values()) {
            if(type.equalsIgnoreCase(list.type)) {
                return list;
            }


        }
        return null;
    }

    public void msg(Player p, String effect) {
        p.sendMessage(Util.color("&d[Sets] " + "&7Equipping that armor piece has granted you &d" + effect + "."));
    }
    public void lostMsg(Player p, String effect) {
        p.sendMessage(Util.color("&d[Sets] " + "&7Un-Equipping that armor piece has removed your &d" + effect + "."));
    }

}
