package me.christo.sets;

import me.arcaniax.hdb.api.DatabaseLoadEvent;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import me.christo.sets.sets.commands.GiveSetCommand;
import me.christo.sets.sets.events.ArmorEquipEvent;
import me.christo.sets.sets.events.athena.EntityDamageEntityEvent;
import me.christo.sets.sets.events.hermes.BlockBreakEvent;
import me.christo.sets.sets.events.zeus.StrikeLightningEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;


public final class Sets extends JavaPlugin implements Listener {

    public static Sets instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists())
            saveDefaultConfig();



        getCommand("set").setExecutor(new GiveSetCommand());
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new ArmorEquipEvent(),this);

        //hermes
        getServer().getPluginManager().registerEvents(new BlockBreakEvent(), this);

        //zeus
        getServer().getPluginManager().registerEvents(new StrikeLightningEvent(), this);

        //athena
        getServer().getPluginManager().registerEvents(new EntityDamageEntityEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onDatabaseLoad(DatabaseLoadEvent e) {
        HeadDatabaseAPI api = new HeadDatabaseAPI();
        try {
            ItemStack item = api.getItemHead("7129");
            getLogger().info(api.getItemID(item));
        } catch (NullPointerException nullPointerException) {
            getLogger().info("Could not find the head you were looking for");
        }
    }

    public static Sets getInstance() {
        return instance;
    }
}
