package de.lioncraft.arcadegames;

import de.lioncraft.arcadegames.commands.defaultCommand;
import de.lioncraft.arcadegames.data.defaultData;
import de.lioncraft.arcadegames.data.inventoryContent;
import de.lioncraft.arcadegames.data.strings;
import de.lioncraft.arcadegames.listeners.*;
import de.lioncraft.arcadegames.data.buttons;
import de.lioncraft.arcadegames.utils.deleteExpiredRequests;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class ArcadeGames extends JavaPlugin {

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        new strings().setContents();
        new buttons().setVariables();
        new inventoryContent().setContent();
        getCommand("arcadegames").setExecutor(new defaultCommand());
        getServer().getPluginManager().registerEvents(new invClickListener(), this);
        getServer().getPluginManager().registerEvents(new enterArcadeListener(), this);
        getServer().getPluginManager().registerEvents(new playerMoveListener(), this);
        getServer().getPluginManager().registerEvents(new joinleavelistener(), this);
        getServer().getPluginManager().registerEvents(new interactListeners(), this);
        getServer().getPluginManager().registerEvents(new invCloseListener(), this);
        defaultData.playerInArcadeArea = new ArrayList<>();
        defaultData.interactableEntity = new ArrayList<>();
        defaultData.playerRequest = new HashMap<>();
        defaultData.activeTiktaktoe = new ArrayList<>();
        defaultData.activessp = new ArrayList<>();
        defaultData.activeVierGewinnt = new ArrayList<>();
        defaultData.pos1 = plugin.getConfig().getLocation("arcadegames.data.location1");
        defaultData.pos2 = plugin.getConfig().getLocation("arcadegames.data.location2");
        if(defaultData.pos1 != null && defaultData.pos2 != null){
            defaultData.isAreaSet = true;
        }
        if(!plugin.getConfig().getStringList("arcadegames.data.entities").isEmpty()){
            for(String s : plugin.getConfig().getStringList("arcadegames.data.entities")){
                defaultData.interactableEntity.add(UUID.fromString(s));
            }
        }
        BukkitTask t = new deleteExpiredRequests().runTaskTimer(plugin, 1200, 200);
    }

    @Override
    public void onDisable() {
        List<String> list = new ArrayList<>();
        for(UUID uuid : defaultData.interactableEntity){
            list.add(uuid.toString());
        }
        plugin.getConfig().set("arcadegames.data.entities", list);
        plugin.saveConfig();
    }
    public static ArcadeGames plugin;
    public static ArcadeGames getPlugin() { return plugin; }
}
