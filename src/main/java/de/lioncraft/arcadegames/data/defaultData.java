package de.lioncraft.arcadegames.data;

import de.lioncraft.arcadegames.ArcadeGames;
import de.lioncraft.arcadegames.games.*;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class defaultData {
    public static Location pos1;
    public static Location pos2;
    public static List<Player> playerInArcadeArea;
    public static List<UUID> interactableEntity;
    public static HashMap<Player, GameRequest> playerRequest;
    public static List<tiktaktoe> activeTiktaktoe;
    public static List<ssp> activessp;
    public static List<vierGewinnt> activeVierGewinnt;
    public static HashMap<Inventory, ? extends game> activeGames;
    public static boolean isAreaSet;
    //pos1 hat immer grössere werte
    public void orderLocations(){
        if(pos1.getX() < pos2.getX()){
            double temp = pos1.getX();
            pos1.setX(pos2.getX());
            pos2.setX(temp);
        }
        if(pos1.getY() < pos2.getY()){
            double temp = pos1.getY();
            pos1.setY(pos2.getY());
            pos2.setY(temp);
        }
        if(pos1.getZ() < pos2.getZ()){
            double temp = pos1.getZ();
            pos1.setZ(pos2.getZ());
            pos2.setZ(temp);
        }
        ArcadeGames.getPlugin().getConfig().set("arcadegames.data.location2", pos2);
        ArcadeGames.getPlugin().getConfig().set("arcadegames.data.location1", pos1);
        ArcadeGames.getPlugin().saveConfig();
    }
}
