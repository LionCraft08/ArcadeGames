package de.lioncraft.arcadegames.listeners;

import de.lioncraft.arcadegames.ArcadeGames;
import de.lioncraft.arcadegames.data.defaultData;
import de.lioncraft.arcadegames.events.playerEnterArcadeAreaEvent;
import de.lioncraft.arcadegames.utils.baseMethods;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class playerMoveListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(new baseMethods().isInArcadeArea(e.getPlayer())){
            if (!defaultData.playerInArcadeArea.contains(e.getPlayer())) {
                Bukkit.getPluginManager().callEvent(new playerEnterArcadeAreaEvent(e.getPlayer(), true));

            }
        }else{
            if (defaultData.playerInArcadeArea.contains(e.getPlayer())) {
                Bukkit.getPluginManager().callEvent(new playerEnterArcadeAreaEvent(e.getPlayer(), false));
            }
        }

    }

}
