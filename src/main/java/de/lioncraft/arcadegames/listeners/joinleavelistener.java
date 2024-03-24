package de.lioncraft.arcadegames.listeners;

import de.lioncraft.arcadegames.data.defaultData;
import de.lioncraft.arcadegames.utils.baseMethods;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class joinleavelistener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if(new baseMethods().isInArcadeArea(e.getPlayer())){
            defaultData.playerInArcadeArea.add(e.getPlayer());
        }
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        if(new baseMethods().isInArcadeArea(e.getPlayer())){
            defaultData.playerInArcadeArea.remove(e.getPlayer());
        }
    }

}
