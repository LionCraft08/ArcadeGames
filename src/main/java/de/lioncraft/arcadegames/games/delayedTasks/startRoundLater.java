package de.lioncraft.arcadegames.games.delayedTasks;

import de.lioncraft.arcadegames.games.game;
import de.lioncraft.arcadegames.games.ssp;
import de.lioncraft.arcadegames.games.tiktaktoe;
import de.lioncraft.arcadegames.games.vierGewinnt;
import org.bukkit.scheduler.BukkitRunnable;

public class startRoundLater extends BukkitRunnable {
    @Override
    public void run() {
        if(ssp != null){
            ssp.startNewRound();
        } else if (tiktaktoe != null) {
            tiktaktoe.startNewRound();
        } else if (vierGewinnt != null) {
            vierGewinnt.startNewRound();
        }
    }
    ssp ssp;
    tiktaktoe tiktaktoe;
    vierGewinnt vierGewinnt;

    public startRoundLater(de.lioncraft.arcadegames.games.ssp ssp) {
        this.ssp = ssp;
    }
    public startRoundLater(tiktaktoe game){
        tiktaktoe = game;
    }
    public startRoundLater(vierGewinnt game){
        vierGewinnt = game;
    }
}
