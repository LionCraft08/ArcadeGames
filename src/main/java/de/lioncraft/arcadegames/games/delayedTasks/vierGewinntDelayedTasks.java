package de.lioncraft.arcadegames.games.delayedTasks;

import de.lioncraft.arcadegames.games.vierGewinnt;
import org.bukkit.scheduler.BukkitRunnable;

public class vierGewinntDelayedTasks extends BukkitRunnable {
    @Override
    public void run() {
        if(vierGewinnt.getRounds() > vierGewinnt.getP1wins() + vierGewinnt.getP2wins()){
            vierGewinnt.startNewRound();
        }else{
            vierGewinnt.endGame();
        }
    }
    de.lioncraft.arcadegames.games.vierGewinnt vierGewinnt;
    public vierGewinntDelayedTasks(vierGewinnt vierGewinnt) {
        this.vierGewinnt = vierGewinnt;
    }
}
