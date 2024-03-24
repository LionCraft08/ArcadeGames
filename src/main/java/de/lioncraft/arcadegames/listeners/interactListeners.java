package de.lioncraft.arcadegames.listeners;

import de.lioncraft.arcadegames.data.defaultData;
import de.lioncraft.arcadegames.utils.inventories;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class interactListeners implements Listener {
    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent e){
        if(defaultData.interactableEntity.contains(e.getRightClicked().getUniqueId())){
            e.setCancelled(true);
            if(e.getPlayer().hasPermission("arcadegames.play")){
                new inventories().openMainGUI(e.getPlayer());
            }

        }
    }

}
