package de.lioncraft.arcadegames.utils;

import de.lioncraft.arcadegames.data.defaultData;
import de.lioncraft.arcadegames.games.GameRequest;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class deleteExpiredRequests extends BukkitRunnable {
    @Override
    public void run() {
        for (Player p : defaultData.playerRequest.keySet()){
            GameRequest gr = defaultData.playerRequest.get(p);
            if(gr.getTime() > System.currentTimeMillis() + 60000){
                defaultData.playerRequest.remove(p, gr);
            }
        }
    }
}
