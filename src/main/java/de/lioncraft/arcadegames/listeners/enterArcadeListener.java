package de.lioncraft.arcadegames.listeners;

import de.lioncraft.arcadegames.ArcadeGames;
import de.lioncraft.arcadegames.data.defaultData;
import de.lioncraft.arcadegames.data.strings;
import de.lioncraft.arcadegames.events.playerEnterArcadeAreaEvent;
import de.lioncraft.arcadegames.utils.playMusic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;

public class enterArcadeListener implements Listener {
    @EventHandler
    public void onEnter(playerEnterArcadeAreaEvent e){
        if(e.hasEntered()){
            e.getPlayer().sendMessage(strings.PlayerEnterAreaMessage);
            defaultData.playerInArcadeArea.add(e.getPlayer());
            BukkitTask t0 = new playMusic(e.getPlayer(), 0).runTaskLater(ArcadeGames.getPlugin(), 1);
            BukkitTask t1 = new playMusic(e.getPlayer(), 1).runTaskLater(ArcadeGames.getPlugin(), 5);
            BukkitTask t2 = new playMusic(e.getPlayer(), 2).runTaskLater(ArcadeGames.getPlugin(), 9);
            BukkitTask t3 = new playMusic(e.getPlayer(), 3).runTaskLater(ArcadeGames.getPlugin(), 13);
        }else{
            e.getPlayer().sendMessage(strings.PlayerLeaveAreaMessage);
            defaultData.playerInArcadeArea.remove(e.getPlayer());
        }
    }
}
